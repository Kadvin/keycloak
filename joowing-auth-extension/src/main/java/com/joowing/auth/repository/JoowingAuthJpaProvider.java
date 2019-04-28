package com.joowing.auth.repository;

import com.joowing.auth.model.*;
import org.keycloak.connections.jpa.entityprovider.JpaEntityProvider;

import java.util.Arrays;
import java.util.List;

/**
 * PrincipalFactory的Jpa Entity Provider
 */
public class JoowingAuthJpaProvider implements JpaEntityProvider {
    @Override
    public List<Class<?>> getEntities() {
        return Arrays.asList(
                PrincipalFactory.class,
                Principal.class,
                UserPrincipal.class,
                GroupPrincipal.class,
                MenuEntry.class
        );
    }

    @Override
    public String getChangelogLocation() {
        return "META-INF/joowing-auth-changelog-1.0.0.xml";
    }

    @Override
    public String getFactoryId() {
        return JoowingAuthJpaProviderFactory.ID;
    }

    @Override
    public void close() {

    }
}
