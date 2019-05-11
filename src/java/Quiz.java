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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sumit
 */
public class Quiz extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  response.setContentType("text/html");
       PrintWriter out = response.getWriter();
        int a=0,b=0;
        float d,percent;
        
        String pass;
        
                    HttpSession s=request.getSession();
        String email=(String)s.getAttribute("email");
        //String name=(String)s.getAttribute("name");
        
        String q1=request.getParameter("Math");
        String q2 = request.getParameter("Physics");
        String q3 = request.getParameter("Chemistry");
        String q4=request.getParameter("like");
        String q5=request.getParameter("IC");
        try{
            Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/test";
	 String uname="root";
	 String password="sumit2123";
	
        if("sumit".equals(q1))
        {
            a++;
        }
        else
        {
            b++;
        }
        if("pcm".equals(q2))
        {
            a++;
        }
        else
        {
            b++;
        }
        if("m".equals(q3))
        {
            a++;
        }
        else
        {
            b++;
        }
        if("sona".equals(q4))
        {
            a++;
        }
        else
        {
            b++;
        }
        if("real".equals(q5))
        {
            a++;
        }
        else
        {
            b++;
        }
        
        int c=a+b;
        d=(float)a/c;
        percent=d*100;
        if(percent>30)
        {
           pass="pass"; 
           
        }
        else
        {
            pass="fail";
        }
        String query="insert into admin1 values (?,?)";
         Connection con=DriverManager.getConnection(url,uname,password);
	 PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,pass);
        ps.setFloat(2,d);
         ps.executeUpdate();
         out.println("<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
"  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
"</head>\n" +
"<body background=\"bg1.jpg\">\n" +
"\n" +
"<div class=\"container\">\n" +
"  <h2>Your Results</h2>\n" +
                                                                                        
"  <div class=\"table-responsive\">          \n" +
"  <table class=\"table\">\n" +
"    <thead>\n" +
"      <tr>\n" +
"        <th>About</th>\n" +
"        <th>Result</th>\n" +

"      </tr>\n" +
"    </thead>\n" +
"    <tbody>\n" +
"      <tr>\n" +
"        <td>EMAIL</td>\n" +
"        <td>"+email+"</td>\n" +
"      </tr>\n" +
"      <tr>\n" +
"        <td>RESULT</td>\n" +
"        <td>"+pass+"</td>\n" +
"      </tr>\n" +  
"      <tr>\n" +
"        <td>PERCENTAGE</td>\n" +
"        <td>"+percent+"%"+"</td>\n" +
"      </tr>\n" +
"    </tbody>\n" +
"  </table>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
        "<style>\n" +
".center {\n" +
"    display: block;\n" +
"    margin-left: auto;\n" +
"    margin-right: auto;\n" +
"    width: 50%;\n" +
"}\n" +
"</style>"+

"</body>\n" +
"</html>");

         if(percent>30)
         {
             out.println("<img src='one.jpg' align=\"middle\" class=\"center\" style=\"width:400px;height:400px;\">");
         }
         else
         {
             out.println("<img src='two.jpg' align=\"middle\" class=\"center\" style=\"width:400px;height:400px;\">");
         }
           // out.println(email+" "+pass+" "+d);
            
            
           // s.setAttribute("pass", pass);
           // s.setAttribute("percent", d);
               
           // response.sendRedirect("newjsp.jsp");
    }catch(Exception e)
    {
        e.printStackTrace();
    }
            }
  
}
