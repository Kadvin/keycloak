package com.joowing.auth.spi;

import com.joowing.auth.represent.PrincipalFactoryRepresentation;
import org.keycloak.provider.Provider;

import java.util.List;

/**
 * 身份工厂服务
 */
public interface PrincipalFactoryService extends Provider {
    List<PrincipalFactoryRepresentation> listFactories(String clientId);

    PrincipalFactoryRepresentation create(PrincipalFactoryRepresentation factoryRepresentation);

    PrincipalFactoryRepresentation update(String factoryType, PrincipalFactoryRepresentation factoryRepresentation);

    void delete(String factoryType);
}
