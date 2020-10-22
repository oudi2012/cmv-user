package com.mycmv.user.service.impl;

import com.mycmv.user.mapper.user.StudentInfoMapper;
import com.mycmv.user.mapper.UserInfoMapper;
import com.mycmv.user.model.entry.StudentInfo;
import com.mycmv.user.service.StudentInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/***
 * StudentInfoService
 * @author a
 */
@Service
public class StudentInfoServiceImpl extends AbstractUserInfoService<StudentInfo> implements StudentInfoService {

    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Override
    public UserInfoMapper<StudentInfo> getUserInfoMapper() {
        return studentInfoMapper;
    }
}
