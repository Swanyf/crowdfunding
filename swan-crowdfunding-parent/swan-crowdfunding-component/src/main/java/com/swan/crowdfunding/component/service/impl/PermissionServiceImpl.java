package com.swan.crowdfunding.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swan.crowdfunding.component.mapper.PermissionDOMapper;
import com.swan.crowdfunding.component.service.api.PermissionService;
import com.swan.crowdfunding.entity.PermissionDO;

@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionDOMapper permissionDOMapper; 
    
    @Override
    public List<PermissionDO> getTreeList() {
        return permissionDOMapper.selectAll();
    }

    @Override
    public void addPermission(PermissionDO permission) {
        permissionDOMapper.insert(permission);
    }

    @Override
    public void delPermissionById(Integer id) {
        permissionDOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PermissionDO getPermissionById(Integer id) {
       return permissionDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public void editPermission(PermissionDO permissionDO) {
       permissionDOMapper.updateByPrimaryKey(permissionDO);
    }

    // 根据roleId查询已分配的许可的id-list
    @Override
    public List<Integer> getPermissionIdListByRoleId(Integer roleId) {
        return permissionDOMapper.getPermissionIdListByRoleId(roleId);
    }

    @Override
    public void giveRoleIdAssignPermissignIdList(Integer roleId, List<Integer> permissionIdList) {
        // 先删除旧数据的许可信息
        permissionDOMapper.delOldPermissionListByRoleId(roleId);
        
        // 判断提交的许可信息是否为空
        if (permissionIdList != null && permissionIdList.size() > 0) {
            // 再保存新的许可信息
            permissionDOMapper.insertNewPermission(roleId,permissionIdList);
        }
    }

    @Override
    public List<PermissionDO> getPermissionsByUserId(Integer userId) {
        
        return permissionDOMapper.getPermissionsByUserId(userId);
    }
    
    
}
