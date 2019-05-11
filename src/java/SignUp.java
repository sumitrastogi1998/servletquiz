/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sumit
 */
@WebServlet(urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name=request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        HttpSession s = request.getSession();
        s.setAttribute("email", email);
        //s.setAttribute("name", name);
        try{
            Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/test";
	 String uname="root";
	 String password="sumit2123";
	 String query="insert into admin values (?,?,?)";
         
	 Connection con=DriverManager.getConnection(url,uname,password);
	 PreparedStatement ps=con.prepareStatement(query);
	 ps.setString(1,name);
	 ps.setString(2,email);
         ps.setString(3,pass);
	 int count=ps.executeUpdate();
	 out.println(count + "rows affected");
         //out.println("<br>");
         response.sendRedirect("Login.html");
         /*out.println("<form action=\"newjsp.jsp\">");
         out.println("<br><input type=\"button\" value=\"jsp\"\">"); 
         out.println("</form>");*/
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    
    // </editor-fold>
    }
    }


