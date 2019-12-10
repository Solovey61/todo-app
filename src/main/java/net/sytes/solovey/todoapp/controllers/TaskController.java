package net.sytes.solovey.todoapp.controllers;

import net.sytes.solovey.todoapp.APIResponse;
import net.sytes.solovey.todoapp.models.Task;
import net.sytes.solovey.todoapp.repos.TaskRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/task")
public class TaskController {
    private final TaskRepo taskRepo;
    private final String RESPONSE_NOT_FOUND_MESSAGE = "NOT FOUND";
    private final String RESPONSE_OK_MESSAGE = "OK";

    @Autowired
    public TaskController(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @GetMapping
    public List<Task> getTasksList() {
        return taskRepo.findAll();
    }

    @GetMapping("{id:\\d+}")
    public ResponseEntity<Object> getTask(@PathVariable("id") Integer id) {
        Optional<Task> task = taskRepo.findById(id);
        return task.isPresent()
                ? APIResponse.getAPIOkResponse(task.get())
                : APIResponse.getAPINotFoundResponse(RESPONSE_NOT_FOUND_MESSAGE);
    }

    @PostMapping
    public ResponseEntity<Object> addNewTask(@RequestBody Task task) {
        task.setId(null);
        taskRepo.save(task);
        return APIResponse.getAPIOkResponse(task);
    }

    @PutMapping("{id:\\d+}")
    public ResponseEntity<Object> updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        Optional<Task> taskFromDb = taskRepo.findById(id);
        if (taskFromDb.isPresent()) {
            BeanUtils.copyProperties(task, taskFromDb.get(), "id", "creationDate");
            taskRepo.save(taskFromDb.get());
            return APIResponse.getAPIOkResponse(taskFromDb.get());
        }
        return APIResponse.getAPINotFoundResponse(RESPONSE_NOT_FOUND_MESSAGE);
    }

    @DeleteMapping("{id:\\d+}")
    public ResponseEntity<Object> deleteTask(@PathVariable("id") Integer id) {
        Optional<Task> task = taskRepo.findById(id);
        if (task.isPresent()) {
            taskRepo.delete(task.get());
            return APIResponse.getAPIOkResponse(RESPONSE_OK_MESSAGE);
        }
        return APIResponse.getAPINotFoundResponse(RESPONSE_NOT_FOUND_MESSAGE);
    }
}
