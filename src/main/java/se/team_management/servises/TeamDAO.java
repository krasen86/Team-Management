package se.team_management.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.team_management.models.TeamUserDetails;
import se.team_management.models.User;
import se.team_management.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamDAO {

    @Autowired
    UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return  user.map(User::new).get();
    }
    
    public User findById(Integer id){
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + id));
        return  user.map(User::new).get();
    }

    public User findByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return user.map(User::new).get();
    }

//    public User findAllByFirstName(String firstName){
//        Optional<List<User>> user = userRepository.findAllByFirstName(email);
//        user.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + firstName));
//        return  user.map(User::new).get();
//    }



    public void delete(User user){
        userRepository.delete(user);
    }


}
