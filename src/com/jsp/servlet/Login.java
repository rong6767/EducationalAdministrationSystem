package com.jsp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import Entity.User;
import java.sql.*;

/**
 * Created by 闻新 on 2018/5/10.
 */
public class Login extends HttpServlet {

    final String msg1="no_id";
    final String msg2="wrong_password";
    final String msg3="pass";
    String StrCon="";
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    User user=new User();
    public Login(){
        super();
    }

    public void init()throws ServletException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public int queryUser(String sql) throws Exception{
        int message=0;
        try{
            StrCon="jdbc:mysql://103.1.154.39/School";
            con=DriverManager.getConnection(StrCon,"wen","123456");

            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            if (rs.next()){
                message=rs.getInt(1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String no = (String) request.getParameter("InputUsername");
        String password = (String) request.getParameter("InputPassword");
        String role = (String) request.getParameter("role");
        session.setAttribute("id", no);
        String Stupass = null;
        if (role.equals("student")) {
            String sql = "select Spassword from Student where Sno='" + no + "'";
            try {
                int message = queryUser(sql);
                if (message != 0) {
                    Stupass = rs.getString("Spassword");
                    rs.close();
                    if (Stupass.equals(password)) {
                        user.setUsername(no);
                        user.setPassword(password);
                        user.setRole(role);

                        out.print(msg3);
                        out.flush();
                        out.close();
                    } else {
                        out.print(msg2);
                        out.flush();
                        out.close();
                    }

                } else {

                    out.print(msg1);
                    out.flush();
                    out.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (role.equals("teacher")) {
            String sql = "select Tpassword from Teacher where Tno='" + no + "'";
            try {
                int message = queryUser(sql);
                if (message != 0) {
                    String Tpass = rs.getString("Tpassword");
                    rs.close();
                    if (Tpass.equals(password)) {
                        user.setUsername(no);
                        user.setPassword(password);
                        user.setRole(role);

                        out.print(msg3);
                        out.flush();
                        out.close();
                    } else {
                        out.print(msg2);
                        out.flush();
                        out.close();
                    }

                } else {

                    out.print(msg1);
                    out.flush();
                    out.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
