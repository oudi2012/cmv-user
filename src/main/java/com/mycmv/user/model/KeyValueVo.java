package com.mycmv.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * @author a
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyValueVo {
    private Long code;
    private String name;
}
