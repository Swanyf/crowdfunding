package com.swan.crowdfunding.component.service.api;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.swan.crowdfunding.entity.RoleDO;

@Service
public interface RoleService {

    List<RoleDO> getRoleList();

    // 获取已分配的角色
    List<RoleDO> getAssignList(Integer userId);
    
    // 获取未分配的角色
    List<RoleDO> getUnAssginList(Integer userId);

    void delAssignRoleAndAddAssignRole(Integer userId, List<Integer> assignRoleIdList);

    PageInfo<RoleDO> getRoleListPageInfo(Integer pageNum, Integer pageSize, String keyword);

    RoleDO getRoleByName(String name);

    void addRole(RoleDO roleDO);

    RoleDO getRoleById(Integer id);

    void delRoleById(Integer id);

    void modifyRole(RoleDO role);
}
