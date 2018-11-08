package com.jsp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import  Service.Impl.DBService;
import Entity.Teaching;
import Entity.Course;
import Service.Impl.CourseService;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;
import net.sf.json.util.JSONUtils;

/**
 * Created by 闻新 on 2018/6/21.
 */
@WebServlet(name = "CourseList")
public class CourseList extends HttpServlet {
    DBService db=new DBService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String sql="select Teachingno,Tday,Tserial,Raddr from Teaching";
        List<Map<String, Object>> al = new ArrayList<Map<String, Object>>();
        al=db.execQuery(sql,null);
        JSONArray jsonData = JSONArray.fromObject(al);
        PrintWriter out=response.getWriter();
        out.print(jsonData);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
