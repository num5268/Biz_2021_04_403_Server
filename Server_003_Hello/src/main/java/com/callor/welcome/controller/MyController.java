package com.callor.welcome.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/num5268")
public class MyController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		out.print("만들었던 홈>>\n");
		out.print("<a href='");
		out.print("http://localhost");
		out.print(":8080");
		out.print("/welcome/home'>");
		out.print("홈으로</a>\n");
		
		out.println("만들었던 북>>\n");
		out.println("<a href='");
		out.println("http://localhost");
		out.println(":8080");
		out.println("/welcome/book'>");
		out.println("북으로</a>\n");
		
		
	}
	
	

}
