package com.prime.entity;

import java.io.Serializable;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.servlet.http.HttpServletRequest;

/**
 * Super Entity class
 *
 * @author prime
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "DEL_IND")
    private boolean deleted = Boolean.FALSE;

    @Embedded
    private Stamp stamp;

    @Version
    @Column(name = "VERSION")
    private Integer version = 0;
    
    public Integer getVersion() {
        return version;
    }

    protected void setVersion(Integer version) {
        this.version = version;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted() {
        this.deleted = Boolean.TRUE;
    }

    public Stamp getStamp() {
        return stamp;
    }

    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
    }
    
    private String getUser(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(request.getUserPrincipal()!= null){
            return request.getUserPrincipal().getName();
        }else{
            return null;
        }
    }

    @PrePersist
    public void prePersist() {
        if (this.stamp == null) {
            this.stamp = new Stamp();
        }
        this.stamp.setCreatedDate(new Date());
        this.stamp.setCreatedUser(getUser());
    }

    @PreUpdate
    public void preUpdate() {
        if (this.stamp == null) {
            this.stamp = new Stamp();
        }
        this.stamp.setModifiedDate(new Date());
        this.stamp.setModifiedUser(getUser());
    }
    
}
