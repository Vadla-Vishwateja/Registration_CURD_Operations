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
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class Read
 */
@WebServlet("/Read")
public class Read extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public Read() {
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
		
		String query="select * from userdata where name=?;";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url, user, pass);
			
			PreparedStatement st=conn.prepareStatement(query);
			
			st.setString(1, name);
			
			ResultSet rs = st.executeQuery();

			out.println("<table border='1' cellpadding='8' cellspacing='0'>");
			out.println("<tr>");
			out.println("<th>Name</th>");
			out.println("<th>Password</th>");
			out.println("<th>Age</th>");
			out.println("<th>Location</th>");
			out.println("</tr>");

			while (rs.next()) {
			    out.println("<tr>");
			    out.println("<td>" + rs.getString(1) + "</td>");
			    out.println("<td>" + rs.getString(2) + "</td>");
			    out.println("<td>" + rs.getInt(3) + "</td>");
			    out.println("<td>" + rs.getString(4) + "</td>");
			    out.println("</tr>");
			}

			out.println("</table><br><br><br><br>");
			
			
			out.println("<a href='index.html'>Go Back</a>");
			
			st.close();
			rs.close();
			conn.close();
		
		} catch (Exception e) {
			out.println("<h2>"+e.getMessage()+"</h2>");
		}

	}

}
