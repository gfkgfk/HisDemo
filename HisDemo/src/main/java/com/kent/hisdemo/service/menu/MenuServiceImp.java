package com.kent.hisdemo.service.menu;


import com.kent.hisdemo.entity.menu.Menu;
import com.kent.hisdemo.mapper.menu.MenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("menuService")
public class MenuServiceImp implements MenuService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenuByMenuParentId(int id) {
        return menuMapper.getMenuByMenuParentId(id);
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    @Override
    public List<Long> getMenuIdByRole(int roleId) {
        return menuMapper.getMenuIdByRole(roleId);
    }

    @Override
    public List<Menu> getMenusByIds(List<Long> ids) {
        List<Menu> menus = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            Menu menu = getMenuById(ids.get(i));
            menus.add(menu);
        }
        return menus;
    }

    @Override
    public Menu getMenuById(Long id) {
        return menuMapper.getMenuById(id);
    }


    @Override
    public List<Menu> getUserMenuByRole(int roleId) {
        List<Long> ids = getMenuIdByRole(roleId);
        List<Menu> menus = getMenusByIds(ids);
        return menus;
    }

}
