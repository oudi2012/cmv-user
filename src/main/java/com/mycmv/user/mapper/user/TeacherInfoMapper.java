package com.mycmv.user.mapper.user;

import com.mycmv.user.mapper.UserInfoMapper;
import com.mycmv.user.model.entry.TeacherInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * GradeInfo
 * @author oudi
 */
public interface TeacherInfoMapper extends UserInfoMapper<TeacherInfo> {

    /***
     * 列表
     * @param list list
     * @return list
     */
    List<TeacherInfo> listByPhoneList(@Param("list") List<String> list);
}
