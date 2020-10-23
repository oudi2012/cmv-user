package com.mycmv.user.service;


import com.mycmv.user.model.entry.GuardianInfo;
import com.mycmv.user.model.vo.GuardianStudentVo;

/***
 * 监护人
 * @author a
 */
public interface GuardianInfoService extends UserInfoService<GuardianInfo>{

    /***
     * 绑定监护人
     * @param guardianStudentVo guardianStudentVo
     * @return
     */
    void bindGuardianInfo(GuardianStudentVo guardianStudentVo);

}
