package com.swan.crowdfunding.component.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swan.crowdfunding.component.mapper.UserDOMapper;
import com.swan.crowdfunding.component.service.api.UserService;
import com.swan.crowdfunding.entity.UserDO;
import com.swan.crowdfunding.util.StringMd5Utils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDOMapper userDOMapper;

    // 根据管理员名字查询信息
    @Override
    public UserDO getUserDOByAccount(String userAccount) {
        return userDOMapper.getUserDOByAccount(userAccount);
    }

    // 分页后的数据
    @Override
    public PageInfo<UserDO> getUserDOPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        // 启动分页的效果
        PageHelper.startPage(pageNum, pageSize);
        // 从数据库模糊查询得到UserDO信息
        List<UserDO> list = userDOMapper.selectForSearch(keyword);
        // 将从数据库得到的list信息封装到PageInfo的集合中(分页)
        PageInfo<UserDO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void deleteUsersByIdList(List<Integer> userIdList) {
        userDOMapper.deleteUsersByIdList(userIdList);
    }

    @Override
    public List<UserDO> getUsersByIdList(List<Integer> userIdList) {
        return userDOMapper.getUsersByIdList(userIdList);
    }

    @Override
    public int getUserByLoginAccount(String loginAccount) {
        return userDOMapper.getUserByLoginAccount(loginAccount);
    }

    @Override
    public void insertUser(UserDO userDO) {
        // 给密码进行加密
        String userpswd = userDO.getUserpswd();
        String md5 = StringMd5Utils.md5(userpswd);
        userDO.setUserpswd(md5);
        
        // 创建的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createtime = sdf.format(new Date());
        userDO.setCreatetime(createtime);
        userDOMapper.insert(userDO);
    }

    @Override
    public UserDO getUserById(Integer id) {
        return userDOMapper.selectByPrimaryKey(id);
    }

    // 修改用户
    @Override
    public void editUser(UserDO userDO) {
        userDOMapper.updateByPrimaryKey(userDO);
    }
}
