package com.swan.crowdfunding.component.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.swan.crowdfunding.entity.RoleDO;

public interface RoleDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleDO record);

    RoleDO selectByPrimaryKey(Integer id);

    List<RoleDO> selectAll();

    int updateByPrimaryKey(RoleDO record);
    
    List<RoleDO> getAssignList(Integer userId);
                 
    List<RoleDO> getUnAssignList(Integer userId);

    void delOldAssignRoleList(Integer userId);

    void insertAssignRoleList(@Param("userId")Integer userId, @Param("assignRoleIdList") List<Integer> assignRoleIdList);

    RoleDO getRoleByName(String name);

    List<RoleDO> getPermissionsByKeyword(String keyword);
 
}