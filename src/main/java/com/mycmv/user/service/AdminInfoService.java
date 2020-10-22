package com.mycmv.user.service;

import com.mycmv.user.model.entry.AdminInfo;

import java.util.List;
import java.util.Map;

/***
 * AdminInfoService
 * @author a
 */
public interface AdminInfoService extends UserInfoService<AdminInfo> {


    /***
     * 列表
     * @param list list
     * @return Map
     */
    Map<Long, AdminInfo> mapByUserIdList(List<Long> list);

}
