package com.jsp.servlet;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;
        import java.io.PrintWriter;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Map;

        import net.sf.ezmorph.object.DateMorpher;
        import net.sf.json.JSONArray;
        import net.sf.json.JSONObject;
        import net.sf.json.JsonConfig;
        import net.sf.json.processors.JsDateJsonValueProcessor;
        import net.sf.json.util.JSONUtils;
        import Service.Impl.DBService;
/**
 * Created by 闻新 on 2018/6/22.
 */
public class GetCourse extends HttpServlet {
    DBService db=new DBService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String id=session.getAttribute("id").toString();
        String sql="select Coursename,Teachingno from Teaching where Tno='"+id+"'";
        List<Map<String, Object>> al = new ArrayList<Map<String, Object>>();
        al=db.execQuery(sql,null);
        JSONObject myobj=new JSONObject();
        int i=al.size();
        JSONArray array=new JSONArray();
        for (int j=0;j<i;j++) {
            myobj.put("id", al.get(j).get("Teachingno").toString());
            myobj.put("unit", al.get(j).get("Coursename").toString());
            array.add(myobj);
        }

        out.print(array.toString());
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
