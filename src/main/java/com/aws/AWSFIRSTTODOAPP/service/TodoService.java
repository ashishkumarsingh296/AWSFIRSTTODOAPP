package com.aws.AWSFIRSTTODOAPP.service;

import com.aws.AWSFIRSTTODOAPP.entity.Todo;

import  java.util.List;


public interface TodoService {

	public Todo saveTodo(Todo todo);

	public List<Todo> getAllTodo();

	public Todo getByid(int id);

	public void delete(int id);

	public Todo updateTodo(int id, Todo todo);

}