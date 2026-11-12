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
import java.sql.Statement;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public Delete() {
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
		
		
		
		
		String url="jdbc:mysql://localhost:3306/registration";
		String user="root";
		String pass="Vishwa@1107";
		
		String query="Delete from userdata where name=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url, user, pass);
			
			PreparedStatement st=conn.prepareStatement(query);
			
			st.setString(1, name);
			
			int a=st.executeUpdate();
			
			out.println("<h2>"+a+" row  Deleted </h2>");	
			out.println("<a href='index.html'>Go Back</a>");
			st.close();
			conn.close();
		
		} catch (Exception e) {
			out.println("<h2>"+e.getMessage()+"</h2>");
		}

	}

}
