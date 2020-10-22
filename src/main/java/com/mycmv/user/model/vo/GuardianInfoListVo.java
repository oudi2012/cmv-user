package com.mycmv.user.model.vo;

import com.mycmv.user.model.entry.GuardianInfo;
import lombok.Data;

import java.util.List;

/***
 * @author a
 */
@Data
public class GuardianInfoListVo {
    private List<GuardianInfo> guardianInfoList;
}
