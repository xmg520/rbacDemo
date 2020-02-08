package com.mzx.rbacdemo.controller;

import com.mzx.rbacdemo.pojo.AJAXResult;
import com.mzx.rbacdemo.pojo.Permission;
import com.mzx.rbacdemo.pojo.User;
import com.mzx.rbacdemo.service.PermissionService;
import com.mzx.rbacdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/reg")
    public String reg(){
        return "reg";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }


    @ResponseBody
    @RequestMapping("/doAjaxLogin")
    public Object doAjaxLogin(User user, HttpSession session){
        AJAXResult result = new AJAXResult();
        User dbUser = userService.queryByUser(user);
        if (dbUser != null){
            session.setAttribute("loginUser",dbUser);

            //获取用户权限信息
            List<Permission> permissions = permissionService.queryPermissionsByUser(dbUser);
            Map<Integer,Permission> permissionMap = new HashMap<>();
            Permission root = null;
            Set<String> uriSet = new HashSet<String>();
            for (Permission permission :permissions){
                permissionMap.put(permission.getId(),permission);
                if (permission.getUrl() != null && !"".equals(permission.getUrl())){
                    uriSet.add(permission.getUrl());
                }
            }
            session.setAttribute("authUriSet",uriSet);

            for (Permission permission: permissions){
                Permission child = permission;
                if (child.getPid() == 0){
                    root = permission;
                }else {
                    Permission parent = permissionMap.get(child.getPid());
                    parent.getChildren().add(child);
                }
            }
            session.setAttribute("rootPermission",root);
            result.setSuccess(true);
        }else {
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }


    @ResponseBody
    @RequestMapping("/queryAll")
    public Object queryAll(){
        List<User> users = userService.queryAll();
        return users;
    }
}
