package se.team_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import se.team_management.models.auth.AuthRegister;
import se.team_management.models.auth.AuthRequst;
import se.team_management.models.Employee;
import se.team_management.models.auth.JwtResponse;
import se.team_management.servises.EmployeeDAO;
import se.team_management.util.JwtUtil;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1")
public class HomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmployeeDAO employeeDAO;

    @PostMapping("/login")
    public ResponseEntity<?> generateJwToken(@RequestBody AuthRequst authRequst) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequst.getUsername(),authRequst.getPassword())
            );
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new JwtResponse(jwtUtil.generateToken(authRequst.getUsername()),employeeDAO.findByUsername(authRequst.getUsername())));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthRegister authRegister) {
        if (employeeDAO.findIfExistsByUsername(authRegister.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Username is already taken!");
        }

        if (employeeDAO.findIfExistsByEmail(authRegister.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email is already in use!");
        }

        Employee employee = new Employee(authRegister.getUsername(),
                authRegister.getEmail(),
                authRegister.getPassword());

        return ResponseEntity.ok().body(employeeDAO.save(employee));
    }

    @GetMapping()
    public ResponseEntity<String> getServerMessage(){
        return ResponseEntity.ok().body("{ \"message\": \"Hello From The Server!\"}");
    }
}
