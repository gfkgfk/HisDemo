package com.kent.hisdemo.vo.vo.menu;

import com.kent.hisdemo.entity.menu.Menu;

import java.io.Serializable;
import java.util.List;

public class MenuVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String cName;
    private String path;
    private String icon;
    private Long parentId;
    private List<Menu> subMenus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cName='" + cName + '\'' +
                ", path='" + path + '\'' +
                ", icon='" + icon + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
