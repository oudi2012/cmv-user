package com.mycmv.user.model.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * TeacherInfo
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherInfo extends UserInfo{
    /** 所属学校 */
    private Long schoolId;
}
