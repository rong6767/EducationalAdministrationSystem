package com.jsp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import  Service.Impl.DBService;

/**
 * Created by 闻新 on 2018/6/22.
 */
public class EditScore extends HttpServlet {
    DBService db=new DBService();
    final String msg1="success";
    final String msg2="fail";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String Sname=request.getParameter("Sname");
        int score=Integer.parseInt(request.getParameter("score"));
        int Teachingno=Integer.parseInt(request.getParameter("Teachingno"));
        String sql="update Elective set Score="+score+" where Sno=(select Sno from Student where Sname='"+Sname+"') and Teachingno="+Teachingno;
        int i=db.execUpdate(sql,null);
        if(i==1){
            out.print(msg1);
            out.flush();
            out.close();
        }
        else{
            out.print(msg2);
            out.flush();
            out.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
