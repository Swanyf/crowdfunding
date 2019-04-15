package com.swan.crowdfunding.component.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swan.crowdfunding.component.service.api.PermissionService;
import com.swan.crowdfunding.component.service.api.RoleService;
import com.swan.crowdfunding.constant.AttrName;
import com.swan.crowdfunding.entity.PermissionDO;
import com.swan.crowdfunding.entity.ResultVO;
import com.swan.crowdfunding.entity.RoleDO;
import com.swan.crowdfunding.entity.UserDO;

@Controller
public class AssignController {

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    // 获取用户id对应的分配角色和未分配的角色
    @RequestMapping("/assign/role/{userId}")
    public String toAssignRolePage(@PathVariable("userId") Integer userId, Model model) {

        List<RoleDO> assignRoleList = roleService.getAssignList(userId);
        List<RoleDO> unAssignRoleList = roleService.getUnAssginList(userId);

        model.addAttribute("assignList", assignRoleList);
        model.addAttribute("unAssignList", unAssignRoleList);

        // 分配角色页面提交表单时需要携带userId
        model.addAttribute("userId", userId);

        return "assign/role";
    }

    /*
     * required 一般用在做校验判断当前选项内容是否为必填 true 就表示输出时标签必须有内容,否则页面会400报错
     */

    @RequestMapping(value = "/assign/do/assign/role")
    public String assginDoAssign(@RequestParam("userId") Integer userId,
            @RequestParam(value = "assignRoleIdList", required = false) List<Integer> assignRoleIdList) {

        // 执行分配操作
        roleService.delAssignRoleAndAddAssignRole(userId, assignRoleIdList);

        return "user/page";
    }
    
    
    @RequestMapping("assign/to/permission/page/{roleId}")
    public String assignToPermissionPage(@PathVariable("roleId") Integer roleId,Model model) {
        model.addAttribute("roleId", roleId);
        return "assign/permission";
    }

    // 根据树形图结构生成方法
    @ResponseBody
    @RequestMapping("/assign/permission")
    public ResultVO<PermissionDO> assignPermission(@RequestParam("roleId") Integer roleId) {
        // 初始化根节点
        PermissionDO root = null;

        // 查询所有的permission对象数据
        List<PermissionDO> treeList = permissionService.getTreeList();
        // 根据roleId查询已分配的许可数据id
        List<Integer> permissionIdList = permissionService.getPermissionIdListByRoleId(roleId);

        // 创建根据当前id节点,和permission对象的map
        Map<Integer, PermissionDO> map = new HashMap<>();

        // 查询所有的permission对象数据
        for (PermissionDO permission : treeList) {
            // 获取当前节点的id
            Integer id = permission.getId();
            map.put(id, permission);

            // 判断permissionIdList是否有数据
            if(permissionIdList != null && permissionIdList.size() > 0) {
                if (permissionIdList.contains(id)) {
                    // 将已存在的节点对应的id选中
                    permission.setChecked(true);
                }
            }
        }

        // 组装树形结构图
        for (PermissionDO permission : treeList) {
            // 获取pid的节点
            Integer pid = permission.getPid();

            // 判断当前pid的节点是否为空
            if (pid == null) {
                // 设置为根节点
                root = permission;
            } else {
                // 根据pid找到父节点
                PermissionDO parent = map.get(pid);

                // 给父节点的子节点添加元素
                parent.getChildren().add(permission);
            }
        }
 
        return new ResultVO<PermissionDO>(ResultVO.SUCCESS, root, ResultVO.NO_MSG);
    }
    
    // 给角色分配许可数据
    @ResponseBody
    @RequestMapping("/do/assign/permission")
    // 传递过来的是角色id和选中的复选框的许可信息的id
    public ResultVO<String> doAssignPermission(@RequestBody Map<String, List<Integer>> roleIdAndPromessionIdList){
        
        // 根据roleId获取对应的permission对象
        Integer roleId = roleIdAndPromessionIdList.get("roleId").get(0);
        
        List<Integer> permissionIdList = roleIdAndPromessionIdList.get("permissionId");
        
        // 给当前角色的id分配许可数据
        permissionService.giveRoleIdAssignPermissignIdList(roleId,permissionIdList);
        return new ResultVO<String>(ResultVO.SUCCESS, ResultVO.NO_DATA, ResultVO.NO_MSG);
    }

    // 给当前用户的许可信息生成右侧树形导航栏数据

    @RequestMapping("/give/user/assign/permission")
    public String giveUserAssignPermission(HttpSession session){
        
        // 获取当前登录的用户id
        UserDO user = (UserDO) session.getAttribute(AttrName.USER_LOGIN_SUCCESS_SESSION);
        Integer userId = user.getId();
        // 根据id查询已分配的许可信息
        List<PermissionDO> permissions = permissionService.getPermissionsByUserId(userId);
        
        // 将许可信息封装到map中
        Map<Integer,PermissionDO> map = new HashMap<>();
        
        // 遍历查询到的许可信息得到每一个对象，将对象存放到map中
        for (PermissionDO permissionDO : permissions) {
            // 获取当前节点的id
            Integer id = permissionDO.getId();
            map.put(id, permissionDO);
        }
        
        // 初始化根节点
        PermissionDO root = null;
        
        for (PermissionDO permissionDO : permissions) {
            // 获取分支节点的id
            Integer pid = permissionDO.getPid();
            
            if (pid == null) {
                // 如果当前pid为null，说明当前节点是根节点
                root = permissionDO;
            } else {
                // 获取分支节点的对象
                PermissionDO parent = map.get(pid);
                // 给分支节点添加子节点对象
                parent.getChildren().add(permissionDO);
            }
        }
        
        // 将根节点的数据存放到session中，以便每个登录的用户获取不同的侧边栏许可信息
        session.setAttribute("root", root);
        return "manager/main";
    }
    
    
}
