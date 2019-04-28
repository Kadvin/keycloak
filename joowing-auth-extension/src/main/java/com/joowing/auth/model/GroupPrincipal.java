package com.joowing.auth.model;

import org.keycloak.models.jpa.entities.GroupEntity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 用户组的身份
 */
public class GroupPrincipal {
    @Column(name = "REALM_ID")
    protected String realmId;

    @Column(name = "CLIENT_ID")
    protected String clientId;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "PRINCIPAL_CODE")
    private Principal principal;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

}
