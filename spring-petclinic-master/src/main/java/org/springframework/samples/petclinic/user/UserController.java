/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.user;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author SPEK1
 */
    @Controller
    class UserController {
    
    private final UserRepository users;
    private final LoginRepository loginRepositorio;
    BCryptPasswordEncoder code = new    BCryptPasswordEncoder();
    public UserController(UserRepository user, LoginRepository loginRepositorio){
        this.users = user;
        this.loginRepositorio = loginRepositorio;
    }
    //lista usuario
    @GetMapping("/usuarios")
    public String userList(User user, BindingResult result, Map<String, Object> model){
        Collection<User> results = this.users.findAll();
        model.put("selections",results);
        return "users/UserList";
    }
    @GetMapping("/usuarios/reporte")
    public String userLista(User user, BindingResult result, Map<String, Object> model){
        Collection<User> results = this.users.findAll();
        model.put("selections",results);
        return "users/reporte";
    }
    
    @GetMapping("/reporte/login")
    public String reporteLogin(UserSession userSession, BindingResult result, Map<String, Object> model){
        Collection<UserSession> results = this.loginRepositorio.findAll();
        model.put("selections",results);
    return "ReporteLoginSession";
    }
    
    @GetMapping("/usuarios/create")
    public  String initCreationForm(Map<String, Object> model){
    User user = new User();
    model.put("user",user);
    return "users/CreateUpdateUsersForm";
    }
    
    @PostMapping("/usuarios/store")
    public String ProcessCreationForm(@Valid User user,BindingResult result ){
    user.setPassword(code.encode(user.getPassword()));
    this.users.save(user);
    return "redirect:/usuarios";
    }
    
    
    
    
    //editar Usuarios mostrar formualario de editar
    @GetMapping("/usuarios/{userId}/edit")
    public String initUpdateUserForm(@PathVariable("userId") int userId, Model model) {
        User user = this.users.findbyId(userId);
        model.addAttribute(user);
        return "users/edit"; 
    }
    
    @PostMapping("/usuarios/{userId}/edit")
    public String processUpdateUserForm(@Valid User user, BindingResult result, @PathVariable("userId") int userId){
     if (result.hasErrors()) {
           return "users/edit";
        } else {
            user.setId(userId);
            user.setPassword(code.encode(user.getPassword()));
            this.users.save(user);
            return "redirect:/usuarios";
        }

    }
    
    
    @PostMapping("/usuarios/{userId}/delete")
    public String deleteUsers(@Valid User user, BindingResult result, @PathVariable("userId") int userId) {
        this.users.deleteUsers(userId);
        return "redirect:/usuarios";
    }
 
 }