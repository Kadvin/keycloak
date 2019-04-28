package com.joowing.auth.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 身份工厂模型
 */
@Entity
@Table(name = "JOOWING_PRINCIPAL_FACTORIES", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"TYPE"})
})
@NamedQueries(
        {
                @NamedQuery(name="findByType", query="SELECT pf FROM PrincipalFactory pf WHERE  pf.type = :type "),
        }
)
public class PrincipalFactory {

    @Id
    @Column(name = "ID", length = 36)
    @Access(AccessType.PROPERTY)
    // we do this because relationships often fetch id, but not entity.  This avoids an extra SQL
    private String id;

    @Column(name = "REALM_ID")
    protected String realmId;

    @Column(name = "CLIENT_ID")
    protected String clientId;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DERIVING_CODE")
    private String derivingCode;

    @Column(name = "DERIVING_NAME")
    private String derivingName;

    @Column(name = "DERIVING_DESCRIPTION")
    private String derivingDescription;

    @Column(name = "RESOURCE_TYPE")
    private String resourceType;

    // TODO 映射JSON
    @Column(name = "RESOURCE_CONDITIONS")
    private String resourceConditions;

    @Column(name = "DEFAULT_ICON")
    private String defaultIcon;

    @Column(name = "DISABLED")
    private Boolean disabled;

    @OneToMany(fetch = FetchType.LAZY, cascade = {})
    @JoinTable(name = "JOOWING_PRINCIPALS", joinColumns = @JoinColumn(name = "TYPE"), inverseJoinColumns = @JoinColumn(name = "PRINCIPAL_TYPE"))
    private Set<Principal> associatedPrincipals = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {})
    @JoinTable(name = "JOOWING_MENU_ENTRIES", joinColumns = @JoinColumn(name = "TYPE"), inverseJoinColumns = @JoinColumn(name = "PRINCIPAL_TYPE"))
    private Set<Principal> associatedMenuEntries = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDerivingCode() {
        return derivingCode;
    }

    public void setDerivingCode(String derivingCode) {
        this.derivingCode = derivingCode;
    }

    public String getDerivingName() {
        return derivingName;
    }

    public void setDerivingName(String derivingName) {
        this.derivingName = derivingName;
    }

    public String getDerivingDescription() {
        return derivingDescription;
    }

    public void setDerivingDescription(String derivingDescription) {
        this.derivingDescription = derivingDescription;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceConditions() {
        return resourceConditions;
    }

    public void setResourceConditions(String resourceConditions) {
        this.resourceConditions = resourceConditions;
    }

    public String getDefaultIcon() {
        return defaultIcon;
    }

    public void setDefaultIcon(String defaultIcon) {
        this.defaultIcon = defaultIcon;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Set<Principal> getAssociatedPrincipals() {
        return associatedPrincipals;
    }

    public void setAssociatedPrincipals(Set<Principal> associatedPrincipals) {
        this.associatedPrincipals = associatedPrincipals;
    }

    public Set<Principal> getAssociatedMenuEntries() {
        return associatedMenuEntries;
    }

    public void setAssociatedMenuEntries(Set<Principal> associatedMenuEntries) {
        this.associatedMenuEntries = associatedMenuEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrincipalFactory that = (PrincipalFactory) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
