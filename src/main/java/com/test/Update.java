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
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public Update() {
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
		
		String field=request.getParameter("field");
		String newvalue=request.getParameter("new");
		
		
		
		
		String url="jdbc:mysql://localhost:3306/registration";
		String user="root";
		String pass="Vishwa@1107";
		
		
		if (!field.equals("name") &&
			    !field.equals("password") &&
			    !field.equals("age") &&
			    !field.equals("location")) {
			    out.println("<h2>Invalid field selected!</h2>");
			    return;
			}
		String query = "UPDATE userdata SET " + field + " = ? WHERE name = ?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url, user, pass);
			
			PreparedStatement st=conn.prepareStatement(query);
			
			if (field.equals("age")) {
			    st.setInt(1, Integer.parseInt(newvalue));
			} else {
			    st.setString(1, newvalue);
			}

			st.setString(2, name);
			
			int a=st.executeUpdate();
			
			if (a > 0) {
			    out.println("<h2>Data updated successfully!</h2>");
			} else {
			    out.println("<h2>No record found with that name.</h2>");
			}
			
			out.println("<a href='index.html'>Go Back</a>");
			st.close();
			conn.close();
		
		} catch (Exception e) {
			out.println("<h2>"+e.getMessage()+"</h2>");
		}

		
		
	}

}
