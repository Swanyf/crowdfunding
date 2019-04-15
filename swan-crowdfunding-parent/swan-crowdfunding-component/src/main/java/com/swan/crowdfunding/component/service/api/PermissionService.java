package com.swan.crowdfunding.component.service.api;

import java.util.List;

import com.swan.crowdfunding.entity.PermissionDO;

public interface PermissionService {

    List<PermissionDO> getTreeList();

    void addPermission(PermissionDO permission);

    void delPermissionById(Integer id);

    PermissionDO getPermissionById(Integer id);

    void editPermission(PermissionDO permissionDO);

    List<Integer> getPermissionIdListByRoleId(Integer roleId);

    void giveRoleIdAssignPermissignIdList(Integer roleId, List<Integer> permissionIdList);

    List<PermissionDO> getPermissionsByUserId(Integer userId);

}
