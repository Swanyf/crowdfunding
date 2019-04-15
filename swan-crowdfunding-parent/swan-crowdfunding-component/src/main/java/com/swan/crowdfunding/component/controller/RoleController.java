package com.swan.crowdfunding.component.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.swan.crowdfunding.component.service.api.RoleService;
import com.swan.crowdfunding.constant.DefaultPage;
import com.swan.crowdfunding.entity.ResultVO;
import com.swan.crowdfunding.entity.RoleDO;

@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("get/role/list")
    public String getRoleList(Model model) {
        List<RoleDO> roleList = roleService.getRoleList();
        model.addAttribute("roleList", roleList);
        return "role/list";
    }

    // 获取分页的数据
    @ResponseBody
    @RequestMapping("get/role/list/page/info")
    public ResultVO<PageInfo<RoleDO>> getRoleListPageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        PageInfo<RoleDO> pageInfo = roleService.getRoleListPageInfo(pageNum, DefaultPage.PAGE_SIZE, keyword);
        return new ResultVO<PageInfo<RoleDO>>(ResultVO.SUCCESS, pageInfo, ResultVO.NO_MSG);
    }

    // 检查用户是否存在
    @ResponseBody
    @RequestMapping("/get/role/by/name/{name}")
    public ResultVO<String> getRoleByName(@PathVariable("name") String name) {

        RoleDO roleDO = roleService.getRoleByName(name);
        if (roleDO == null) {
            // 不存在，角色名可用
            return new ResultVO<String>(ResultVO.SUCCESS, ResultVO.NO_DATA, ResultVO.NO_MSG);
        } else {
            // 用户名存在
            return new ResultVO<String>(ResultVO.FAILED, ResultVO.NO_DATA, ResultVO.NO_MSG);
        }
    }

    // 添加
    @ResponseBody
    @RequestMapping("/add/role")
    public ResultVO<String> addRole(RoleDO roleDO){
        roleService.addRole(roleDO);
        return new ResultVO<String>(ResultVO.SUCCESS, ResultVO.NO_DATA, ResultVO.NO_MSG);
    }
    
    // 根据id查询role
    @ResponseBody
    @RequestMapping("/get/role/by/id/{id}")
    public ResultVO<RoleDO> getRoleById(@PathVariable("id") Integer id){
        RoleDO role = roleService.getRoleById(id);
        return new ResultVO<RoleDO>(ResultVO.SUCCESS, role , ResultVO.NO_MSG);
    }
    
    // 单行删除
    @ResponseBody
    @RequestMapping("/del/role/by/id/{id}")
    public ResultVO<String> delRoleById(@PathVariable("id") Integer id){
        roleService.delRoleById(id);
        return new ResultVO<String>(ResultVO.SUCCESS, ResultVO.NO_DATA, ResultVO.NO_MSG);
    }
    
    // 修改
    @ResponseBody
    @RequestMapping("/modify/role")
    public ResultVO<String> modify(RoleDO role){
        roleService.modifyRole(role);
        return new ResultVO<String>(ResultVO.SUCCESS, ResultVO.NO_DATA, ResultVO.NO_MSG);
    }
}
