package com.mzx.rbacdemo.service.impl;

import com.mzx.rbacdemo.dao.UserDao;
import com.mzx.rbacdemo.pojo.User;
import com.mzx.rbacdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }

    @Override
    public User queryByUser(User user) {
        return userDao.queryByUser(user);
    }

    @Override
    public List<User> queryByPage(int start, int size) {
        return userDao.queryByPage(start,size);
    }

    @Override
    public Integer queryDataCount() {
        return userDao.queryDataCount();
    }

    @Override
    public List<User> findAll(Map<String,Object> map) {
        return userDao.findAll(map);
    }

    @Override
    public Integer pageFindCount(Map<String, Object> map) {
        return userDao.pageFindCount(map);
    }

    @Override
    public boolean insertUser(User user) {

        return userDao.insertUser(user);
    }

    @Override
    public User queryById(Integer id) {
        return userDao.queryById(id);
    }

    @Override
    public boolean editByUser(User user) {
        return userDao.editByUser(user);
    }

    @Override
    public boolean deleteById(Integer id) {
        return userDao.deleteById(id);
    }

    @Override
    public boolean deleteUsers(Map<String, Object> map) {
        return userDao.deleteUsers(map);
    }

    @Override
    public boolean insertUserRoles(Map<String, Object> map) {
        return userDao.insertUserRoles(map);
    }

    @Override
    public boolean deleteUserRoles(Map<String, Object> map) {
        return userDao.deleteUserRoles(map);
    }

    @Override
    public List<Integer> queryRoleidsByUserid(Integer id) {
        return userDao.queryRoleidsByUserid(id);
    }


}
