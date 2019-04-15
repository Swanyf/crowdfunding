package com.swan.crowdfunding.component.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.swan.crowdfunding.component.service.api.UserService;
import com.swan.crowdfunding.constant.DefaultPage;
import com.swan.crowdfunding.constant.ErrorMessage;
import com.swan.crowdfunding.entity.ResultVO;
import com.swan.crowdfunding.entity.UserDO;

@Controller
public class UserCRUDController {

    @Autowired
    UserService userService;

    /**
     * PageInfo<UserDO> 分页的数据 分页的数据封装到ResultVO的responseData属性中
     */
    @ResponseBody
    @RequestMapping("/user/get/page")
    public ResultVO<PageInfo<UserDO>> getUserPage(
            // 将每页显示的数据条数设置为常量，所有分页数据需要俩个参数：当前页数，模糊查询的关键字
            // 默认是 1 表示，每次访问时显示第一页
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 由于关键字不一定输入，所以只有默认是空串才能不影响输出数据
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        // 查询分页后的数据
        PageInfo<UserDO> pageInfo = userService.getUserDOPageInfo(pageNum, DefaultPage.PAGE_SIZE, keyword);
        // 将分页后的数据封装成ResultVO的数据，响应返回给ajax请求
        return new ResultVO<>(ResultVO.SUCCESS, pageInfo, ResultVO.NO_MSG);
    }

    // 跳转到显示分页数据的界面
    /*
     * @RequestMapping("/user/page") public String toUserPage() { return
     * "user/page"; }
     */

    // 执行删除操作
    @ResponseBody
    @RequestMapping("/batch/remove/user/by/id/list")
    // 这里的参数是请求的一些用户id集合
    public ResultVO<String> removeUser(@RequestBody List<Integer> userIdList) {
        userService.deleteUsersByIdList(userIdList);
        return new ResultVO<String>(ResultVO.SUCCESS, ResultVO.NO_DATA, ResultVO.NO_MSG);
    }

    // 批量查询，根据id查询用户集合信息
    @ResponseBody
    @RequestMapping("/get/user/list/by/id/list")
    public ResultVO<List<UserDO>> getUsers(@RequestParam("userIdList[]") List<Integer> userIdList) {
        List<UserDO> userList = userService.getUsersByIdList(userIdList);
        // 给浏览器响应查询到的用户数据
        return new ResultVO<List<UserDO>>(ResultVO.SUCCESS, userList, ResultVO.NO_MSG);
    }

    // 新增操作：查询用户名是否存在
    @ResponseBody
    @RequestMapping("/get/loginAccount")
    public ResultVO<String> getUserByLoginAccount(@RequestParam("loginacct") String loginAccount) {
        // 根据请求的参数查询数据库
        int count = userService.getUserByLoginAccount(loginAccount);

        if (count != 0) {
            // 用户名已存在
            return new ResultVO<String>(ResultVO.FAILED, ResultVO.NO_DATA, ErrorMessage.LOGINACCOUNT_EXIST);
        } else {
            // 可以使用的用户名
            return new ResultVO<String>(ResultVO.SUCCESS, ResultVO.NO_DATA, ResultVO.NO_MSG);
        }
    }
    

    // 添加操作
    @ResponseBody
    @RequestMapping("/add/user")
    // 新增用户的操作
    public ResultVO<String> addUser(UserDO userDO) {
        // 执行添加业务
        userService.insertUser(userDO);
        // 返回给浏览器信息
        return new ResultVO<String>(ResultVO.SUCCESS, ResultVO.NO_DATA, ResultVO.NO_MSG);
    }

    // 根据id查询user对象
    @ResponseBody
    @RequestMapping("/get/user/by/id")
    public ResultVO<UserDO> getUserById(@RequestParam("id") Integer id) {
        UserDO user = userService.getUserById(id);
        return new ResultVO<UserDO>(ResultVO.SUCCESS, user, ResultVO.NO_MSG);
    }

    // 修改user信息
    @ResponseBody
    @RequestMapping("/edit/user")
    public ResultVO<String> editUser(UserDO userDO) {
        userService.editUser(userDO);
        return new ResultVO<String>(ResultVO.SUCCESS, ResultVO.NO_DATA, ResultVO.NO_MSG);
    }

}
