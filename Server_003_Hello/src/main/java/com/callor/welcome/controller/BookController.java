package com.callor.welcome.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/book")
public class BookController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("this is book Controller");
		out.println("<a href='");
		out.println("http://localhost");
		out.println(":8080");
		out.println("/welcome/home'>");
		out.println("홈으로</a>");
		
		out.println("만들었던 num5268>>\n");
		out.println("<a href='");
		out.println("http://localhost");
		out.println(":8080");
		out.println("/welcome/num5268'>");
		out.println("num5268으로</a>");
		out.close();
		
	}
	
	

}
