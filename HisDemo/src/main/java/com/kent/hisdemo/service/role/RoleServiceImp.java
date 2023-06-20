package com.kent.hisdemo.service.role;

import com.kent.hisdemo.entity.role.Role;
import com.kent.hisdemo.mapper.role.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImp implements RoleService {

    @Autowired
    RoleMapper roleMapper;


    @Override
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }




}
