package com.mycmv.user.controller.rest;

import com.google.common.base.Preconditions;
import com.mycmv.user.constants.LogConstants;
import com.mycmv.user.model.ResponseObject;
import com.mycmv.user.model.config.UserTypeEnum;
import com.mycmv.user.model.entry.StudentInfo;
import com.mycmv.user.model.vo.StudentInfoListVo;
import com.mycmv.user.service.StudentInfoService;
import com.mycmv.user.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * student
 * @author a
 */
@RestController
@RequestMapping("register/student")
public class StudentInfoRegister {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);

    @Resource
    private StudentInfoService studentInfoService;

    @ResponseBody
    @PostMapping
    public ResponseObject add(@RequestBody StudentInfo studentInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(studentInfo, "用户信息不能为空");
        studentInfoService.insert(studentInfo, UserTypeEnum.STUDENT);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

    @ResponseBody
    @PostMapping("list")
    public ResponseObject addList(@RequestBody StudentInfoListVo studentInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(studentInfo, "用户信息不能为空");
        studentInfoService.batchInsert(studentInfo.getStudentInfoList(), UserTypeEnum.STUDENT);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
