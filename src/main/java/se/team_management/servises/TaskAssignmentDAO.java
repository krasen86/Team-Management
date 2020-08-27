package se.team_management.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.team_management.models.TaskAssignment;
import se.team_management.repository.TaskAssignmentRepository;

@Service
public class TaskAssignmentDAO {

    @Autowired
    private TaskAssignmentRepository taskAssignmentRepository;

    public boolean findIfExistsByEmployeeIdAndTaskId(Integer employeeId, Integer taskId){
        return taskAssignmentRepository.existsByEmployeeIdAndTaskId(employeeId,taskId);
    }

    public TaskAssignment save(TaskAssignment newTaskAssignment) {
        return taskAssignmentRepository.save(newTaskAssignment);
    }
}
