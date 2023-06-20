package com.kent.hisdemo.service.menu;

import com.kent.hisdemo.entity.menu.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> getMenuByMenuParentId(int id);
    public List<Menu> getAllMenus();
    public List<Long> getMenuIdByRole(int roleId);
    public List<Menu> getMenusByIds(List<Long> ids);
    public Menu getMenuById(Long id);

    public List<Menu> getUserMenuByRole(int roleId);
}
