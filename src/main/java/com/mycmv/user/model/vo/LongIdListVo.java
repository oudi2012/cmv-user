package com.mycmv.user.model.vo;

import lombok.Data;

import java.util.List;

/***
 * 编号参数
 * @author a
 */
@Data
public class LongIdListVo {
    private Long id;
    private List<Long> ids;
}
