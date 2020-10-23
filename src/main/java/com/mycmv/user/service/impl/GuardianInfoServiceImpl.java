package com.mycmv.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mycmv.user.constants.LogConstants;
import com.mycmv.user.exception.BusinessException;
import com.mycmv.user.mapper.UserInfoMapper;
import com.mycmv.user.mapper.user.GuardianInfoMapper;
import com.mycmv.user.model.config.UserTypeEnum;
import com.mycmv.user.model.entry.GuardianInfo;
import com.mycmv.user.model.entry.StudentInfo;
import com.mycmv.user.model.vo.GuardianStudentVo;
import com.mycmv.user.service.GuardianInfoService;
import com.mycmv.user.service.StudentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/***
 * 监护人
 * @author a
 */
@Service
public class GuardianInfoServiceImpl extends AbstractUserInfoService<GuardianInfo> implements GuardianInfoService {


    @Resource
    private GuardianInfoMapper guardianInfoMapper;
    @Resource
    private StudentInfoService studentInfoService;

    @Override
    public UserInfoMapper<GuardianInfo> getUserInfoMapper() {
        return guardianInfoMapper;
    }


    @Override
    public void bindGuardianInfo(GuardianStudentVo guardianStudentVo) {
        GuardianInfo guardianInfo = guardianStudentVo.getGuardianInfo();
        StudentInfo studentInfo = guardianStudentVo.getStudentInfo();
        studentInfo.setGuardianId(guardianInfo.getId());
        if (studentInfo.getId() == null) {
            throw new BusinessException(501, "请选择要绑定的学生");
        }
        studentInfoService.edit(studentInfo, UserTypeEnum.STUDENT);
    }
}
