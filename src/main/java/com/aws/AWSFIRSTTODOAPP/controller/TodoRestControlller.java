package com.aws.AWSFIRSTTODOAPP.controller;

import com.aws.AWSFIRSTTODOAPP.entity.Todo;
import com.aws.AWSFIRSTTODOAPP.service.TodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

//@Tag(name = "Todo API", description = "Operations related to Todo management")

@RestController
public class TodoRestControlller {

	@Autowired
	private TodoService todoService;

	@PostMapping("/save")
//	@Operation(summary = "Save a new Todo", description = "Creates and saves a new Todo item")
	public ResponseEntity<?> save(@RequestBody Todo todo) {
		return new ResponseEntity<>(todoService.saveTodo(todo), HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
//	@Operation(summary = "Get Todo by ID", description = "Fetch a single Todo item by its ID")
	public ResponseEntity<?> getAllTodo() {
		return new ResponseEntity<>(todoService.getAllTodo(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
//	@Operation(summary = "Delete Todo by ID", description = "Deletes a Todo item by ID")
	public ResponseEntity<?> getTodoById(@PathVariable int id) {
		return new ResponseEntity<>(todoService.getByid(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
//	@Operation(summary = "Delete Todo by ID", description = "Delete an existing Todo item by ID")
	public ResponseEntity<?> deleteTodo(@PathVariable int id) {
		todoService.delete(id);
		return new ResponseEntity<>("Todo Delete sucessfully", HttpStatus.OK);
	}

	@PutMapping("/{id}")
//	@Operation(summary = "Update Todo by ID", description = "Updates an existing Todo item by ID")
	public ResponseEntity<?> updateTodo(@RequestBody Todo todo, @PathVariable int id) {
		return new ResponseEntity<>(todoService.updateTodo(id, todo), HttpStatus.OK);
	}

}