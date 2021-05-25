package com.callor.todolist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.todolist.config.DBConnection;
import com.callor.todolist.config.DBInfo;
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String subPath = req.getPathInfo();
		
		String td_work = req.getParameter(DBInfo.td_work);
		String td_date = req.getParameter(DBInfo.td_date);
		String td_time = req.getParameter(DBInfo.td_time);
		String td_place = req.getParameter(DBInfo.td_place);
		
		TodoVO tdVO = new TodoVO();
		tdVO.setTd_date(td_date);
		tdVO.setTd_time(td_time);
		tdVO.setTd_work(td_work);
		tdVO.setTd_place(td_place);
		
		System.out.println(tdVO.toString());
		tdService.update(tdVO);
		
		
	}
	
}
