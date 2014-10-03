package com.prime.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * User Entity
 * 
 * @author prime
 */
@Entity
@Table(name="USERS")
@NamedQueries({    
    @NamedQuery(name = User.EMAIL, query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = User.TOKEN, query = "SELECT u FROM User u WHERE u.token = :token AND u.username = :username"),
    @NamedQuery(name = User.ALL, query = "SELECT u FROM User u WHERE UPPER(u.username) LIKE :globalFilter OR UPPER(u.firstname) LIKE :globalFilter OR UPPER(u.lastname) LIKE :globalFilter OR UPPER(u.email) LIKE :globalFilter"),
    @NamedQuery(name = User.TOTAL, query = "SELECT COUNT(u) FROM User u WHERE UPPER(u.username) LIKE :globalFilter OR UPPER(u.firstname) LIKE :globalFilter OR UPPER(u.lastname) LIKE :globalFilter OR UPPER(u.email) LIKE :globalFilter")})
public class User extends BaseEntity implements Serializable {

    public static final String EMAIL ="User.findByEmail";
    public static final String TOKEN = "User.findByToken";
    public static final String ALL = "User.populateUsers";
    public static final String TOTAL = "User.countUsersTotal";
    
    private static final long serialVersionUID = 1L;

    @TableGenerator(name = "Users_Gen", 
                    table = "ID_GEN", 
                    pkColumnName = "GEN_NAME", 
                    valueColumnName = "GEN_VAL", 
                    pkColumnValue = "Users_Gen", 
                    initialValue = 100000, 
                    allocationSize = 1)    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Users_Gen")    
    private Integer id;
    
    @NotNull
    @Column(name="USERNAME")
    private String username;
    
    @Column(name="FIRST_NAME")
    private String firstname;
    
    @Column(name="LAST_NAME")
    private String lastname;
    
    @Column(name="EMAIL")
    private String email;
    
    @Column(name="PASSWORD")
    private String password;
    
    @Column(name="TOKEN")
    private String token;
    
    @Temporal(TemporalType.DATE)
    @Column(name="TOKEN_DATE")
    private Date tokenValidDate;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;
    
    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "User_userid")}, inverseJoinColumns = {
        @JoinColumn(name = "Role_roleid")})
    private List<Role> roles;
    
    public User() {
        roles = new ArrayList<>();
        address = new Address();
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenValidDate() {
        return tokenValidDate;
    }

    public void setTokenValidDate(Date tokenValidDate) {
        this.tokenValidDate = tokenValidDate;
    }
    
    
}