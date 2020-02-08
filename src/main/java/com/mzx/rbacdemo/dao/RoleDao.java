package com.mzx.rbacdemo.dao;

import com.mzx.rbacdemo.pojo.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleDao {


    List<Role> findByMap(Map<String, Object> map);

    @Select("select count(*) from t_role")
    int queryDataCountByMap(Map<String, Object> map);

    @Delete("delete from t_role where id = #{id}")
    boolean deleteById(Integer id);

    boolean deleteByMap(Map<String,Object> map);

    @Select("select * from t_role where id = #{id}")
    Role findById(Integer id);

    @Select("select * from t_role")
    List<Role> findAll();

    boolean deleteRolePermission(Map<String, Object> map);

    boolean insertRolePermission(Map<String, Object> map);
}
