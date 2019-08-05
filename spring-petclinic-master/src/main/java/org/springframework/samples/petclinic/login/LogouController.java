/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.login;

import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author SPEK1
 */
public class LogouController {
    
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/welcome";
    }
    
    
}
