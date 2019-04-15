package com.swan.crowdfunding.component.service.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.swan.crowdfunding.entity.UserDO;

public interface UserService {

    // 根据管理员名字查询信息
    UserDO getUserDOByAccount(String userAccount);

    // 分页操作
    PageInfo<UserDO> getUserDOPageInfo(Integer pageNum,Integer pageSize,String keyword);

    void deleteUsersByIdList(List<Integer> userIdList);

    List<UserDO> getUsersByIdList(List<Integer> userIdList);

    //UserDO getUserByLoginAccount(String loginAccount);

    void insertUser(UserDO userDO);

    UserDO getUserById(Integer id);

    void editUser(UserDO userDO); 
    
    int getUserByLoginAccount(String loginAccount);
}
