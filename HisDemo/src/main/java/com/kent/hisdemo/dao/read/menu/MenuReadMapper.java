package com.kent.hisdemo.dao.read.menu;

import com.kent.hisdemo.entity.menu.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MenuReadMapper {
    public List<Menu> getMenuByMenuParentId(int id);


    public List<Menu> getAllMenus();

    public List<Long> getMenuIdByRole(int roleId);

    public Menu getMenuById(Long id);

}
