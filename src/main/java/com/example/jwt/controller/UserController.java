package com.example.jwt.controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, authenticated user!";
    }
    /*
    //Dodaj rolę użytkownika do odpowiedzi
   //możesz zwrócić więcej informacji, np. 
   //aktualnie zalogowaną nazwę użytkownika i role
    @GetMapping("/hello")
    public String hello() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String username = auth.getName();
      String role = auth.getAuthorities().toString();

      return "Hello, " + username + "! Your role is: " + role;
     }
     //Dodaj role-based endpoint (np. dla admina)
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
     public String adminOnly() {
       return "Welcome admin!";
     }

    */
}