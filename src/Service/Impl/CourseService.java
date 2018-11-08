package Service.Impl;
import Entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 闻新 on 2018/6/21.
 */
public class CourseService {
    DBService db=new DBService();
    public Course getCourseList(String Courseno){
        String sql="select * from Course where Courseno="+"'"+Courseno+"'";
        List<Map<String, Object>> al = new ArrayList<Map<String, Object>>();
        al=db.execQuery(sql,null);
        Map<String,Object> mycourse=al.get(0);
        Course course=new Course();
        course.setCoursename(mycourse.get("Coursename").toString());
        course.setCourseno(mycourse.get("Courseno").toString());
        course.setCredit(Integer.parseInt(mycourse.get("Credit").toString()));
        return course;
    }
}
