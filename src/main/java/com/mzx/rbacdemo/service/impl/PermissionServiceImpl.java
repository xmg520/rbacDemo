package com.mzx.rbacdemo.service.impl;

import com.mzx.rbacdemo.dao.PermissionDao;
import com.mzx.rbacdemo.pojo.Permission;
import com.mzx.rbacdemo.pojo.User;
import com.mzx.rbacdemo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Permission> queryChildPermissions(Integer id) {
        return permissionDao.queryChildPermissions(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public boolean insertPermission(Permission permission) {
        return permissionDao.insertPermission(permission);
    }

    @Override
    public Permission queryById(Integer id) {
        return permissionDao.queryById(id);
    }

    @Override
    public boolean updatePermission(Permission permission) {
        return permissionDao.updatePermission(permission);
    }

    @Override
    public boolean deleteById(Integer id) {
        return permissionDao.deleteById(id);
    }

    @Override
    public List<Integer> queryPermissionidsByRoleId(Integer roleid) {
        return permissionDao.queryPermissionidsByRoleId(roleid);
    }

    @Override
    public List<Permission> queryPermissionsByUser(User dbUser) {
        return permissionDao.queryPermissionsByUser(dbUser);
    }
}
