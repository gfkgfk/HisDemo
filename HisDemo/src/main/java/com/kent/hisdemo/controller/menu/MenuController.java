package com.kent.hisdemo.controller.menu;

import com.kent.hisdemo.config.annotation.CurrentUser;
import com.kent.hisdemo.entity.menu.Menu;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.service.menu.MenuService;
import com.kent.hisdemo.utils.beancopy.BeanCopyUtil;
import com.kent.hisdemo.vo.param.menu.MenuParentParam;
import com.kent.hisdemo.vo.vo.menu.MenuVO;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api(value = "v1", tags = "2.菜单管理")
@RequestMapping("/api/v1")
public class MenuController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/get_menu_by_parent_id", method = RequestMethod.POST)
    public List<MenuVO> getMenuByParentId(@CurrentUser User user, MenuParentParam menuParentParam) {
        List<Menu> menus = menuService.getMenuByMenuParentId(menuParentParam.getMenuId());
        List<MenuVO> ret = BeanCopyUtil.copyListProperties(menus, MenuVO::new);
//        List<UserVO> userVOList = BeanCopyUtil.copyListProperties(userDOList, UserVO::new, (userDO, userVO) ->{
//            // 这里可以定义特定的转换规则
//            userVO.setSex(SexEnum.getDescByCode(userDO.getSex()).getDesc());
//        });
        return ret;
    }

    @RequestMapping(value = "/get_all_menus", method = RequestMethod.POST)
    public List<MenuVO> getAllMenus(@CurrentUser User user) {
        List<Menu> menus = menuService.getAllMenus();
        List<MenuVO> ret = BeanCopyUtil.copyListProperties(menus, MenuVO::new);
//        List<UserVO> userVOList = BeanCopyUtil.copyListProperties(userDOList, UserVO::new, (userDO, userVO) ->{
//            // 这里可以定义特定的转换规则
//            userVO.setSex(SexEnum.getDescByCode(userDO.getSex()).getDesc());
//        });
        return ret;
    }

    @RequestMapping(value = "/get_menu_by_user", method = RequestMethod.POST)
    public List<MenuVO> getUserMenu(@CurrentUser User user) {
        int role = user.getRole();
        List<Menu> menus= menuService.getUserMenuByRole(role);
        List<MenuVO> ret = BeanCopyUtil.copyListProperties(menus, MenuVO::new);
        return ret;
    }



}
