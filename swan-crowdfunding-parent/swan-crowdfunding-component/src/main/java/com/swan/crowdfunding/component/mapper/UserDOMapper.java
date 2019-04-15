package com.swan.crowdfunding.component.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.swan.crowdfunding.entity.UserDO;

public interface UserDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    UserDO selectByPrimaryKey(Integer id);

    List<UserDO> selectAll();

    int updateByPrimaryKey(UserDO record);

    UserDO getUserDOByAccount(String userAccount);
    
    // 模糊查询得到分页的所有数据
    List<UserDO> selectForSearch(String keyword);

    void deleteUsersByIdList(@Param("userIdList") List<Integer> userIdList);

    List<UserDO> getUsersByIdList(@Param("userIdList") List<Integer> userIdList);

    // 根据账号名称查询用户的个数
    int getUserByLoginAccount(String loginAccount);
}