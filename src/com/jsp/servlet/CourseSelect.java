package com.jsp.servlet;

import Service.Impl.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Entity.Elective;

/**
 * Created by 闻新 on 2018/6/21.
 */
public class CourseSelect extends HttpServlet {
    DBService db=new DBService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        final String msg1="exsisted";
        final String msg2="fail";
        final String msg3="success";
        int message=0;
        int teachingno=Integer.parseInt(request.getParameter("Teachingno"));

        String sql="SELECT Coursename\n" +
                "FROM Course\n" +
                "WHERE Courseno = (\n" +
                "\n" +
                "SELECT Courseno\n" +
                "FROM Teaching\n" +
                "WHERE Teachingno =" +teachingno+"\n"+
                ")";
        List<Map<String, Object>> al = new ArrayList<Map<String, Object>>();
        al=db.execQuery(sql,null);
        String Coursename=al.get(0).get("Coursename").toString();
        sql="select Tname from Teacher where Tno= ( select Tno from Teaching where Teachingno ="+teachingno+")";
        al=db.execQuery(sql,null);
        String Tname=al.get(0).get("Tname").toString();
        String id=session.getAttribute("id").toString();
        Elective myele=new Elective();
        myele.setSno(id);
        myele.setTeachingno(teachingno);
        sql="select count(*) from Elective where Sno='"+id+"' and Teachingno="+teachingno;
        al=db.execQuery(sql,null);
        message=Integer.parseInt(al.get(0).get("count(*)").toString());
        if(message==0) {
            sql = "INSERT INTO Elective (`Sno`, `Teachingno`,`Score`) " +
                    "VALUES " +
                    "('" + id + "'," + teachingno + ",0)";
            message = db.execUpdate(sql, null);
            if(message==1){
                out.print(msg3);
                out.flush();
                out.close();
            }
            else {
                out.print(msg2);
                out.flush();
                out.close();
            }
        }
        else {
            out.print(msg1);
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
