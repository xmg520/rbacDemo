package com.mzx.rbacdemo.controller;

import com.mzx.rbacdemo.pojo.AJAXResult;
import com.mzx.rbacdemo.pojo.Page;
import com.mzx.rbacdemo.pojo.Role;
import com.mzx.rbacdemo.pojo.User;
import com.mzx.rbacdemo.service.RoleService;
import com.mzx.rbacdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 分配角色管理接口
     */
    @ResponseBody
    @RequestMapping("/doAssign")
    public Object doAssign(Integer roleid , Integer[] permissionids){
        AJAXResult result = new AJAXResult();
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("roleid",roleid);
            map.put("permissionids",permissionids);
            if (roleService.insertRolePermission(map)){
                result.setSuccess(true);
            }else {
                result.setSuccess(false);
            }
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * 取消角色管理接口
     */
//    @ResponseBody
//    @RequestMapping("/doUnAssign")
//    public Object doUnAssign(Integer roleid , Integer[] permissionids){
//        AJAXResult result = new AJAXResult();
//        try{
//            Map<String,Object> map = new HashMap<>();
//            map.put("roleid",roleid);
//            map.put("permissionids",permissionids);
//            if(roleService.deleteRolePermission(map)) {
//                result.setSuccess(true);
//            }else {
//                result.setSuccess(false);
//            }
//            result.setSuccess(true);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setSuccess(false);
//        }
//        return result;
//    }



    /**
     * 批量删除角色许可接口
     */
    @ResponseBody
    @RequestMapping("/roleDeletes")
    public Object roleDeletes(Integer[] ids){
        AJAXResult result = new AJAXResult();

        try {
            Map<String,Object> map = new HashMap<>();
            map.put("ids",ids);
            if (roleService.deleteByMap(map)){
                result.setSuccess(true);
            }else {
                result.setSuccess(false);
            }
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * 根据id删除角色许可接口
     */
    @ResponseBody
    @RequestMapping("/roleDelete")
    public Object roleDelete(Integer id){
        AJAXResult result = new AJAXResult();

        try{
            if (roleService.deleteById(id)){
                result.setSuccess(true);
            }else {
                result.setSuccess(false);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * 角色许可查询接口
     */
    @ResponseBody
    @RequestMapping("/roleQuery")
    public Object roleQuery(String queryText,Integer pageno, Integer pagesize){
        AJAXResult result = new AJAXResult();

        try {
            Map<String,Object> map = new HashMap<>();

            int start = (pageno - 1)*pagesize;

            map.put("start",start);
            map.put("size",pagesize);
            map.put("queryText",queryText);

            List<Role> roles = roleService.findByMap(map);
            // 总数据量
            int totalsize = roleService.queryDataCountByMap(map);
            // 最大页码
            int totalno = 0;
            if (totalno % pagesize == 0){
                totalno = totalsize / pagesize + 1;
            }else {
                totalno = totalsize / pagesize;
            }

            //分页对象
            Page<Role> rolePage = new Page<>();
            rolePage.setDatas(roles);
            rolePage.setTotalno(totalno);
            rolePage.setPageno(pageno);
            rolePage.setTotalsize(totalsize);

            result.setData(rolePage);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }


    @RequestMapping("/assign")
    public String assign(){
        return "role/assign";
    }

    @RequestMapping("/index")
    public String index(){
        return "role/index";
    }
}
