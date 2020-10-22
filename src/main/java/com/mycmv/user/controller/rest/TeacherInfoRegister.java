package com.mycmv.user.controller.rest;


import com.google.common.base.Preconditions;
import com.mycmv.user.constants.LogConstants;
import com.mycmv.user.model.ResponseObject;
import com.mycmv.user.model.config.UserTypeEnum;
import com.mycmv.user.model.entry.TeacherInfo;
import com.mycmv.user.model.vo.TeacherInfoListVo;
import com.mycmv.user.service.TeacherInfoService;
import com.mycmv.user.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * teacher
 * @author a
 */
@RestController
@RequestMapping("register/teacher")
public class TeacherInfoRegister {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.TEACH_LOG);

    @Resource
    private TeacherInfoService teacherInfoService;

    @ResponseBody
    @PostMapping
    public ResponseObject add(@RequestBody TeacherInfo teacherInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(teacherInfo, "用户信息不能为空");
        if (!StringUtils.isEmpty(teacherInfo.getUserName())) {
            Preconditions.checkArgument(teacherInfo.getUserName().length() < 6, "用户名不能少于6位");
        }
        teacherInfoService.insert(teacherInfo, UserTypeEnum.TEACHER);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }


    @ResponseBody
    @PostMapping("list")
    public ResponseObject addList(@RequestBody TeacherInfoListVo teacherInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(teacherInfo, "用户信息不能为空");
        teacherInfo.getTeacherInfoList().forEach(item -> {
            if (!StringUtils.isEmpty(item.getUserName())) {
                Preconditions.checkArgument(item.getUserName().length() < 6, "用户名不能少于6位");
            }
        });
        teacherInfoService.batchInsert(teacherInfo.getTeacherInfoList(), UserTypeEnum.TEACHER);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
