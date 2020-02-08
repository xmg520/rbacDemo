package com.mzx.rbacdemo.controller;

import com.mzx.rbacdemo.pojo.AJAXResult;
import com.mzx.rbacdemo.pojo.Permission;
import com.mzx.rbacdemo.service.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;


    @ResponseBody
    @RequestMapping("/loadAssignData")
    public Object loadAssignData(Integer roleid){
        List<Permission> permissions = new ArrayList<>();
        List<Permission> ps = permissionService.findAll();

        //获取当前角色分配的许可信息
        List<Integer> permissionids = permissionService.queryPermissionidsByRoleId(roleid);
        Map<Integer,Permission> permissionMap = new HashMap<>();
        for (Permission p:ps){
            if (permissionids.contains(p.getId())){
                p.setChecked(true);
            }else {
                p.setChecked(false);
            }
            permissionMap.put(p.getId(),p);
        }
        for (Permission p:ps){
            Permission child = p;
            if (child.getPid() == 0){
                permissions.add(p);
            }else {
                Permission parent = permissionMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return permissions;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(Integer id){
        AJAXResult result = new AJAXResult();

        try{
            if (permissionService.deleteById(id)){
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

    @ResponseBody
    @RequestMapping("/update")
    public Object update(Permission permission){
        AJAXResult result = new AJAXResult();
        try {
            if (permissionService.updatePermission(permission)){
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

    @RequestMapping("/edit")
    public String edit(Integer id,Model model){

        Permission permission = permissionService.queryById(id);
        model.addAttribute("permission",permission);
        return "permission/edit";
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(Permission permission){
        AJAXResult result = new AJAXResult();

        try {
            if (permissionService.insertPermission(permission)){
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

    @RequestMapping("/add")
    public String add(){
        return "permission/add";
    }

    @RequestMapping("/index")
    public String index(){
        return "permission/index";
    }

    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){
        List<Permission> permissions = new ArrayList<Permission>();
//        Permission root = new Permission();
//        root.setName("顶级节点");
//
//        Permission child = new Permission();
//        child.setName("子节点");
//
//        root.getChildren().add(child);
//        permissions.add(root);

        //递归查询
//        Permission parent = new Permission();
//        parent.setId(0);
//        queryChildPermissions(parent);
//        System.out.println(parent.getChildren());
//        return parent.getChildren();

        //for循环查询
        //查询所有的许可数据
        List<Permission> ps = permissionService.findAll();

//        for (Permission p : ps){
//            Permission child = p;
//            if (p.getPid() == 0){
//                permissions.add(p);
//            }else {
//                for (Permission innerPermission:ps){
//                    if (child.getPid().equals(innerPermission.getId())){
//                        //父节点
//                        Permission parent = innerPermission;
//                        //组合父子节点的关系
//                        parent.getChildren().add(child);
//                        break;
//                    }
//                }
//            }
//        }
        //map高效查询
        Map<Integer,Permission> permissionMap = new HashMap<>();
        for (Permission p:ps){
            permissionMap.put(p.getId(),p);
        }
        for (Permission p :ps){
            Permission child = p;
            if (child.getPid() == 0){
                permissions.add(p);
            }else {
                Permission parent = permissionMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return permissions;
    }

    /**
     * 递归查询许可信息
     * 1)方法自己调用自己
     * 2)方法一定要有跳出条件
     * 3)方法调用时，要有规律
     *
     */

    private void queryChildPermissions(Permission parent){
        List<Permission> childPermissions = permissionService.
                queryChildPermissions(parent.getId());

        for (Permission permission : childPermissions){
            queryChildPermissions(permission);
        }

        parent.setChildren(childPermissions);
    }
}
