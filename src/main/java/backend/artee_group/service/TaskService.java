package backend.artee_group.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.artee_group.dto.TaskDTO.TaskRequest;
import backend.artee_group.dto.TaskDTO.TaskResponse;
import backend.artee_group.entity.Task;
import backend.artee_group.exception.FuncErrorException;
import backend.artee_group.repository.TaksRepository;

@Service
public class TaskService {

    @Autowired
    private TaksRepository taksRepository;

    public List<TaskResponse> getAllTasks() {
        try {
            List<Task> tasks = taksRepository.findAll();
            return tasks.stream().map(task -> new TaskResponse(task.getId(), task.getTitle(), task.getCompleted(),
                    task.getCreatedAt(), task.getUpdatedAt())).toList();
        } catch (Exception e) {
            throw new FuncErrorException(e.getMessage());
        }
    }

    public TaskResponse getTaksById(Long id) {
        try {
            Task task = taksRepository.findById(id)
                    .orElseThrow(() -> new FuncErrorException("Task not found"));
            return new TaskResponse(task.getId(), task.getTitle(), task.getCompleted(), task.getCreatedAt(),
                    task.getUpdatedAt());
        } catch (Exception e) {
            throw new FuncErrorException(e.getMessage());
        }
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        try {
            Task task = new Task();
            task.setTitle(taskRequest.getTitle());
            Task savedTask = taksRepository.save(task);
            return new TaskResponse(savedTask.getId(), savedTask.getTitle(), savedTask.getCompleted(),
                    savedTask.getCreatedAt(), savedTask.getUpdatedAt());
        } catch (Exception e) {
            throw new FuncErrorException(e.getMessage());
        }
    }

    public TaskResponse updateComplated(Long id) {
        try {
            Task task = taksRepository.findById(id)
                    .orElseThrow(() -> new FuncErrorException("Task not found"));
            task.setCompleted(!task.getCompleted());
            Task savedTask = taksRepository.save(task);
            return new TaskResponse(savedTask.getId(), savedTask.getTitle(), savedTask.getCompleted(),
                    savedTask.getCreatedAt(), savedTask.getUpdatedAt());
        } catch (Exception e) {
            throw new FuncErrorException(e.getMessage());
        }
    }

    public Map<String, String> deleteTask(Long id) {
        try {
            Optional<Task> task = taksRepository.findById(id);
            if (task.isPresent()) {
                taksRepository.delete(task.get());
                return Map.of("message", "Task deleted successfully");
            } else {
                return Map.of( "message", "Task not found");
            }
        } catch (Exception e) {
            throw new FuncErrorException(e.getMessage());
        }
    }
}
