package com.kent.hisdemo.vo.param.menu;

import java.io.Serializable;

public class MenuParentParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private int menuId;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "MenuParam{" +
                "menuId=" + menuId +
                '}';
    }
}
