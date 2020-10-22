package com.mycmv.user.model.vo;


import com.mycmv.user.model.entry.GuardianInfo;
import com.mycmv.user.model.entry.StudentInfo;
import lombok.Data;

/***
 * @author a
 */
@Data
public class GuardianStudentVo {
    private GuardianInfo guardianInfo;
    private StudentInfo studentInfo;
}
