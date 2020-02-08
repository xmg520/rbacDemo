package com.mzx.rbacdemo.service;

import com.mzx.rbacdemo.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> queryAll();

    User queryByUser(User user);

    List<User> queryByPage(int start , int size);

    Integer queryDataCount();

    List<User> findAll(Map<String,Object> map);

    Integer pageFindCount(Map<String,Object> map);

    boolean insertUser(User user);

    User queryById(Integer id);

    boolean editByUser(User user);

    boolean deleteById(Integer id);

    boolean deleteUsers(Map<String, Object> map);

    boolean insertUserRoles(Map<String, Object> map);

    boolean deleteUserRoles(Map<String, Object> map);

    List<Integer> queryRoleidsByUserid(Integer id);
}
