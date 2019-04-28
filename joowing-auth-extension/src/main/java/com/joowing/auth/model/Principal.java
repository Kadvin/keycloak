package com.joowing.auth.model;

import javax.persistence.*;

/**
 * 身份模型
 *
 *   身份来自于 PrincipalFactory * Resource
 *   身份用于与User/Group进行关联
 */
public class Principal {
    @Id
    @Column(name = "ID", length = 36)
    @Access(AccessType.PROPERTY)
    // we do this because relationships often fetch id, but not entity.  This avoids an extra SQL
    private String id;

    @Column(name = "REALM_ID")
    protected String realmId;

    @Column(name = "CLIENT_ID")
    protected String clientId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRINCIPAL_TYPE")
    private PrincipalFactory principalFactory;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RESOURCE_TYPE")
    private String resourceType;

    @Column(name = "RESOURCE_CODE")
    private String resourceCode;

    @Column(name = "RESOURCE_NAME")
    private String resourceName;

    @Column(name = "ICON")
    private String icon;
}
