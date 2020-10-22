package com.mycmv.user.model.vo;

import com.mycmv.user.model.entry.TeacherInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherInfoVo extends TeacherInfo {
    private String schoolName;
    private Integer provinceCode;
    private String provinceName;
    private Integer cityCode;
    private String cityName;
    private Integer townCode;
    private String townName;
}
