package com.callor.todolist.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.todolist.config.DBInfo;
import com.callor.todolist.model.TodoVO;
import com.callor.todolist.service.TodoListService;
import com.callor.todolist.service.impl.TodoListServiceImplV1;


@WebServlet("/todolist/*")
public class TodoListController extends HttpServlet{

	protected TodoListService tdService;
	
	public TodoListController() {
		tdService = new TodoListServiceImplV1();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String subPath = req.getPathInfo();
		if(subPath.equals("/home")) {
			String strSeq = req.getParameter("td_seq");
			Long td_seq = Long.valueOf(strSeq);
			TodoVO tdVO = tdService.findById(td_seq);
			
			SimpleDateFormat sd 
			= new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat st 
			= new SimpleDateFormat("HH:mm:ss");
			
			Date date 
			= new Date(System.currentTimeMillis());
			
			tdVO.setTd_seq(0L);
			tdVO.setTd_date(sd.format(date));
			tdVO.setTd_time(st.format(date));
			
			req.setAttribute("TD", tdVO);
			
		}
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
		if(subPath.equals("/home")) {
			tdService.insert(tdVO);
			resp.sendRedirect("/todolist/");
			
		}
	}
}
