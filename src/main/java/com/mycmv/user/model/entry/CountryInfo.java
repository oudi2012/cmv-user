package com.mycmv.user.model.entry;

import lombok.Data;

/***
 * 国家
 * @author a
 */
@Data
public class CountryInfo {
    private int     id;
    private int		code;
    private String	name;
    private int		orderNo;
}
