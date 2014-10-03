package com.prime.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 
 * @author prime
 */
@Entity
@Table(name="ADDRESS")
public class Address extends BaseEntity implements Serializable {
        
	private static final long serialVersionUID = 1L;

		@TableGenerator(name = "Address_Gen", 
                    table = "ID_GEN", 
                    pkColumnName = "GEN_NAME", 
                    valueColumnName = "GEN_VAL", 
                    pkColumnValue = "Address_Gen", 
                    initialValue = 100000, 
                    allocationSize = 1)    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Address_Gen")    
    private Integer id;

    @Column(length = 50)
    private String street;
    
    @Column(length = 50)
    private String suburb;
    
    @Column(length = 50)
    private String city;
    
    @Column(length = 50)
    private String country;

    public Address() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return this.suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}