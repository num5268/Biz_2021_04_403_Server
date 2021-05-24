package com.callor.todolist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.todolist.config.DBConnection;
import com.callor.todolist.model.TodoVO;
import com.callor.todolist.service.TodoListService;
import com.callor.todolist.service.impl.TodoListServiceImplV1;

@WebServlet("/")
public class HomeController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected TodoListService tdService;
	
	public HomeController() {
		tdService = new TodoListServiceImplV1();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		List<TodoVO> tdList = tdService.selectAll();
		req.setAttribute("TDLIST", tdList);
		req.getRequestDispatcher("/WEB-INF/views/home.jsp")
		.forward(req, resp);
	}
}
