package com.mycmv.user.controller.rest;

import com.google.common.base.Preconditions;
import com.mycmv.user.constants.LogConstants;
import com.mycmv.user.exception.BusinessException;
import com.mycmv.user.model.ResponseObject;
import com.mycmv.user.model.entry.StudentInfo;
import com.mycmv.user.model.vo.LoginVo;
import com.mycmv.user.service.StudentInfoService;
import com.mycmv.user.utils.CmvDesUtils;
import com.mycmv.user.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * @author a
 */
@RestController
@RequestMapping("login/student")
public class StudentInfoLogin {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);

    @Resource
    private StudentInfoService studentInfoService;

    @ResponseBody
    @PostMapping
    public ResponseObject login(@RequestBody LoginVo loginVo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(loginVo, "登录信息不能为空");
        Preconditions.checkNotNull(loginVo.getPassWord(), "密码不能为空");
        if (!StringUtils.isEmpty(loginVo.getPhone())) {
            StudentInfo tmpItem = studentInfoService.findByPhone(loginVo.getPhone());
            if (ObjectUtils.isEmpty(tmpItem)) {
                throw new BusinessException(501, "该手机号还没有注册");
            }
            String pwd = CmvDesUtils.encrypt(loginVo.getPassWord());
            if (pwd != null && !pwd.equals(tmpItem.getPassWord())) {
                throw new BusinessException(501, "用户名和密码不对");
            }
        }
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
