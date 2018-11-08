package com.jsp.servlet;

import Service.Impl.DBService;
import net.sf.json.JSONArray;

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

/**
 * Created by 闻新 on 2018/6/22.
 */
public class ScoreList extends HttpServlet {
    DBService db=new DBService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String Teachingno=request.getParameter("Teachingno");
        String sql="Select Student.Sname,Elective.Score from Student right join Elective on Student.Sno=any(Select Sno from Elective where Teachingno="+Teachingno+") group by Sname";
        List<Map<String, Object>> al = new ArrayList<Map<String, Object>>();
        al=db.execQuery(sql,null);
        JSONArray jsonData = JSONArray.fromObject(al);
        out.print(jsonData);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
