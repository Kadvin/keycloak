package com.joowing.auth.rest;

import com.joowing.auth.represent.MenuRepresentation;
import com.joowing.auth.spi.MenuService;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.KeycloakSession;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 菜单/应用入口
 */
@Path("{realmId}/clients/{clientId}/principal_factories/{principalType}/menus")
public class MenuResource {
    private final KeycloakSession session;
    @PathParam("realmId")
    private String realmId;
    @PathParam("clientId")
    private String clientId;
    @PathParam("principalType")
    private String principalType;

    public MenuResource(KeycloakSession session) {
        this.session = session;
    }

    /**
     * 获取特定身份类型下的菜单版本号，用于与特定子系统进行比较
     *
     * @return 版本号
     */
    @GET
    @Path("/version")
    @NoCache
    @Produces(MediaType.APPLICATION_JSON)
    public String getMenuVersion() {
        return session.getProvider(MenuService.class).getVersion(principalType);
    }

    /**
     * 获取特定身份类型下的菜单，用于人工查阅
     *
     * @return 菜单
     */
    @GET
    @NoCache
    @Produces(MediaType.APPLICATION_JSON)
    public MenuRepresentation[] getMenus(){
        return session.getProvider(MenuService.class).getMenus(principalType);
    }

    /**
     * 某个子系统版本比对之后，发现版本不一致时，同步相应的菜单结构
     *
     * @param menus         应用的菜单树
     * @return 同步后的版本号，调用者应该存下来，用于下次版本比对
     */
    @PUT
    @Path("/synchronize")
    @NoCache
    @Produces(MediaType.APPLICATION_JSON)
    public String synchronize( MenuRepresentation[] menus) {
        return session.getProvider(MenuService.class).synchronize(principalType, menus);
    }

    /**
     * 查询某个身份实例下的菜单，未做权限过滤
     *
     * @param principalCode 身份代码
     * @return 未做权限过滤的原始菜单，用于检查判断
     */
    @GET
    @Path("/{principalCode}")
    @NoCache
    @Produces(MediaType.APPLICATION_JSON)
    public List<MenuRepresentation> getMenus(@PathParam("principalCode") String principalCode){
        return session.getProvider(MenuService.class).queryMenus(principalCode);
    }

    /**
     * 过滤当前用户，特定身份下的菜单结构
     *
     * @param principalCode 身份代码
     * @return 已经过滤后的菜单
     */
    @GET
    @Path("/filtered/{principalCode}")
    @NoCache
    @Produces(MediaType.APPLICATION_JSON)
    public List<MenuRepresentation> filterMenus(@PathParam("principalCode") String principalCode){
        String currentUser = "";
        return session.getProvider(MenuService.class).filterMenus(currentUser, principalCode);
    }
}
