package com.mycmv.user.controller.rest;

import com.mycmv.user.model.ResponseObject;
import com.mycmv.user.model.entry.GradeInfo;
import com.mycmv.user.service.GradeInfoService;
import com.mycmv.user.utils.CommonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/***
 * 班级
 * @author a
 */
@RestController
@RequestMapping("class")
public class GradeInfoController {

    @Resource
    private GradeInfoService classInfoService;

    @ResponseBody
    @GetMapping("list")
    public ResponseObject list() {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, classInfoService.list());
        return resObj;
    }

    @ResponseBody
    @PostMapping("create")
    public ResponseObject create(@RequestBody GradeInfo classInfo) {
        ResponseObject resObj = new ResponseObject();
        classInfoService.insert(classInfo);
        CommonUtils.executeSuccess(resObj, classInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("findById")
    public ResponseObject findById(Integer gradeId) {
        ResponseObject resObj = new ResponseObject();
        GradeInfo classInfo = classInfoService.findById(gradeId);
        CommonUtils.executeSuccess(resObj, classInfo);
        return resObj;
    }

    @ResponseBody
    @PostMapping("edit")
    public ResponseObject edit(@RequestBody GradeInfo gradeInfo) {
        ResponseObject resObj = new ResponseObject();
        classInfoService.update(gradeInfo);
        CommonUtils.executeSuccess(resObj, gradeInfo);
        return resObj;
    }

    @ResponseBody
    @GetMapping("remove")
    public ResponseObject remove(Integer id) {
        ResponseObject resObj = new ResponseObject();
        classInfoService.delete(id);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }

}
