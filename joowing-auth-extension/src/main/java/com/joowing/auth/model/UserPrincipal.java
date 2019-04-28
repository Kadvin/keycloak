package com.joowing.auth.model;

import org.keycloak.models.jpa.entities.UserEntity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 用户的身份关系
 */
public class UserPrincipal {
    @Column(name = "REALM_ID")
    protected String realmId;

    @Column(name = "CLIENT_ID")
    protected String clientId;


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "PRINCIPAL_CODE")
    private Principal principal;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "USERNAME")
    private UserEntity user;
}
