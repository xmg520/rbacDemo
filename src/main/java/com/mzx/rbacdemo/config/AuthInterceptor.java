package com.mzx.rbacdemo.config;

import com.mzx.rbacdemo.pojo.Permission;
import com.mzx.rbacdemo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PermissionService permissionService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户的请求地址
        String uri = request.getRequestURI();

        //判断当前路径是否需要进行权限验证
        List<Permission> permissions = permissionService.findAll();

        Set<String> uriSet = new HashSet<String>();
        for (Permission permission:permissions){
            if (permission.getUrl() != null && !"".equals(permission.getUrl())){
                uriSet.add(permission.getUrl());
            }
        }

        if (uriSet.contains(uri)){
            //权限验证
            //判断当前用户是否有对应权限
            Set<String> authUriSet = (Set<String>) request.getSession().getAttribute("authUriSet");
            if (authUriSet.contains(uri)){
                return true;
            }else {
                response.sendRedirect("/error");
                return false;
            }
        }else {
            return true;
        }
    }


}
