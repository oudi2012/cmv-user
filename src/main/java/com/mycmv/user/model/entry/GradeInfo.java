package com.mycmv.user.model.entry;

import lombok.Data;


/***
 * @author oudi
 */
@Data
public class GradeInfo {
    private Integer gradeId;
    private String name;
    private Integer state;
    private Integer orderNo;
    private Long createTime;
}
