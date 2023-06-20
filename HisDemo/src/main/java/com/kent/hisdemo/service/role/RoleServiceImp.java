package com.kent.hisdemo.service.role;

import com.kent.hisdemo.entity.role.Role;
import com.kent.hisdemo.dao.read.role.RoleReadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImp implements RoleService {

    @Autowired
    RoleReadMapper roleReadMapper;


    @Override
    public List<Role> getAllRoles() {
        return roleReadMapper.getAllRoles();
    }




}
