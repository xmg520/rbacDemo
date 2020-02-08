package com.mzx.rbacdemo.service.impl;

import com.mzx.rbacdemo.dao.RoleDao;
import com.mzx.rbacdemo.pojo.Role;
import com.mzx.rbacdemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findByMap(Map<String, Object> map) {
        return roleDao.findByMap(map);
    }

    @Override
    public int queryDataCountByMap(Map<String, Object> map) {
        return roleDao.queryDataCountByMap(map);
    }

    @Override
    public boolean deleteById(Integer id) {
        return roleDao.deleteById(id);
    }

    @Override
    public boolean deleteByMap(Map<String, Object> map) {
        return roleDao.deleteByMap(map);
    }

    @Override
    public Role findById(Integer id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public boolean insertRolePermission(Map<String, Object> map) {
        roleDao.deleteRolePermission(map);
        return roleDao.insertRolePermission(map);
    }

    @Override
    public boolean deleteRolePermission(Map<String, Object> map) {
        return roleDao.deleteRolePermission(map);
    }

}
