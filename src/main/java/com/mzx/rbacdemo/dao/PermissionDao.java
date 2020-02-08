package com.mzx.rbacdemo.dao;

import com.mzx.rbacdemo.pojo.Permission;
import com.mzx.rbacdemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionDao {

    @Select("select * from t_permission where pid = #{id}")
    List<Permission> queryChildPermissions(Integer id);

    @Select("select * from t_permission where 1 = 1")
    List<Permission> findAll();

    boolean insertPermission(Permission permission);

    @Select("select * from t_permission where id = #{id}")
    Permission queryById(Integer id);

    boolean updatePermission(Permission permission);

    boolean deleteById(Integer id);

    @Select("select permissionid from t_role_permission where roleid = #{roleid} ")
    List<Integer> queryPermissionidsByRoleId(Integer roleid);

    List<Permission> queryPermissionsByUser(User dbUser);
}
