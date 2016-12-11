package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo extends HttpServlet {
	 private static final long serialVersionUID = 1L;
     
	    public ServletDemo() {
	        super();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");  
	        response.setContentType("text/html;charset=utf-8");
	        
	        String action = request.getParameter("action");  
	        if("login_input".equals(action)) {  
	            request.getRequestDispatcher("login.jsp").forward(request , response);  
	        } else if("login".equals(action)) {  
	            String name = request.getParameter("name");  
	            String password = request.getParameter("password");  
	            
	            //System.out.println("name->" + name + ",password->" + password);
	            System.out.println(request.getRequestURI());
	            request.setAttribute("name1", name);
	            request.getRequestDispatcher("login.jsp").forward(request , response);
	            //PrintWriter out = response.getWriter();
	            //out.print("name->" + name + ",password->" + password);

	        }
	    }

}
