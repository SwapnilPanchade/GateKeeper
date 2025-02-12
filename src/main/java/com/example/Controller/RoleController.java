package com.example.Controller;

import com.example.Entity.Role;
import com.example.Service.RoleService;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
//
//    @Autowired
//    private RoleService roleService;
//
//    @PostMapping("/create")
//    public String createRole(@RequestParam String role){
//        return roleService.createRole(role);
//    }

}
