package com.mzx.rbacdemo.service;

import com.mzx.rbacdemo.pojo.Permission;
import com.mzx.rbacdemo.pojo.User;

import java.util.List;

/**
 * @author Mzx
 */
public interface PermissionService {
    List<Permission> queryChildPermissions(Integer id);

    List<Permission> findAll();

    boolean insertPermission(Permission permission);

    Permission queryById(Integer id);

    boolean updatePermission(Permission permission);

    boolean deleteById(Integer id);

    List<Integer> queryPermissionidsByRoleId(Integer roleid);

    List<Permission> queryPermissionsByUser(User dbUser);
}
