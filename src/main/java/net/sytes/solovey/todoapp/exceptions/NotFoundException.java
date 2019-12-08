package net.sytes.solovey.todoapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
	public NotFoundException(Integer id) {
		super(String.format("Task with id %d was not found", id));
	}
}
