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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    /**
     * 分配角色管理接口
     */
    @ResponseBody
    @RequestMapping("/doAssign")
    public Object doAssign(Integer userid , Integer[] unassignroleids){
        AJAXResult result = new AJAXResult();
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("userid",userid);
            map.put("roleids",unassignroleids);

            if (userService.insertUserRoles(map)){
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
    @ResponseBody
    @RequestMapping("/doUnAssign")
    public Object doUnAssign(Integer userid , Integer[] assignroleids){
        AJAXResult result = new AJAXResult();
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("userid",userid);
            map.put("roleids",assignroleids);
            System.out.println(assignroleids);
            if(userService.deleteUserRoles(map)) {
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
     * 跳转角色管理页面
     */
    @RequestMapping("/assign")
    public String assign(Integer id, Model model){
        User user = userService.queryById(id);
        model.addAttribute("user",user);
//        Role role = roleService.findById(id);
        List<Role> roles = roleService.findAll();
//        model.addAttribute("roles",roles);
        List<Role> assingeRoles = new ArrayList<Role>();
        List<Role> unassingeRoles = new ArrayList<Role>();

        //获取关系表的数据
        List<Integer> roleids = userService.queryRoleidsByUserid(id);
        for (Role role:roles){
            if (roleids.contains(role.getId())){
                assingeRoles.add(role);
            }else {
                unassingeRoles.add(role);
            }
        }

        model.addAttribute("assingeRoles",assingeRoles);
        model.addAttribute("unassingeRoles",unassingeRoles);
        return "user/assign";
    }

    /**
     * 批量删除接口
     */
    @ResponseBody
    @RequestMapping("/deletes")
    public Object deletes(Integer[] userid){
        AJAXResult result = new AJAXResult();
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("userids",userid);
            if (userService.deleteUsers(map)){
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
     * 删除信息接口
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object deleteById(Integer id){
        AJAXResult result = new AJAXResult();
        try {
            if (userService.deleteById(id)){
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
     * 修改接口
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(User user){
        AJAXResult result = new AJAXResult();

        try {
            if (userService.editByUser(user)){
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
     * 修改用户信息页面
     */
    @RequestMapping("/edit")
    public String edit(Integer id,Model model){
        User user = userService.queryById(id);
        model.addAttribute("user",user);
        return "user/edit";
    }

    /**
     * 插入接口
     */
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(User user){
        AJAXResult result = new AJAXResult();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setCreatetime(sdf.format(new Date()));
            user.setUserpswd("1234");
            if (userService.insertUser(user)){
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
     * 添加页面
     * @return
     */
    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }


    /**
     * 异步刷新版本
     * 用户首页
     */
    @RequestMapping("/index")
    public String index(){
        return "user/index";
    }

    /**
     *  用户信息模糊+分页查询
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageQuery")
    public Object pageQuery(String queryText ,Integer pageno, Integer pagesize){
        AJAXResult result = new AJAXResult();
        try {
            //分页查询
            Map<String,Object> map = new HashMap<>();

            int start = (pageno-1)*pagesize;

            map.put("start",start);
            map.put("size",pagesize);
            map.put("queryText",queryText);
//            List<User> users = userService.queryByPage(start,pagesize);
            List<User> users1 = userService.findAll(map);
            // 总的数据量
            int totalsize = userService.queryDataCount();
            //最大页码
            int totalno = 0;
            if(totalsize % pagesize == 0){
                totalno = totalsize / pagesize;
            }else {
                totalno = totalsize / pagesize + 1;
            }

            //分页对象
            Page<User> userPage = new Page<>();
            userPage.setDatas(users1);
            userPage.setPageno(pageno);
            userPage.setTotalno(totalno);
            userPage.setTotalsize(totalsize);

            result.setData(userPage);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 未异步刷新版本
     * 用户首页
     */
//    @RequestMapping("/index")
//    public String index(@RequestParam(required = false,defaultValue = "1") Integer pageno, @RequestParam(required = false,defaultValue = "2") Integer pagesize , Model model){
//        int start = (pageno-1)*pagesize;
//        List<User> users = userService.queryByPage(start,pagesize);
//        model.addAttribute("users",users);
//        // 当前页码
//        model.addAttribute("pageno",pageno);
//
//        // 总的数据量
//        int totalsize = userService.queryDataCount();
//        //最大页码
//        int totalno = 0;
//        if(totalsize % pagesize == 0){
//            totalno = totalsize / pagesize;
//        }else {
//            totalno = totalsize / pagesize + 1;
//        }
//
//        model.addAttribute("totalno",totalno);
//
//        return "/user/index";
//    }


}
