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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
         HttpSession s = request.getSession();
        s.setAttribute("email", email);
      //  s.setAttribute("name", name);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "sumit2123");
            PreparedStatement pst = conn.prepareStatement("Select email,pass from admin where email=? and pass=?");
            pst.setString(1, email);
            pst.setString(2, password);
            //out.print("<table width=25% border=1>");
            //out.print("<center><h1>Result:</h1></center>");
            ResultSet rs = pst.executeQuery();
            //ResultSetMetaData rsmd=rs.getMetaData();
            if (rs.next()) {
              response.sendRedirect("quiz.html");
               // out.println("Correct login credentials");
            } 
            else {
                //out.println("Incorrect login credentials");
                response.sendRedirect("Login.html");
            }
            //out.println("</table>");
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
    