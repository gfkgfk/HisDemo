package com.kent.hisdemo.dao.read.role;

import com.kent.hisdemo.entity.role.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface RoleReadMapper {
    public List<Role> getAllRoles();


}
