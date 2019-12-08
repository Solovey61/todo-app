package net.sytes.solovey.todoapp.controllers;

import net.sytes.solovey.todoapp.exceptions.NotFoundException;
import net.sytes.solovey.todoapp.models.Task;
import net.sytes.solovey.todoapp.repos.TaskRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@GetMapping("{id}")
	public Task getTask(@PathVariable("id") Integer id) {
		return taskRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(id));
	}

	@PostMapping
	public Task addNewTask(@RequestBody Task task) {
		return taskRepo.save(task);
	}

	@PutMapping("{id}")
	public Task updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
		Task taskFromDb = taskRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(id));
		BeanUtils.copyProperties(task, taskFromDb, "id", "creationDate");
		return taskRepo.save(taskFromDb);
	}

	@DeleteMapping("{id}")
	public void deleteTask(@PathVariable("id") Integer id) throws NotFoundException {
		taskRepo.delete(taskRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(id)));
	}
}
