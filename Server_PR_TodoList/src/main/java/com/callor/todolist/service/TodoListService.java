package com.callor.todolist.service;

import java.util.List;

import com.callor.todolist.model.TodoVO;

public interface TodoListService {

	public List<TodoVO> selectAll();
	public TodoVO findById(Long seq);

	public List<TodoVO> findBywork(String td_work);
	public List<TodoVO> findByDate(String td_date);

	public Integer insert(TodoVO tdVO);
	public Integer update(TodoVO tdVO);
	public Integer delete(Long seq);

}
