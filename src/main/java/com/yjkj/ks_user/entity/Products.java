package com.yjkj.ks_user.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: Create By DaDa
 * @Date: 2019/3/25 16:55
 */
@Table(name = "t_mp_products")
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "merChant")
    private String merChant;
    @Column(name = "institution")
    private String institution;
    @Column(name = "merType")
    private String merType;
    @Column(name = "type")
    private String type;
    @Column(name = "typeName")
    private String typeName;
    @Column(name = "appId")
    private String appId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerChant() {
        return merChant;
    }

    public void setMerChant(String merChant) {
        this.merChant = merChant;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getMerType() {
        return merType;
    }

    public void setMerType(String merType) {
        this.merType = merType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
