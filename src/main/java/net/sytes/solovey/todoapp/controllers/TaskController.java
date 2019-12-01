package net.sytes.solovey.todoapp.controllers;

import net.sytes.solovey.todoapp.models.Task;
import net.sytes.solovey.todoapp.repos.TaskRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
		Iterable<Task> all = taskRepo.findAll();
		List<Task> tasks = new ArrayList<>();
		all.forEach(tasks::add);
		return tasks;
	}

	@GetMapping("{id}")
	public Task getTask(@PathVariable("id") Task task) {
		return task;
	}

	@PostMapping
	public Task addNewTask(@RequestBody Task task) {
		return taskRepo.save(task);
	}

	@PutMapping("{id}")
	public Task updateTask(@PathVariable("id") Task taskFromDb, @RequestBody Task task) {
		BeanUtils.copyProperties(task, taskFromDb, "id", "creationDate");
		return taskRepo.save(taskFromDb);
	}

	@DeleteMapping("{id}")
	public void deleteTask(@PathVariable("id") Task task) {
		taskRepo.delete(task);
	}
}
