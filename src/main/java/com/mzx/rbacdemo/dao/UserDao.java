package com.mzx.rbacdemo.dao;

import com.mzx.rbacdemo.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    @Select("select * from t_user")
    List<User> queryAll();

    @Select("select * from t_user where loginacct=#{loginacct} and userpswd=#{userpswd} ")
    User queryByUser(User user);

    @Select("select * from t_user limit #{start},#{size}")
    List<User> queryByPage(int start, int size);

    @Select("SELECT count(*) from t_user")
    Integer queryDataCount();

    List<User> findAll(Map<String,Object> map);

    Integer pageFindCount(Map<String,Object> map);

    boolean insertUser(User user);

    @Select("select * from t_user where id = #{id}")
    User queryById(Integer id);

    @Update("update t_user set loginacct=#{loginacct} ,username=#{username},email=#{email} where id=#{id} ")
    boolean editByUser(User user);

    @Delete("delete from t_user where id=#{id} ")
    boolean deleteById(Integer id);

    boolean deleteUsers(Map<String, Object> map);

    boolean insertUserRoles(Map<String, Object> map);

    boolean deleteUserRoles(Map<String, Object> map);

    @Select("select roleid from t_user_role where userid = #{id}")
    List<Integer> queryRoleidsByUserid(Integer id);
}
