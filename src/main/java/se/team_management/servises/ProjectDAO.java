package se.team_management.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.team_management.models.Project;
import se.team_management.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectDAO {


    @Autowired
    private ProjectRepository projectRepository;

    public Project save(Project project){
        return projectRepository.save(project);
    }

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public Project findByName(String name){
        Project project = projectRepository.findProjectByName(name);
        if (project == null){
            throw new UsernameNotFoundException("Project not found with name: " + name);
        }
        return  project;
    }

    public Project findById(Integer id){
        Optional<Project> project = projectRepository.findById(id);
        project.orElseThrow(() -> new UsernameNotFoundException("Project not found with ID: " + id));
        return  project.map(Project::new).get();
    }

    public boolean findIfExistsByName(String name){
        return projectRepository.existsByName(name);
    }

    public void delete(Project project){
        projectRepository.delete(project);
    }

}
