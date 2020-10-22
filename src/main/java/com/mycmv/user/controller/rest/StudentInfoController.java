package com.mycmv.user.controller.rest;


import com.mycmv.user.constants.LogConstants;
import com.mycmv.user.exception.BusinessException;
import com.mycmv.user.model.ResponseObject;
import com.mycmv.user.model.config.UserTypeEnum;
import com.mycmv.user.model.entry.AreaInfo;
import com.mycmv.user.model.entry.StudentInfo;
import com.mycmv.user.model.vo.LongIdListVo;
import com.mycmv.user.model.vo.StudentInfoVo;
import com.mycmv.user.service.AreaInfoService;
import com.mycmv.user.service.StudentInfoService;
import com.mycmv.user.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static com.mycmv.user.constants.UserConstants.*;

/***
 * student
 * @author a
 */
@RestController
@RequestMapping("student")
public class StudentInfoController {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.STU_LOG);

    @Resource
    private StudentInfoService studentInfoService;
    @Resource
    private AreaInfoService areaInfoService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list(StudentInfo studentInfo, int pageIndex, int pageSize) {
        logger.info("分页获取学生列表");
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, studentInfoService.list(studentInfo, pageIndex, pageSize));
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Long id) {
        ResponseObject resObj = new ResponseObject();
        StudentInfo studentInfo = studentInfoService.findById(id);
        if (ObjectUtils.isEmpty(studentInfo)) {
            throw new BusinessException(501, "编号 " + id + " 对应的学生信息不存在");
        }
        StudentInfoVo studentInfoVo = new StudentInfoVo();
        BeanUtils.copyProperties(studentInfo, studentInfoVo);
        List<AreaInfo> areaInfoList = areaInfoService.pathListByCode(studentInfo.getAreaId());
        if (!ObjectUtils.isEmpty(areaInfoList.get(ZERO))) {
            studentInfoVo.setProvince(areaInfoList.get(ZERO).getAreaCode());
        }
        if (!ObjectUtils.isEmpty(areaInfoList.get(ONE))) {
            studentInfoVo.setCity(areaInfoList.get(ONE).getAreaCode());
        }
        if (!ObjectUtils.isEmpty(areaInfoList.get(TWO))) {
            studentInfoVo.setTown(areaInfoList.get(TWO).getAreaCode());
        }
        CommonUtils.executeSuccess(resObj, studentInfoVo);
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject add(@RequestBody StudentInfo studentInfo) {
        ResponseObject resObj = new ResponseObject();
        studentInfo.setPassWord(DEFAULT_PWD);
        studentInfoService.insert(studentInfo, UserTypeEnum.STUDENT);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@RequestBody StudentInfo studentInfo) {
        ResponseObject resObj = new ResponseObject();
        studentInfoService.edit(studentInfo, UserTypeEnum.STUDENT);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

    @ResponseBody
    @PostMapping("remove")
    public ResponseObject remove(@RequestBody LongIdListVo longIdListVo) {
        ResponseObject resObj = new ResponseObject();
        if (CollectionUtils.isEmpty(longIdListVo.getIds())) {
            longIdListVo.setIds(Collections.singletonList(longIdListVo.getId()));
        }
        CommonUtils.executeSuccess(resObj, studentInfoService.delete(longIdListVo.getIds()));
        return resObj;
    }
}
