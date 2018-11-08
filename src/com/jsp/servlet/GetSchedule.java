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
@WebServlet(name = "GetSchedule")
public class GetSchedule extends HttpServlet {
    DBService db=new DBService();
    public JSONArray doJson(String id){
        JSONObject myobj=new JSONObject();
        String sql="select Teachingno from Elective where Sno='"+id+"'";
        List<Map<String, Object>> al = new ArrayList<Map<String, Object>>();
        al=db.execQuery(sql,null);
        List<Map<String, Object>> bl = new ArrayList<Map<String, Object>>();
        JSONArray array = new JSONArray();
        int n=al.size();

            for (int i = 0; i < 4; i++) {
                myobj.put("mon", " ");
                myobj.put("tues", " ");
                myobj.put("wed", " ");
                myobj.put("thur", " ");
                myobj.put("fri", " ");
                myobj.put("sat", " ");
                myobj.put("sun", " ");
                for(int j=0;j<n;j++) {
                    int teachingno=Integer.parseInt(al.get(j).get("Teachingno").toString());
                    sql = "select Tserial,Tday,Coursename,Raddr from Teaching where Teachingno='" + teachingno + "'";
                    bl = db.execQuery(sql, null);
                    if (Integer.parseInt(bl.get(0).get("Tserial").toString()) == i + 1) {
                        switch (Integer.parseInt(bl.get(0).get("Tday").toString())) {
                            case 1: {
                                myobj.remove("mon");
                                myobj.put("mon", bl.get(0).get("Coursename").toString()+bl.get(0).get("Raddr").toString());

                            }
                            break;

                            case 2: {
                                myobj.remove("tues");
                                myobj.put("tues", bl.get(0).get("Coursename").toString()+bl.get(0).get("Raddr").toString());

                            }
                            break;

                            case 3: {
                                myobj.remove("wed");
                                myobj.put("wed", bl.get(0).get("Coursename").toString()+bl.get(0).get("Raddr").toString());

                            }
                            break;

                            case 4: {
                                myobj.remove("thur");
                                myobj.put("thur", bl.get(0).get("Coursename").toString()+bl.get(0).get("Raddr").toString());

                            }
                            break;

                            case 5: {
                                myobj.remove("fri");
                                myobj.put("fri", bl.get(0).get("Coursename").toString()+bl.get(0).get("Raddr").toString());

                            }
                            break;

                            case 6: {
                                myobj.remove("sat");
                                myobj.put("sat", bl.get(0).get("Coursename").toString()+bl.get(0).get("Raddr").toString());

                            }
                            break;

                            case 7: {
                                myobj.remove("sun");
                                myobj.put("sun", bl.get(0).get("Coursename").toString()+bl.get(0).get("Raddr").toString());

                            }
                            break;

                        }
                    }
                }
                array.add(myobj);
                }

        return array;
        }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String id=session.getAttribute("id").toString();
        JSONArray array=new JSONArray();
        array=doJson(id);
        out.print(array);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
