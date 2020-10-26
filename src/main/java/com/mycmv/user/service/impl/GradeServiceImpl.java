package com.mycmv.user.service.impl;

import com.mycmv.user.mapper.user.GradeInfoMapper;
import com.mycmv.user.model.entry.GradeInfo;
import com.mycmv.user.service.GradeInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author wanghaikuo
 * @Date 2020/7/6
 **/
@Service
public class GradeServiceImpl implements GradeInfoService {

    @Resource
    private GradeInfoMapper gradeInfoMapper;

    @Override
    public List<GradeInfo> list() {
        return gradeInfoMapper.list();
    }

    @Override
    public GradeInfo findById(int id) {
        return gradeInfoMapper.findById(id);
    }

    @Override
    public List<GradeInfo> findByIds(List<Integer> ids) {
        return gradeInfoMapper.findByIds(ids);
    }

    @Override
    public Map<Integer, GradeInfo> findMapByIds(List<Integer> ids) {
        return gradeInfoMapper.findByIds(ids).stream().collect(Collectors.toMap(GradeInfo::getGradeId, Function.identity()));
    }

    @Override
    public void insert(GradeInfo itemInfo) {
        gradeInfoMapper.insert(itemInfo);
    }

    @Override
    public void update(GradeInfo itemInfo) {
        gradeInfoMapper.update(itemInfo);
    }

    @Override
    public void batchInsert(List<GradeInfo> list) {
        gradeInfoMapper.batchInsert(list);
    }

    @Override
    public void delete(int id) {
        gradeInfoMapper.delete(id);
    }
}
