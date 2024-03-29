/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.user;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             



@Service
public class SeriviceAuthentication implements UserDetailsService{

    @Autowired
    UserRepository repositorioUser;
    
    @Autowired
    LoginRepository repositorioLoginSession;
    
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        
        org.springframework.samples.petclinic.user.User modelUser = repositorioUser.findbyName(name);//busqueda del usuario
        org.springframework.samples.petclinic.user.UserSession modelUserSession = new UserSession();
        List <GrantedAuthority> rol = new ArrayList <>();
        rol.add(new SimpleGrantedAuthority("admin"));
        
        if(modelUser.getStatus().equals("activo")){
            UserDetails userValidation = new User(modelUser.getName(),modelUser.getPassword(),rol);
            modelUserSession.setIdUser(modelUser.getId());
            modelUserSession.setUserName(modelUser.getName());
            modelUserSession.setSuccessful("SUCCESSFUL");
            repositorioLoginSession.save(modelUserSession);
            return userValidation;
        }
        UserDetails userValidation = new User(modelUser.getLastName(),modelUser.getEmail(),rol);
        modelUserSession.setIdUser(modelUser.getId());
        modelUserSession.setUserName(modelUser.getName());
        modelUserSession.setSuccessful("FAIL");
        repositorioLoginSession.save(modelUserSession);
        
        
    return userValidation;
    }
    
}
