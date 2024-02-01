package com.example.springsecuritydemo.Controller;


import com.example.springsecuritydemo.Models.MyMessageSecure;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/home/")
@CrossOrigin("*") // Allow requests from all origins
public class HomeController {


    @GetMapping("welcomeUser")
    public MyMessageSecure welcomeUser(){
        return new MyMessageSecure("weclome salah-eddine you are User");
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("welcomeManager")
    public MyMessageSecure welcomeManager(){
        return new MyMessageSecure("weclome salah-eddine you are manager");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("welcomeAdmin")
    public MyMessageSecure welcomeAdmin(){
        return new MyMessageSecure("weclome salah-eddine you are Admin");
    }
    @GetMapping("welcomeGuest")
    public MyMessageSecure welcomeGuest(){
        return new MyMessageSecure("weclome salah-eddine you are Guest");
    }
}
