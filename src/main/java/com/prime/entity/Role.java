package com.prime.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * Role
 * 
 * @author prime
 */
@Entity
@Table(name="ROLES")
@NamedQueries({@NamedQuery(name = Role.ALL, query = "SELECT r FROM Role r")})
public class Role extends BaseEntity implements Serializable {
    
	private static final long serialVersionUID = 1L;

	public static final String ALL = "Role.populateRoles";
   
    @Id
    @GeneratedValue
    private Integer id;
    private String roledesc;
    private String rolename;

    public Role() {
    }

    public Role(Integer roleid, String rolename) {
        this.rolename = rolename;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public String getRoledesc() {
        return this.roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    public String getRolename() {
        return this.rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }  

}