package com.mycmv.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mycmv.user.constants.LogConstants;
import com.mycmv.user.exception.BusinessException;
import com.mycmv.user.mapper.UserInfoMapper;
import com.mycmv.user.model.AbstractUser;
import com.mycmv.user.model.config.UserTypeEnum;
import com.mycmv.user.model.entry.AdminInfo;
import com.mycmv.user.model.entry.StudentInfo;
import com.mycmv.user.service.UserInfoService;
import com.mycmv.user.utils.CmvDesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/***
 * @author a
 * @param <T>
 */
public abstract class AbstractUserInfoService<T extends AbstractUser> implements UserInfoService<T> {

    private static final Logger logger = LoggerFactory.getLogger(LogConstants.ADM_LOG);

    private static final String LOG_LIST_PARAM = "list param {},{},{}";
    private static final String LOG_LIST_RESULT = "list result {}";
    private static final String LOG_FIND_ID = "findById param {}";
    private static final String LOG_FIND_PARAM = "findOne param {}";
    private static final String LOG_FIND_IDS = "findByIds param {}";
    private static final String LOG_INSERT = "insert param {}";
    private static final String LOG_INSERT_LIST = "batchInsert param {}";
    private static final String LOG_EDIT = "edit param {}";
    private static final String LOG_REMOVE = "delete param {}";

    /***
     * getUserInfoMapper
     * @return UserInfoMapper
     */
    public abstract UserInfoMapper<T> getUserInfoMapper();

    @Override
    public PageInfo<T> list(T t, int pageIndex, int pageSize) {
        logger.info(LOG_LIST_PARAM, JSON.toJSONString(t), pageIndex, pageSize);
        PageHelper.startPage(pageIndex, pageSize).setOrderBy(" id desc ");
        List<T> list = getUserInfoMapper().list(t);
        logger.info(LOG_LIST_RESULT, JSON.toJSONString(list));
        if (CollectionUtils.isEmpty(list)) {
            return new PageInfo<>();
        }
        return new PageInfo<>(list);
    }

    @Override
    public T findOne(T t) {
        logger.info(LOG_FIND_PARAM, JSON.toJSONString(t));
        return getUserInfoMapper().findOne(t);
    }

    @Override
    public T findByPhone(String phone) {
        return getUserInfoMapper().findByPhone(phone);
    }

    @Override
    public T findByUserName(String userName) {
        return getUserInfoMapper().findByUserName(userName);
    }

    @Override
    public List<T> listByUserNames(List<String> userNameList) {
        return getUserInfoMapper().listByUserNames(userNameList);
    }

    @Override
    public List<T> listByPhones(List<String> phones) {
        return getUserInfoMapper().listByPhones(phones);
    }

    @Override
    public T findById(Long id) {
        logger.info(LOG_FIND_ID, id);
        return getUserInfoMapper().findById(id);
    }

    @Override
    public List<T> findByIds(List<Long> ids) {
        logger.info(LOG_FIND_IDS, JSON.toJSONString(ids));
        return getUserInfoMapper().findByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void edit(T item, UserTypeEnum userTypeEnum) {
        logger.info(LOG_EDIT, JSON.toJSONString(item));
        getUserInfoMapper().update(item);
    }

    @Override
    public void insert(T item, UserTypeEnum userTypeEnum) {
        logger.info(LOG_INSERT, JSON.toJSONString(item));
        if (!StringUtils.isEmpty(item.getUserName())) {
            if (!ObjectUtils.isEmpty(findByUserName(item.getUserName()))) {
                throw new BusinessException(501, "用户名" + item.getUserName() + "已经注册");
            }
        }
        item.setPassWord(CmvDesUtils.encrypt(item.getPassWord()));
        getUserInfoMapper().insert(item);
    }

    @Override
    public void batchInsert(List<T> list, UserTypeEnum userTypeEnum) {
        logger.info(LOG_INSERT_LIST, JSON.toJSONString(list));
        if (userTypeEnum != UserTypeEnum.ADMIN) {
            List<String> phoneList = list.stream().filter(a -> a.getPhone() != null).map(AbstractUser::getPhone).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(phoneList)) {
                List<T> existItem = this.listByPhones(phoneList);
                if (!CollectionUtils.isEmpty(existItem)) {
                    StringBuilder sbInfo = new StringBuilder();
                    sbInfo.append("手机号：");
                    existItem.forEach(item -> {
                        sbInfo.append(item.getPhone()).append(",");
                    });
                    sbInfo.deleteCharAt(sbInfo.length() - 1);
                    sbInfo.append("已经注册");
                    throw new BusinessException(501, sbInfo.toString());
                }
            }
        }
        List<String> userNameList = list.stream().filter(a -> a.getUserName() != null).map(AbstractUser::getUserName).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(userNameList)) {
            List<T> existItem = this.listByUserNames(userNameList);
            if (!CollectionUtils.isEmpty(existItem)) {
                StringBuilder sbInfo = new StringBuilder();
                sbInfo.append("用户名：");
                existItem.forEach(item -> {
                    sbInfo.append(item.getUserName()).append(",");
                });
                sbInfo.deleteCharAt(sbInfo.length() - 1);
                sbInfo.append("已经注册");
                throw new BusinessException(501, sbInfo.toString());
            }
        }
        list.forEach(item -> {
            item.setPassWord(CmvDesUtils.encrypt(item.getPassWord()));
        });
        getUserInfoMapper().batchInsert(list);
    }

    @Override
    public int delete(List<Long> idList) {
        logger.info(LOG_REMOVE, JSON.toJSONString(idList));
        return getUserInfoMapper().delete(idList);
    }
}
