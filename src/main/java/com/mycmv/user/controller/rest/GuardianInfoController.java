package com.mycmv.user.controller.rest;


import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.mycmv.user.constants.LogConstants;
import com.mycmv.user.model.ResponseObject;
import com.mycmv.user.model.config.GuardianRoleEnum;
import com.mycmv.user.model.config.UserTypeEnum;
import com.mycmv.user.model.entry.AreaInfo;
import com.mycmv.user.model.entry.GuardianInfo;
import com.mycmv.user.model.vo.GuardianInfoVo;
import com.mycmv.user.model.vo.GuardianStudentVo;
import com.mycmv.user.model.vo.LongIdListVo;
import com.mycmv.user.service.AreaInfoService;
import com.mycmv.user.service.GuardianInfoService;
import com.mycmv.user.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mycmv.user.constants.UserConstants.*;

/***
 * guardian
 * @author a
 */
@RestController
@RequestMapping("guardian")
public class GuardianInfoController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);

    @Resource
    private GuardianInfoService guardianInfoService;
    @Resource
    private AreaInfoService areaInfoService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(GuardianInfo guardianInfo, int pageIndex, int pageSize) {
        logger.info("调用接口 guardian => list");
        ResponseObject resObj = new ResponseObject();
        PageInfo<GuardianInfo> guardianInfoPageInfo = guardianInfoService.list(guardianInfo, pageIndex, pageSize);
        if (!CollectionUtils.isEmpty(guardianInfoPageInfo.getList())) {
            PageInfo<GuardianInfoVo> guardianInfoVoPageInfo = new PageInfo<>();
            List<GuardianInfoVo> guardianInfoVoList = new ArrayList<>();
            guardianInfoPageInfo.getList().forEach(guardianItem -> {
                GuardianInfoVo guardianInfoVo = new GuardianInfoVo();
                BeanUtils.copyProperties(guardianItem, guardianInfoVo);
                if (guardianItem.getRoleId() != null) {
                    GuardianRoleEnum guardianRoleEnum = GuardianRoleEnum.getByCode(guardianItem.getRoleId());
                    if (!ObjectUtils.isEmpty(guardianRoleEnum)) {
                        guardianInfoVo.setRoleName(guardianRoleEnum.getName());
                    }
                }
                List<AreaInfo> areaInfoList = areaInfoService.pathListByCode(guardianItem.getAreaId());
                if (!ObjectUtils.isEmpty(areaInfoList.get(ZERO))) {
                    guardianInfoVo.setProvince(areaInfoList.get(ZERO).getAreaCode());
                }
                if (!ObjectUtils.isEmpty(areaInfoList.get(ONE))) {
                    guardianInfoVo.setCity(areaInfoList.get(ONE).getAreaCode());
                }
                if (!ObjectUtils.isEmpty(areaInfoList.get(TWO))) {
                    guardianInfoVo.setTown(areaInfoList.get(TWO).getAreaCode());
                }
                guardianInfoVoList.add(guardianInfoVo);
            });
            guardianInfoVoPageInfo.setList(guardianInfoVoList);
            CommonUtils.executeSuccess(resObj, guardianInfoVoPageInfo);
        } else {
            CommonUtils.executeSuccess(resObj, guardianInfoPageInfo);
        }
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Long id) {
        logger.info("调用接口 guardian => findById");
        ResponseObject resObj = new ResponseObject();
        GuardianInfo guardianInfo = guardianInfoService.findById(id);
        if (!ObjectUtils.isEmpty(guardianInfo)) {
            GuardianInfoVo guardianInfoVo = new GuardianInfoVo();
            BeanUtils.copyProperties(guardianInfo, guardianInfoVo);
            if (guardianInfo.getRoleId() != null) {
                GuardianRoleEnum guardianRoleEnum = GuardianRoleEnum.getByCode(guardianInfo.getRoleId());
                if (!ObjectUtils.isEmpty(guardianRoleEnum)) {
                    guardianInfoVo.setRoleName(guardianRoleEnum.getName());
                }
            }
            List<AreaInfo> areaInfoList = areaInfoService.pathListByCode(guardianInfo.getAreaId());
            if (!ObjectUtils.isEmpty(areaInfoList.get(ZERO))) {
                guardianInfoVo.setProvince(areaInfoList.get(ZERO).getAreaCode());
            }
            if (!ObjectUtils.isEmpty(areaInfoList.get(ONE))) {
                guardianInfoVo.setCity(areaInfoList.get(ONE).getAreaCode());
            }
            if (!ObjectUtils.isEmpty(areaInfoList.get(TWO))) {
                guardianInfoVo.setTown(areaInfoList.get(TWO).getAreaCode());
            }
            CommonUtils.executeSuccess(resObj, guardianInfoVo);
        } else {
            CommonUtils.executeSuccess(resObj, guardianInfo);
        }
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject add(@RequestBody GuardianInfo guardianInfo) {
        logger.info("调用接口 guardian => create");
        ResponseObject resObj = new ResponseObject();
        guardianInfo.setPassWord(DEFAULT_PWD);
        guardianInfoService.insert(guardianInfo, UserTypeEnum.GUARDIAN);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@RequestBody GuardianInfo guardianInfo) {
        logger.info("调用接口 guardian => edit");
        ResponseObject resObj = new ResponseObject();
        guardianInfoService.edit(guardianInfo, UserTypeEnum.GUARDIAN);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

    @ResponseBody
    @PostMapping("remove")
    public ResponseObject remove(@RequestBody LongIdListVo longIdListVo) {
        logger.info("调用接口 guardian => remove");
        Preconditions.checkArgument(!ObjectUtils.isEmpty(longIdListVo), "删除参数不能为空");
        ResponseObject resObj = new ResponseObject();
        if (CollectionUtils.isEmpty(longIdListVo.getIds())) {
            longIdListVo.setIds(Collections.singletonList(longIdListVo.getId()));
        }
        CommonUtils.executeSuccess(resObj, guardianInfoService.delete(longIdListVo.getIds()));
        return resObj;
    }

    @ResponseBody
    @GetMapping("listRoles")
    public ResponseObject listRoles() {
        logger.info("调用接口 guardian => listRoles");
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, GuardianRoleEnum.listItem());
        return resObj;
    }

    /***
     * 绑定监护人
     * @param guardianStudentVo guardianStudentVo
     * @return ResponseObject
     */
    @ResponseBody
    @PostMapping("bind")
    public ResponseObject bind(@RequestBody GuardianStudentVo guardianStudentVo) {
        logger.info("调用接口 guardian => bind");
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, guardianInfoService.bindGuardianInfo(guardianStudentVo));
        return resObj;
    }

}
