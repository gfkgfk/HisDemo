package com.kent.hisdemo.controller.role;


import com.kent.hisdemo.config.annotation.CurrentUser;
import com.kent.hisdemo.entity.role.Role;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.service.role.RoleService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api(value = "v1", tags = "3.角色管理")
@RequestMapping("/api/v1")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/get_roles", method = RequestMethod.POST)
    public List<Role> getRoles(@CurrentUser User user) {
        logger.info("User:" + user.toString());
        return roleService.getAllRoles();
    }


}
