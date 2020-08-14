package se.team_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import se.team_management.models.AuthRequst;
import se.team_management.util.JwtUtil;

@RestController
public class HomeController {


    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

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

    @PostMapping("/login")
    public String generateJwToken(@RequestBody AuthRequst authRequst) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequst.getUserName(),authRequst.getPassword())
            );
        }
        catch (Exception e){
            throw new Exception("Invalid Username or Password");
        }

        return jwtUtil.generateToken(authRequst.getUserName());
    }
}
