package com.mzx.rbacdemo.service;

import com.mzx.rbacdemo.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    List<Role> findByMap(Map<String, Object> map);


    int queryDataCountByMap(Map<String, Object> map);

    boolean deleteById(Integer id);

    boolean deleteByMap(Map<String,Object> map);

    Role findById(Integer id);

    List<Role> findAll();

    boolean insertRolePermission(Map<String, Object> map);

    boolean deleteRolePermission(Map<String, Object> map);
}
