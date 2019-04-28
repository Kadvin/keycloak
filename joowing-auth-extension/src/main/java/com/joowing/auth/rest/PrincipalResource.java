package com.joowing.auth.rest;

import com.joowing.auth.represent.PrincipalRepresentation;
import com.joowing.auth.spi.PrincipalService;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.KeycloakSession;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 身份
 */
@Path("{realmId}/clients/{clientId}/principal_factories/{principalType}/principals")
public class PrincipalResource {
    private final KeycloakSession session;
    @PathParam("realmId")
    private String realmId;
    @PathParam("clientId")
    private String clientId;

    public PrincipalResource(KeycloakSession session) {
        this.session = session;
    }

    /**
     * 查看某个身份类型下有多少身份实例，
     * <p>
     * TODO 需要支持分页和搜索
     *
     * @param principalType 身份类型
     * @return 身份实例
     */
    @GET
    @Path("{principalType}/factory_principals")
    @NoCache
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrincipalRepresentation> getPrincipals(@PathParam("principalType") String principalType) {
        return session.getProvider(PrincipalService.class).queryPrincipalsByType(principalType);
    }

    /**
     * 查看某个用户具有多少身份
     * <p>
     * TODO 需要支持分页和搜索
     *
     * @param username              用户名
     * @param resolveGroupPrincipal 是否解析来自群组的身份(默认为true)
     * @return 身份实例
     */
    @GET
    @Path("{username}/user_principals")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrincipalRepresentation> getUserPrincipals(@PathParam("username") String username,
                                                           @QueryParam("resolveGroupPrincipal") Boolean resolveGroupPrincipal) {
        return session.getProvider(PrincipalService.class).queryPrincipalsForUser(username);
    }

    /**
     * 为特定用户添加身份
     *
     * @param username      用户名
     * @param principalCode 身份代码
     */
    @POST
    @Path("{username}/user_principals/{principalCode}")
    @NoCache
    public void addUserPrincipal(@PathParam("username") String username,
                                 @PathParam("principalCode") String principalCode) {
        session.getProvider(PrincipalService.class).addUserPrincipal(username, principalCode);
    }

    /**
     * 移除特定用户的相应身份
     *
     * @param username      用户名
     * @param principalCode 身份代码
     */
    @DELETE
    @Path("{username}/user_principals/{principalCode}")
    @NoCache
    public void removeUserPrincipal(@PathParam("username") String username,
                                    @PathParam("principalCode") String principalCode) {

        session.getProvider(PrincipalService.class).removeUserPrincipal(username, principalCode);
    }

    /**
     * 查看某个群组具有多少身份
     * <p>
     * TODO 需要支持分页和搜索
     *
     * @param groupId 群组的ID
     * @return 身份实例
     */
    @GET
    @Path("{groupId}/group_principals")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrincipalRepresentation> getGroupPrincipals(@PathParam("groupId") String groupId) {
        return session.getProvider(PrincipalService.class).queryPrincipalsForGroup(groupId);
    }

    /**
     * 为特定群组添加身份
     *
     * @param groupId       群组ID
     * @param principalCode 身份代码
     */
    @POST
    @Path("{groupId}/group_principals/{principalCode}")
    @NoCache
    public void addGroupPrincipal(@PathParam("groupId") String groupId,
                                  @PathParam("principalCode") String principalCode) {
        session.getProvider(PrincipalService.class).addGroupPrincipal(groupId, principalCode);
    }

    /**
     * 移除特定群组的相应身份
     *
     * @param groupId       群组ID
     * @param principalCode 身份代码
     */
    @DELETE
    @Path("{groupId}/group_principals/{principalCode}")
    @NoCache
    public void removeGroupPrincipal(@PathParam("groupId") String groupId,
                                     @PathParam("principalCode") String principalCode) {
        session.getProvider(PrincipalService.class).removeGroupPrincipal(groupId, principalCode);
    }


}
