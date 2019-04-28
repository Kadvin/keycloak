package com.joowing.auth.rest;

import com.joowing.auth.represent.PrincipalFactoryRepresentation;
import com.joowing.auth.spi.PrincipalFactoryService;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.KeycloakSession;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 身份工厂
 */
@Path("{realmId}/clients/{clientId}/principal_factories")
public class PrincipalFactoryResource {
    private final KeycloakSession session;
    @PathParam("realmId")
    private String realmId;
    @PathParam("clientId")
    private String clientId;

    public PrincipalFactoryResource(KeycloakSession session) {
        this.session = session;
    }

    /**
     * 获取相应客户端下面有多少个身份工厂
     *
     * @return 身份工厂
     */
    @GET
    @NoCache
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrincipalFactoryRepresentation> getPrincipalFactories() {
        return session.getProvider(PrincipalFactoryService.class).listFactories(clientId);
    }

    /**
     * 创建身份工厂
     *
     * @return 身份工厂
     */
    @POST
    @NoCache
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PrincipalFactoryRepresentation create(PrincipalFactoryRepresentation factoryRepresentation){
        return session.getProvider(PrincipalFactoryService.class).create(factoryRepresentation);
    }

    /**
     * 修改特定身份工厂
     *
     * @return 身份工厂
     */
    @PUT
    @Path("/{factoryType}")
    @NoCache
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PrincipalFactoryRepresentation update(@PathParam("factoryType") String factoryType, PrincipalFactoryRepresentation factoryRepresentation){
        return session.getProvider(PrincipalFactoryService.class).update(factoryType, factoryRepresentation);
    }

    /**
     * 删除特定身份工厂
     *
     */
    @DELETE
    @Path("/{factoryType}")
    @NoCache
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("factoryType") String factoryType){
        session.getProvider(PrincipalFactoryService.class).delete(factoryType);
    }

}
