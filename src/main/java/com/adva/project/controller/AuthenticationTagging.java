package com.adva.project.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


//Zawiera informacje o uwierzytelnieniu użytkownika
//w zależności od tego zmienia się widok strony
@ControllerAdvice
public class AuthenticationTagging {
    @ModelAttribute("isAuth")
    public boolean getAuth(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal!=null;
    }
    @ModelAttribute("employee")
    public boolean employeeCapabilities(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal!=null && principal.getName().equals("employee@mail.com") ? true: false;
    }
    @ModelAttribute("admin")
    public boolean adminCapabilities(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal!=null && principal.getName().equals("admin@mail.com") ? true: false;
    }
    @ModelAttribute("user")
    public String getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal!=null ? request.getUserPrincipal().getName() : "";
    }
}
