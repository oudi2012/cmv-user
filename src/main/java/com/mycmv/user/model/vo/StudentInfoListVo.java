package com.mycmv.user.model.vo;

import com.mycmv.user.model.entry.StudentInfo;
import lombok.Data;

import java.util.List;

/***
 * @author a
 */
@Data
public class StudentInfoListVo {
    private List<StudentInfo> studentInfoList;
}
