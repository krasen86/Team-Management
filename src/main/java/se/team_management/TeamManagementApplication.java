package se.team_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import se.team_management.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class TeamManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamManagementApplication.class, args);
    }

}
