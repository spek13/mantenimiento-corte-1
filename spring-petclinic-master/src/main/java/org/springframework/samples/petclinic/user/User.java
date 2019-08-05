/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

/**
 *
 * @author SPEK1
 */
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public boolean isNew() {
        return this.id == null;
    }
    
    @Column(name = "name")
    @NotEmpty
    private String name;
    
    @Column(name = "lastname")
    @NotEmpty
    private String lastname;
    
    @Column(name = "email")
    @NotEmpty
    private String email;
    
    @Column(name = "password")
    @NotEmpty
    private String password;
    
    @Column(name = "status")
    @NotEmpty
    private String status;
    
    
    @Column(name = "avatar")
    @NotEmpty
    private String avatar;
    
    
    public String getStatus(){
    return status; 
    }
     public void setStatus(String status){
         this.status = status;
     }
             
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId()).append("new", this.isNew())
                .append("name", this.getName())
                .append("email", this.getEmail())
                .append("status", this.getStatus()).append("avatar", this.getAvatar())
                .toString();
    }
}
