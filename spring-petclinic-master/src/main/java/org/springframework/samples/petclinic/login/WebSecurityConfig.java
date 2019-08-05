/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.user.SeriviceAuthentication;
import org.springframework.samples.petclinic.user.UserSession;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 *
 * @author SPEK1
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("user").password("{noop}mopa251946").roles("USER")
//            .and()
//            .withUser("admin").password("{noop}mopa251946").roles("ADMIN")
//              ;
//    }
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
//            .authorizeRequests()
//            .antMatchers("/","/login","/login-error","/css/**", "/js/**", "/images/**", "/webjars/**")
//            .permitAll()
//            .anyRequest()
//            .fullyAuthenticated()
//            .and()
//            .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .failureUrl("/login?error=true")
//                .defaultSuccessUrl("/welcome")
//                .usernameParameter("username")
//                .passwordParameter("password")
//            .and()
//            .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//            ;
        
        .authorizeRequests()
            .antMatchers("/","/login","/login-error","/css/**", "/js/**", "/images/**", "/webjars/**")
            .permitAll()
            .anyRequest()
            .fullyAuthenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error=true")
            .defaultSuccessUrl("/welcome")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            ;
        httpSecurity.csrf().disable();
    }
    
    @Autowired
    SeriviceAuthentication servicioAut;
    
    @Autowired
    BCryptPasswordEncoder password;
    
//    @Autowired
//    LoginRepository repositorioLoginSession;
//    
//    @Autowired
//    UserRepository repositorioUser;
    
    @Bean
    public BCryptPasswordEncoder passwordCrypt(){
        BCryptPasswordEncoder codePassword = new BCryptPasswordEncoder();
     return codePassword;
    }
    
    @Bean
    public DaoAuthenticationProvider passwordAuthication(){
        DaoAuthenticationProvider validarPass = new DaoAuthenticationProvider();
        validarPass.setUserDetailsService(servicioAut);
        validarPass.setPasswordEncoder(passwordCrypt());
       
    return validarPass;
    }
    
    protected void validar(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(servicioAut).passwordEncoder(password);
      
    }
    
    


}



