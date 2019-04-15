package com.swan.crowdfunding.component.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.swan.crowdfunding.entity.PermissionDO;

public interface PermissionDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionDO record);

    PermissionDO selectByPrimaryKey(Integer id);

    List<PermissionDO> selectAll();

    int updateByPrimaryKey(PermissionDO record);

    List<Integer> getPermissionIdListByRoleId(Integer roleId);

    void insertNewPermission(@Param("roleId")Integer roleId, @Param("permissionIdList")List<Integer> permissionIdList);

    void delOldPermissionListByRoleId(Integer roleId);

    List<PermissionDO> getPermissionsByUserId(Integer userId);
}