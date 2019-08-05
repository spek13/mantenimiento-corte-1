/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.user;

import java.sql.Timestamp;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;

/**
 *
 * @author SPEK1
 */
@Entity
@Table(name = "user_sessions")
public class UserSession extends BaseEntity {
    
    @Column(name = "id_user")
    private int idUser;
    
    @Column(name = "session_successful")
    private String successful;

    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "date")
    private Timestamp date;
    
    public Timestamp getDate(){
        return date;
    }

    public String getSuccessful() {
        return successful;
    }

    public void setSuccessful(String successful) {
        this.successful = successful;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    
    
    
}
