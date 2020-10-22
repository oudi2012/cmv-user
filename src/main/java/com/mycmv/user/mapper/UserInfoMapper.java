package com.mycmv.user.mapper;

import com.mycmv.user.model.AbstractUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * @author a
 * @param <T>
 */
@Mapper
public interface UserInfoMapper<T extends AbstractUser> {

    /***
     * 列表
     * @param item item
     * @return List
     */
    List<T> list(T item);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    T findById(Long id);

    /***
     * 详细
     * @param userName userName
     * @return obj
     */
    T findByUserName(String userName);

    /***
     * 列表
     * @param userNameList userNameList
     * @return obj
     */
    List<T> listByUserNames(@Param("userNames")List<String> userNameList);

    /***
     * 列表
     * @param phones phones
     * @return obj
     */
    List<T> listByPhones(@Param("phones")List<String> phones);

    /***
     * 详细
     * @param phone phone
     * @return obj
     */
    T findByPhone(String phone);

    /***
     * 详细
     * @param item item
     * @return obj
     */
    T findOne(T item);

    /***
     * 列表
     * @param ids ids
     * @return obj
     */
    List<T> findByIds(@Param("listIds") List<Long> ids);

    /***
     * 添加
     * @param item item
     */
    void insert(T item);

    /***
     * 更新
     * @param item item
     */
    void update(T item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<T> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(@Param("idList") List<Long> idList);

}
