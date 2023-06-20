package com.kent.hisdemo.mapper.role;

import com.kent.hisdemo.entity.role.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface RoleMapper {
    public List<Role> getAllRoles();


}
