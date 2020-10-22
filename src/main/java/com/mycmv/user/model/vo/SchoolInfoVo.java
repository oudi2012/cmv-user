package com.mycmv.user.model.vo;

import com.mycmv.user.model.entry.SchoolInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * SchoolInfo
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SchoolInfoVo extends SchoolInfo {
    private Integer provinceCode;
    private String provinceName;
    private Integer cityCode;
    private String cityName;
    private Integer townCode;
    private String townName;
}
