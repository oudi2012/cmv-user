package com.mycmv.user.controller.rest;

import com.google.common.base.Preconditions;
import com.mycmv.user.constants.LogConstants;
import com.mycmv.user.exception.BusinessException;
import com.mycmv.user.model.ResponseObject;
import com.mycmv.user.model.config.UserTypeEnum;
import com.mycmv.user.model.entry.AdminInfo;
import com.mycmv.user.model.vo.LoginVo;
import com.mycmv.user.service.AdminInfoService;
import com.mycmv.user.service.TokenService;
import com.mycmv.user.utils.CmvDesUtils;
import com.mycmv.user.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.mycmv.user.constants.UserConstants.DEFAULT_PWD;

/***
 * @author a
 */
@RestController
@RequestMapping("login")
public class AdminLogin {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.ADM_LOG);

    @Resource
    private AdminInfoService adminInfoService;
    @Resource
    private TokenService tokenService;

    @PostMapping("admin")
    public ResponseObject login(@RequestBody LoginVo loginVo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkArgument(!ObjectUtils.isEmpty(loginVo), "登录信息不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(loginVo.getUserName()), "登录名不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(loginVo.getPassWord()), "密码不能为空");
        if (!StringUtils.isEmpty(loginVo.getUserName())) {
            AdminInfo tmpItem = adminInfoService.findByUserName(loginVo.getUserName());
            if (ObjectUtils.isEmpty(tmpItem)) {
                throw new BusinessException(501, "该用户名还没有注册");
            }
            String pwd = CmvDesUtils.encrypt(loginVo.getPassWord());
            if (pwd != null && !pwd.equals(tmpItem.getPassWord())) {
                throw new BusinessException(501, "用户名和密码不对");
            }
            loginVo.setPassWord(pwd);
            loginVo.setId(tmpItem.getId());
            loginVo.setUserType(UserTypeEnum.ADMIN.getCode());
            String token = tokenService.getToken(loginVo);
            logger.info("用户 {} 登录，生成的 token :{}", loginVo.getUserName(), token);
            loginVo.setPassWord(null);
            loginVo.setToken(token);
            CommonUtils.executeSuccess(resObj, loginVo);
        }
        return resObj;
    }

    @PostMapping("reSetPassWd")
    public ResponseObject rePassWd(@RequestBody LoginVo loginVo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(loginVo, "用户信息不能为空");
        if (!StringUtils.isEmpty(loginVo.getUserName())) {
            AdminInfo tmpItem = adminInfoService.findByUserName(loginVo.getUserName());
            if (ObjectUtils.isEmpty(tmpItem)) {
                throw new BusinessException(501, "该用户名还没有注册");
            }
            String pwd = CmvDesUtils.encrypt(DEFAULT_PWD);
            tmpItem.setPassWord(pwd);
            adminInfoService.edit(tmpItem, UserTypeEnum.ADMIN);
            CommonUtils.executeSuccess(resObj, loginVo);
        } else {
            CommonUtils.executeFailure(resObj, "用户信息不能为空");
        }
        return resObj;
    }

}
