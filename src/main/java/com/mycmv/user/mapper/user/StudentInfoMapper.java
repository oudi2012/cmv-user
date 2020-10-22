package com.mycmv.user.mapper.user;

import com.mycmv.user.mapper.UserInfoMapper;
import com.mycmv.user.model.entry.StudentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * GradeInfo
 * @author oudi
 */
public interface StudentInfoMapper extends UserInfoMapper<StudentInfo> {


    /***
     * 列表
     * @param list list
     * @return list
     */
    List<StudentInfo> listByPhoneList(@Param("list") List<String> list);
}
