package net.sytes.solovey.todoapp.controllers;

import net.sytes.solovey.todoapp.models.Task;
import net.sytes.solovey.todoapp.repos.TaskRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/task")
public class TaskController {
    private final TaskRepo taskRepo;

    @Autowired
    public TaskController(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @GetMapping
    public List<Task> getTasksList() {
        return taskRepo.findAll();
    }

    @GetMapping("{id:\\d+}")
    public ResponseEntity getTask(@PathVariable("id") Integer id) {
        Optional<Task> task = taskRepo.findById(id);
        return task.map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("NOT FOUND", HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Task addNewTask(@RequestBody Task task) {
        task.setId(null);
        return taskRepo.save(task);
    }

    @PutMapping("{id:\\d+}")
    public ResponseEntity updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        Optional<Task> taskFromDb = taskRepo.findById(id);
        return taskFromDb.map(t -> {
            BeanUtils.copyProperties(task, t, "id", "creationDate");
            return new ResponseEntity<>(taskRepo.save(t), HttpStatus.OK);
        })
                .orElseGet(() -> new ResponseEntity("NOT FOUND", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id:\\d+}")
    public ResponseEntity deleteTask(@PathVariable("id") Integer id) {
        Optional<Task> task = taskRepo.findById(id);
        return task.map(t -> {
            taskRepo.delete(t);
            return new ResponseEntity("OK", HttpStatus.OK);
        })
                .orElseGet(() -> new ResponseEntity("NOT FOUND", HttpStatus.NOT_FOUND));
    }
}
