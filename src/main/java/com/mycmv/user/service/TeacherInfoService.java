package com.mycmv.user.service;

import com.mycmv.user.model.entry.TeacherInfo;
import com.mycmv.user.model.vo.TeacherInfoVo;

import java.util.List;

/***
 * 教师接口
 * @author oudi
 */
public interface TeacherInfoService extends UserInfoService<TeacherInfo> {

    /***
     * 格式化单条
     * @param teacherInfo teacherInfo
     * @return List
     */
    TeacherInfoVo itemToVo(TeacherInfo teacherInfo);

    /***
     * 格式化列表
     * @param teacherInfoList teacherInfoList
     * @return List
     */
    List<TeacherInfoVo> listToVo(List<TeacherInfo> teacherInfoList);

    /***
     * 详细
     * @param id id
     * @return
     */
    TeacherInfoVo findVoById(Long id);

}
