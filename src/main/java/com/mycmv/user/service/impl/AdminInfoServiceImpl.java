package com.mycmv.user.service.impl;

import com.mycmv.user.mapper.user.AdminInfoMapper;
import com.mycmv.user.mapper.UserInfoMapper;
import com.mycmv.user.model.entry.AdminInfo;
import com.mycmv.user.service.AdminInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/***
 * AdminInfoService
 * @author a
 */
@Service
public class AdminInfoServiceImpl extends AbstractUserInfoService<AdminInfo> implements AdminInfoService {

    @Resource
    private AdminInfoMapper adminInfoMapper;

    @Override
    public UserInfoMapper<AdminInfo> getUserInfoMapper() {
        return adminInfoMapper;
    }

    @Override
    public Map<Long, AdminInfo> mapByUserIdList(List<Long> list) {
        List<AdminInfo> adminInfoList = this.findByIds(list);
        if (CollectionUtils.isEmpty(adminInfoList)) {
            return new HashMap<>(0);
        }
        return adminInfoList.stream().collect(Collectors.toMap(AdminInfo::getId, Function.identity()));
    }
}
