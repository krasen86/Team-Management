package se.team_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import se.team_management.models.AuthRequst;
import se.team_management.models.User;
import se.team_management.servises.TeamDAO;
import se.team_management.util.JwtUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/")
public class HomeController {


    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TeamDAO teamDAO;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(teamDAO.findAll());
    }

    @GetMapping("/users/ids/{id}")
    public ResponseEntity<User>  getUserByID(@PathVariable(value = "id") Integer id){
        return  ResponseEntity.ok().body(teamDAO.findById(id));
    }

    @GetMapping("/users/emails/{email}")
    public ResponseEntity<User>  getUserByEmail(@PathVariable(value = "email") String email){
        return  ResponseEntity.ok().body(teamDAO.findByEmail(email));
    }

    @GetMapping("/users/usernames/{username}")
    public ResponseEntity<User>  getUserByUsername(@PathVariable(value = "username") String username){
        return  ResponseEntity.ok().body(teamDAO.findByUsername(username));
    }

    @PostMapping("/users")
    public ResponseEntity<User>  createUser(@RequestBody User user){
        return  ResponseEntity.ok().body(teamDAO.save(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Integer id){
        User userToDelete = teamDAO.findById(id);
        if (userToDelete == null){
            return ResponseEntity.notFound().build();
        }
        teamDAO.delete(userToDelete);
        return ResponseEntity.ok().build();
    }



    @PutMapping("/users/{id}")
    public ResponseEntity<User>  updateUser(@PathVariable(value = "id") Integer id, @RequestBody User user){
        User userToModify = teamDAO.findById(id);
        if (userToModify == null){
            return ResponseEntity.notFound().build();
        }
        updateUserDetails(userToModify,user);
        return  ResponseEntity.ok().body(teamDAO.save(userToModify));
    }
    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUserPartially(@PathVariable(value = "id") Integer id, @RequestBody Map<Object,Object> userData){
        User userToModify = teamDAO.findById(id);
        if (userToModify == null){
            return ResponseEntity.notFound().build();
        }
        userData.forEach((k,v)->{
            Field field = ReflectionUtils.findField(User.class, (String) k);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field,userToModify,v);
        });
        return  ResponseEntity.ok().body(teamDAO.save(userToModify));
    }

    private void updateUserDetails(User userToModify, User user) {
        userToModify.setUsername(user.getUsername());
        userToModify.setEmail(user.getEmail());
        userToModify.setFirstName(user.getFirstName());
        userToModify.setLastName(user.getLastName());
        userToModify.setRoles(user.getRoles());
        userToModify.setPassword(user.getPassword());
        userToModify.setActive(user.isActive());
    }

    @PostMapping("/")
    public ResponseEntity<String> generateJwToken(@RequestBody AuthRequst authRequst) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequst.getUsername(),authRequst.getPassword())
            );
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(jwtUtil.generateToken(authRequst.getUsername()));
    }
}
