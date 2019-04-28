package com.joowing.auth.spi;

import com.joowing.auth.model.Principal;
import com.joowing.auth.represent.MenuRepresentation;
import org.keycloak.provider.Provider;

import java.util.List;

/**
 * 菜单服务(用于形式授权)
 */
public interface MenuService extends Provider {

    String getVersion(String principalType);

    MenuRepresentation[] getMenus(String principalType);

    String synchronize(String principalType, MenuRepresentation[] menus);

    /**
     * 组装身份下的菜单
     *
     * @param principalCode 身份
     * @return 未过滤过的菜单
     */
    List<MenuRepresentation> queryMenus(String principalCode);

    /**
     * 获取某个用户相关身份下的菜单
     *
     * @param user          用户
     * @param principalCode 用户已经有的身份，如果身份不在用户的身份表里面，则会报异常
     * @return 已经根据授权情况过滤了菜单
     */
    List<MenuRepresentation> filterMenus(String user, String principalCode);

}
