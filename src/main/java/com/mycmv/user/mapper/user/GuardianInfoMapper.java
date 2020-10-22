package com.mycmv.user.mapper.user;

import com.mycmv.user.mapper.UserInfoMapper;
import com.mycmv.user.model.entry.GuardianInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * GradeInfo
 * @author oudi
 */
public interface GuardianInfoMapper extends UserInfoMapper<GuardianInfo> {


    /***
     * 列表
     * @param list list
     * @return list
     */
    List<GuardianInfo> listByPhoneList(@Param("list") List<String> list);
}
