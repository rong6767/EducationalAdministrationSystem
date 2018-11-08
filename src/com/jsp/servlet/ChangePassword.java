package com.jsp.servlet;

import Service.Impl.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 闻新 on 2018/6/21.
 */
public class ChangePassword extends HttpServlet {
    StudentService studentService=new StudentService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int message=0;
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String oldPassword=(String)request.getParameter("oldPwd");
        String newPassword=(String)request.getParameter("newPwd");
        String stuID=(String)session.getAttribute("id");
        try {
            message=studentService.changePassword(stuID, oldPassword, newPassword, message);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        out.print(message);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
