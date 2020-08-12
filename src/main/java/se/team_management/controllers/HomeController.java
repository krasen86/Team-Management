package se.team_management.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/")
    public String getHomePage(){
        return "Hello World!";
    }

    @GetMapping("/users")
    public String getUsersPage(){
        return "Hello User!";
    }

    @GetMapping("/admins")
    public String getAdminsPage(){
        return "Hello Admin!";
    }
}
