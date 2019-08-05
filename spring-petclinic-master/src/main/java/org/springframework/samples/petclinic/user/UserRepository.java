/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.user;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SPEK1
 */
interface UserRepository extends Repository< User, Integer>{
    
    @Transactional(readOnly = true)
    Collection<User> findAll() throws DataAccessException;
    
    @Query("SELECT user FROM User user where user.id = :id")
    @Transactional(readOnly = true)
    User findbyId(@Param("id") Integer id);
    
    void save(User user)throws DataAccessException;
    
    
    @Transactional
    @Modifying
    @Query("DELETE FROM User user where user.id = :id")
    void deleteUsers(@Param("id")  Integer id);
    
    //metodo para busqueda y validacion de la base de datos
    @Query("SELECT user FROM User user where user.name = :name")
    @Transactional(readOnly = true)
    User findbyName(@Param("name") String user);
    
    @Query("SELECT user FROM User user where user.status = :status")
    @Transactional(readOnly = true)
    User findbyStatus(@Param("status") String status);
    
    
    
}