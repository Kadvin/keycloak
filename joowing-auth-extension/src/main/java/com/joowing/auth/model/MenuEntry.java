package com.joowing.auth.model;

import javax.persistence.*;

/**
 * 菜单/应用入口模型
 */
@Entity
@Table(name = "JOOWING_PRINCIPALS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ID"})
})
@NamedQueries(
        {
                @NamedQuery(name="findByType", query="SELECT pf FROM PrincipalFactory pf WHERE  pf.type = :type "),
        }
)
public class MenuEntry {
    @Id
    @Column(name = "ID", length = 36)
    @Access(AccessType.PROPERTY)
    // we do this because relationships often fetch id, but not entity.  This avoids an extra SQL
    private String id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRINCIPAL_TYPE")
    private PrincipalFactory principalFactory;

    @Column(name = "CODE")
    private String code;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ICON")
    private String icon;

    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "DESCRIPTION")
    private String description;

    //关联的授权码
    @Column(name = "AUTHORIZATION_SCOPE_NAME")
    private String authorizationScopeName;

    // 用于外部解析这个菜单项的跳转/进入方式
    // 可能是一个url，可能是一个state，可能是某种其他能够指导应用跳转的
    // TODO 映射JSON
    @Column(name = "CONTEXT")
    private String context;
}
