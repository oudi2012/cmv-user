package com.mycmv.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.mycmv.user.constants.LogConstants;
import com.mycmv.user.constants.UserConstants;
import com.mycmv.user.mapper.user.TeacherInfoMapper;
import com.mycmv.user.mapper.UserInfoMapper;
import com.mycmv.user.model.entry.AreaInfo;
import com.mycmv.user.model.entry.SchoolInfo;
import com.mycmv.user.model.entry.TeacherInfo;
import com.mycmv.user.model.vo.TeacherInfoVo;
import com.mycmv.user.service.AreaInfoService;
import com.mycmv.user.service.SchoolInfoService;
import com.mycmv.user.service.TeacherInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * TeacherInfoService
 * @author a
 */
@Service
public class TeacherInfoServiceImpl extends AbstractUserInfoService<TeacherInfo> implements TeacherInfoService {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.TEACH_LOG);

    @Resource
    private TeacherInfoMapper teacherInfoMapper;
    @Resource
    private AreaInfoService areaInfoService;
    @Resource
    private SchoolInfoService schoolInfoService;

    @Override
    public UserInfoMapper<TeacherInfo> getUserInfoMapper() {
        return teacherInfoMapper;
    }

    @Override
    public TeacherInfoVo itemToVo(TeacherInfo teacherInfo) {
        logger.info("itemToVo param {}", JSON.toJSONString(teacherInfo));
        TeacherInfoVo teacherInfoVo = new TeacherInfoVo();
        BeanUtils.copyProperties(teacherInfo, teacherInfoVo);
        List<Integer> areaIdList = areaInfoService.cutAreaCode(teacherInfo.getAreaId());
        Map<Integer, AreaInfo> areaInfoMap = areaInfoService.pathListByCode(areaIdList);
        setVoAreaInfo(teacherInfoVo, areaInfoMap);
        return teacherInfoVo;
    }

    @Override
    public List<TeacherInfoVo> listToVo(List<TeacherInfo> teacherInfoList) {
        List<Integer> areaCodeList = teacherInfoList.stream().map(TeacherInfo::getAreaId).collect(Collectors.toList());
        List<Long> schoolIds = teacherInfoList.stream().map(TeacherInfo::getSchoolId).collect(Collectors.toList());
        Map<Long, SchoolInfo> schoolInfoMap = new HashMap<>(24);
        if (!CollectionUtils.isEmpty(schoolIds)) {
            schoolInfoMap = schoolInfoService.findMapByIds(schoolIds);
        }
        Map<Integer, AreaInfo> areaInfoMap = areaInfoService.pathListByCode(areaCodeList);
        List<TeacherInfoVo> teacherInfoVoList = new ArrayList<>();
        //需要完全获取到值后才能使用
        Map<Long, SchoolInfo> finalSchoolInfoMap = schoolInfoMap;
        teacherInfoList.forEach(teacherItem -> {
            TeacherInfoVo teacherInfoVo = new TeacherInfoVo();
            BeanUtils.copyProperties(teacherItem, teacherInfoVo);
            setVoAreaInfo(teacherInfoVo, areaInfoMap);
            if (finalSchoolInfoMap.containsKey(teacherInfoVo.getSchoolId())) {
                teacherInfoVo.setSchoolName(finalSchoolInfoMap.get(teacherInfoVo.getSchoolId()).getName());
            }
            teacherInfoVoList.add(teacherInfoVo);
        });
        return teacherInfoVoList;
    }

    @Override
    public TeacherInfoVo findVoById(Long id) {
        logger.info("findVoById param {}", id);
        TeacherInfo teacherInfo = teacherInfoMapper.findById(id);
        if (ObjectUtils.isEmpty(teacherInfo)) {
            return null;
        }
        return itemToVo(teacherInfo);
    }

    private void setVoAreaInfo(TeacherInfoVo teacherInfoVo, Map<Integer, AreaInfo> areaInfoMap) {
        List<Integer> areaIdList = areaInfoService.cutAreaCode(teacherInfoVo.getAreaId());
        areaIdList.forEach(areaId -> {
            if (areaInfoMap.containsKey(areaId)) {
                if (areaId/ UserConstants.ONE_OO_OO_OO > 0) {
                    teacherInfoVo.setTownCode(areaInfoMap.get(areaId).getAreaCode());
                    teacherInfoVo.setTownName(areaInfoMap.get(areaId).getAreaName());
                } else if (areaId/ UserConstants.ONE_OO_OO > 0) {
                    teacherInfoVo.setCityCode(areaInfoMap.get(areaId).getAreaCode());
                    teacherInfoVo.setCityName(areaInfoMap.get(areaId).getAreaName());
                } else {
                    teacherInfoVo.setProvinceCode(areaInfoMap.get(areaId).getAreaCode());
                    teacherInfoVo.setProvinceName(areaInfoMap.get(areaId).getAreaName());
                }
            }
        });
    }
}
