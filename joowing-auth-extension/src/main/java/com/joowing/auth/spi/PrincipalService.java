package com.joowing.auth.spi;

import com.joowing.auth.represent.PrincipalRepresentation;
import org.keycloak.provider.Provider;

import java.util.List;

/**
 * 关于身份的服务
 */
public interface PrincipalService extends Provider {

    /**
     * 根据类型查询所有的身份实例
     *
     * @param principalType 身份类型
     * @return 身份列表
     */
    List<PrincipalRepresentation> queryPrincipalsByType(String principalType);

    /**
     * 查询某个用户的身份
     *
     * @param username 用户名
     * @return 身份列表
     */
    List<PrincipalRepresentation> queryPrincipalsForUser(String username);

    /**
     * 查询某个组的身份
     *
     * @param groupId 组的id
     * @return 身份列表
     */
    List<PrincipalRepresentation> queryPrincipalsForGroup(String groupId);

    /**
     * 为特定用户添加身份
     *
     * @param username      用户名
     * @param principalCode 身份代码
     */
    void addUserPrincipal(String username, String principalCode);

    /**
     * 移除特定用户的相应身份
     *
     * @param username      用户名
     * @param principalCode 身份代码
     */
    void removeUserPrincipal(String username, String principalCode);

    /**
     * 为特定群组添加身份
     *
     * @param groupId       群组ID
     * @param principalCode 身份代码
     */
    void addGroupPrincipal(String groupId, String principalCode);

    /**
     * 移除特定群组的相应身份
     *
     * @param groupId       群组ID
     * @param principalCode 身份代码
     */
    void removeGroupPrincipal(String groupId, String principalCode);

}
