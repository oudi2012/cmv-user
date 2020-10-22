package com.mycmv.user.model.vo;

import com.mycmv.user.model.entry.TeacherInfo;
import lombok.Data;

import java.util.List;

/***
 * @author a
 */
@Data
public class TeacherInfoListVo {
    private List<TeacherInfo> teacherInfoList;
}
