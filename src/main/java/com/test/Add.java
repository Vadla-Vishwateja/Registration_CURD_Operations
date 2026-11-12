package com.test;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String name=request.getParameter("name");
		
		String pass=request.getParameter("pass");
		
		String age1=request.getParameter("age");
		
		int age=Integer.parseInt(age1);
		
		String loc=request.getParameter("loc");
		
		String url="jdbc:mysql://localhost:3306/registration";
		String user="root";
		String pass1="Vishwa@1107";
		
		String query = "INSERT INTO userdata VALUES (?, ?, ?, ?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url, user, pass1);
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, name);
			ps.setString(2, pass);
			ps.setInt(3, age);
			ps.setString(4, loc);
			int a=ps.executeUpdate();
			out.println("<h1>"+a+" row  inserted </h1>");
			out.println("<a href='index.html'>Go Back</a>");
			ps.close();
			conn.close();
		
		} catch (Exception e) {
			out.println("<h2>"+e.getMessage()+"</h2>");
		}


	}

}
