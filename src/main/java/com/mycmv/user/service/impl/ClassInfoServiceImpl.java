package com.mycmv.user.service.impl;

import com.mycmv.user.mapper.user.ClassInfoMapper;
import com.mycmv.user.model.entry.ClassInfo;
import com.mycmv.user.service.ClassInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/***
 * @author a
 */
@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Resource
    private ClassInfoMapper classInfoMapper;

    @Override
    public List<ClassInfo> list() {
        return classInfoMapper.list();
    }

    @Override
    public ClassInfo findById(int id) {
        return classInfoMapper.findById(id);
    }

    @Override
    public void insert(ClassInfo areaInfo) {
        classInfoMapper.insert(areaInfo);
    }

    @Override
    public void update(ClassInfo areaInfo) {
        classInfoMapper.update(areaInfo);
    }

    @Override
    public void batchInsert(List<ClassInfo> list) {
        classInfoMapper.batchInsert(list);
    }

    @Override
    public void delete(int id) {
        classInfoMapper.delete(id);
    }
}
