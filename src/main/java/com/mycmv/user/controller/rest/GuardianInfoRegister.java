package com.mycmv.user.controller.rest;


import com.google.common.base.Preconditions;
import com.mycmv.user.model.ResponseObject;
import com.mycmv.user.model.config.UserTypeEnum;
import com.mycmv.user.model.entry.GuardianInfo;
import com.mycmv.user.model.vo.GuardianInfoListVo;
import com.mycmv.user.service.GuardianInfoService;
import com.mycmv.user.utils.CommonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * guardian
 * @author a
 */
@RestController
@RequestMapping("register/guardian")
public class GuardianInfoRegister {

    @Resource
    private GuardianInfoService guardianInfoService;


    @ResponseBody
    @PostMapping
    public ResponseObject add(@RequestBody GuardianInfo guardianInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(guardianInfo, "用户信息不能为空");
        guardianInfoService.insert(guardianInfo, UserTypeEnum.GUARDIAN);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }


    @ResponseBody
    @PostMapping("list")
    public ResponseObject addList(@RequestBody GuardianInfoListVo guardianInfo) {
        ResponseObject resObj = new ResponseObject();
        Preconditions.checkNotNull(guardianInfo, "用户信息不能为空");
        guardianInfoService.batchInsert(guardianInfo.getGuardianInfoList(), UserTypeEnum.GUARDIAN);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }


}
