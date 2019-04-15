package com.swan.crowdfunding.component.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swan.crowdfunding.component.service.api.UserService;
import com.swan.crowdfunding.constant.AttrName;
import com.swan.crowdfunding.constant.ErrorMessage;
import com.swan.crowdfunding.entity.ResultVO;
import com.swan.crowdfunding.entity.UserDO;
import com.swan.crowdfunding.util.StringMd5Utils;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    // 跳转到首页
    @RequestMapping("/index")
    public String indexTest() {
        return "portal/main";
    }

    // 跳转到登录页面
    @RequestMapping("/main/to/login/page")
    public String mainToLoginPage() {
        return "portal/login";
    }
    
    // 用户注销
    @RequestMapping("/user/logout")
    public String userLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.jsp";
    }

    // 通过ajax处理登录请求
    @ResponseBody // 请求成功后向浏览器返回数据给ajax接收
    @RequestMapping("/to/user/page")
    // 这个UserDO是表单提交的
    public ResultVO<String> toUserPage(UserDO userDO, HttpSession session) {
        ResultVO<String> resultVO = new ResultVO<String>();
        String loginacct = userDO.getLoginacct();
        // 明文密码
        String userpswd = userDO.getUserpswd();
        // 根据管理员名字查询UserDO的信息(从数据库获取的UserDO的数据)
        UserDO user = userService.getUserDOByAccount(loginacct);

        // 判断信息是否为空S
        if (user == null) {
            // 登录失败,设置ResultVO的信息
            resultVO.setResult(ResultVO.FAILED); // 登录失败
            resultVO.setResponseData(ResultVO.NO_DATA);// 没有数据
            resultVO.setMessage(ErrorMessage.LOGIN_FAILED); // 提示用户名或密码错误
            // 返回resultVO对线
            return resultVO;
        }

        /*
         * 信息不为空
         *     判断密码是否正确
         *     从数据获取的密码是密文
         */
        String pwdDB = user.getUserpswd();
        // 将表单提交的明文密码设置成密文
        String md5Pwd = StringMd5Utils.md5(userpswd);
        // 如果表单提交的明文密码与数据密文密码相同，就验证成功
        if (md5Pwd.equals(pwdDB)) {
            session.setAttribute(AttrName.USER_LOGIN_SUCCESS_SESSION, user);
            // 设置resultVO的状态
            resultVO.setMessage(ResultVO.NO_MSG);
            resultVO.setResult(ResultVO.SUCCESS);
            resultVO.setResponseData(ResultVO.NO_DATA);
            return resultVO;
        } else {
            // 俩个密码不一致
            resultVO.setMessage(ErrorMessage.LOGIN_FAILED); // 设置异常的信息
            resultVO.setResult(ResultVO.FAILED); // 设置登录失败
            resultVO.setResponseData(ResultVO.NO_DATA);
            return resultVO;
        }
    }

}
