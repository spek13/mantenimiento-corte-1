/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.login;

import java.util.Collection;
import java.util.Map;
import org.springframework.samples.petclinic.user.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author SPEK1
 */
@Controller
public class LoginController {
    
//    private final LoginRepo loginRepository;
//    public LoginController(LoginRepo loginRepository){
//        this.loginRepository = loginRepository;
//    }
    
    @GetMapping({"/","/login"})
    public String login() {
        return "login";
    }
    
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
    
//    @GetMapping("/reporte/login")
//    public String reporteLogin(){
////        UserSession userSession, BindingResult result, Map<String, Object> model
////        Collection<UserSession> results = this.loginRepository.findAll();
////        model.put("selections",results);
//    return "ReporteLoginSession";
//    }
    
    
    
}
