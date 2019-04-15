package com.swan.crowdfunding.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swan.crowdfunding.component.mapper.RoleDOMapper;
import com.swan.crowdfunding.component.service.api.RoleService;
import com.swan.crowdfunding.entity.RoleDO;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDOMapper roleDOMapper;
    
    @Override
    public List<RoleDO> getRoleList() {
        return roleDOMapper.selectAll();
    }

    @Override
    public List<RoleDO> getAssignList(Integer userId) {
        return roleDOMapper.getAssignList(userId);
    }

    @Override
    public List<RoleDO> getUnAssginList(Integer userId) {
        return roleDOMapper.getUnAssignList(userId);
    }

    @Override
    public void delAssignRoleAndAddAssignRole(Integer userId, List<Integer> assignRoleIdList) {
        // 先把旧的元素删除
        roleDOMapper.delOldAssignRoleList(userId);
        
        // 有可能没有分配的juese
        if (assignRoleIdList != null && assignRoleIdList.size() > 0) {
            // 再根据用户id添加新分配的角色
            roleDOMapper.insertAssignRoleList(userId,assignRoleIdList);
        }
    }

    // 分页后的数据
    @Override
    public PageInfo<RoleDO> getRoleListPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        // 生成分页的数据
        PageHelper.startPage(pageNum, pageSize);
        // 获取所有的role数据
        List<RoleDO> list = roleDOMapper.getPermissionsByKeyword(keyword);
        // 封装成分页数据
        PageInfo<RoleDO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public RoleDO getRoleByName(String name) {
        return roleDOMapper.getRoleByName(name);
    }

    @Override
    public void addRole(RoleDO roleDO) {
        roleDOMapper.insert(roleDO);
    }

    @Override
    public RoleDO getRoleById(Integer id) {
        return roleDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delRoleById(Integer id) {
        roleDOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void modifyRole(RoleDO role) {
        roleDOMapper.updateByPrimaryKey(role);
    }

}
