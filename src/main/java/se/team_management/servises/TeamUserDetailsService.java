package se.team_management.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.team_management.models.TeamUserDetails;
import se.team_management.models.User;
import se.team_management.repository.UserRepository;

import java.util.Optional;

@Service
public class TeamUserDetailsService implements UserDetailsService {

    @Autowired
    TeamDAO teamDAO;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = teamDAO.findByUsername(username);
        return new TeamUserDetails(user);
    }
}
