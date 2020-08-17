package se.team_management.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.team_management.models.Employee;
import se.team_management.models.Task;
import se.team_management.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskDAO {

    @Autowired
    TaskRepository taskRepository;

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findByTitle(String title){
        Task task = taskRepository.findByTitle(title);
        if (task == null){
            throw new UsernameNotFoundException("Task not found with title: " + task);
        }
        return  task;
    }

    public Task findById(Integer id){
        Optional<Task> task = taskRepository.findById(id);
        task.orElseThrow(() -> new UsernameNotFoundException("Task not found with ID: " + id));
        return  task.map(Task::new).get();
    }

    public boolean findIfExistsByTitle(String title){
        return taskRepository.existsByTitle(title);
    }

    public void delete(Task task){
        taskRepository.delete(task);
    }

}
