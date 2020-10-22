package com.mycmv.user.service;


import com.github.pagehelper.PageInfo;
import com.mycmv.user.model.AbstractUser;
import com.mycmv.user.model.config.UserTypeEnum;

import java.util.List;

/***
 * 用户接口
 * @author oudi
 */
public interface UserInfoService<T extends AbstractUser> {

    /***
     * 列表
     * @param item item
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return PageInfo
     */
    PageInfo<T> list(T item, int pageIndex, int pageSize);

    /***
     * 详细
     * @param t t
     * @return T
     */
    T findOne(T t);

    /***
     * 详细
     * @param id areaCode
     * @return T
     */
    T findById(Long id);

    /***
     * 详细
     * @param phone phone
     * @return T
     */
    T findByPhone(String phone);

    /***
     * 详细
     * @param userName userName
     * @return T
     */
    T findByUserName(String userName);


    /***
     * 列表
     * @param userNameList userNameList
     * @return obj
     */
    List<T> listByUserNames(List<String> userNameList);

    /***
     * 列表
     * @param phones phones
     * @return obj
     */
    List<T> listByPhones(List<String> phones);


    /***
     * 详细
     * @param ids ids
     * @return List
     */
    List<T> findByIds(List<Long> ids);

    /***
     * 编辑
     * @param item item
     */
    void edit(T item, UserTypeEnum userTypeEnum);

    /***
     * 添加
     * @param item item
     */
    void insert(T item, UserTypeEnum userTypeEnum);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<T> list, UserTypeEnum userTypeEnum);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(List<Long> idList);

}
