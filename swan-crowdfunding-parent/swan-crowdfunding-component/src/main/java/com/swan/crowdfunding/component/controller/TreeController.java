package com.swan.crowdfunding.component.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swan.crowdfunding.component.service.api.PermissionService;
import com.swan.crowdfunding.entity.PermissionDO;
import com.swan.crowdfunding.entity.ResultVO;

@Controller
public class TreeController {

    @Autowired
    PermissionService permissionService;
    
    @ResponseBody
    @RequestMapping("/get/tree/list")
    public ResultVO<PermissionDO> getTreeList(){
        // 声明一个变量,用来存储根节点的全部数据
        PermissionDO root = null;
        // 查询全部的节点数据
        List<PermissionDO> treeList = permissionService.getTreeList();
        
        // 根据主键的Id获取PermissionDO
        Map<Integer,PermissionDO> permissionMap = new HashMap<>();        
        
        // 遍历全部的节点数据
        for (PermissionDO permissionDO : treeList) {
            // 获取主键id
            Integer id = permissionDO.getId();
            // 根据主键id查询每一个节点对象信息
            permissionMap.put(id, permissionDO);
        }
        
        for (PermissionDO permissionDO : treeList) {
            // 获取pId
            Integer pid = permissionDO.getPid();
            // 判断pId是否为空，为空就是根节点
            if (pid == null) {
                root = permissionDO;
            } else {
                // 根据pid获取存放在map中的父节点
                PermissionDO parent = permissionMap.get(pid);
                // 将子节点存放的父节点中
                parent.getChildren().add(permissionDO);
            }
        }
        return new ResultVO<PermissionDO>(ResultVO.SUCCESS, root, ResultVO.NO_MSG);
    }
    
    // 添加节点
    @ResponseBody
    @RequestMapping("/add/permission")
    public ResultVO<String> addPermission(PermissionDO permission){
        permissionService.addPermission(permission);
        return new ResultVO<String>(ResultVO.SUCCESS, ResultVO.NO_DATA, ResultVO.NO_MSG);
    }
    
    // 根据id查询permission对象
    @ResponseBody
    @RequestMapping("/get/permission/by/{permissionId}")
    public ResultVO<PermissionDO> getPermissionById(@PathVariable("permissionId") Integer id){
        PermissionDO permission = permissionService.getPermissionById(id);
        return new ResultVO<PermissionDO>(ResultVO.SUCCESS, permission, ResultVO.NO_MSG);
    }
    
    // 根据id删除permission对象
    @ResponseBody
    @RequestMapping("/del/permission/by/{permissionId}")
    public ResultVO<String> delPermissionById(@PathVariable("permissionId") Integer id){
        permissionService.delPermissionById(id);
        return new ResultVO<String>(ResultVO.SUCCESS, ResultVO.NO_DATA, ResultVO.NO_MSG);
    }
    
    // 修改
    @ResponseBody
    @RequestMapping("/edit/permission")
    public ResultVO<String> editPermission(PermissionDO permissionDO){
        permissionService.editPermission(permissionDO);
        return new ResultVO<String>(ResultVO.SUCCESS, ResultVO.NO_DATA, ResultVO.NO_MSG);
    }
}


