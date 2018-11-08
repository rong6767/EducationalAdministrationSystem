package Service.Impl;

import Entity.Student;
import Service.Interface.StudentServiceInterface;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 闻新 on 2018/6/21.
 */
public class StudentService implements StudentServiceInterface{
    DBService db=new DBService();
    public Student getStudent(String studentId) throws Exception{
        String sql="select * from Student where sno="+"'"+studentId+"'";
        List<Map<String, Object>> al = new ArrayList<Map<String, Object>>();
        al=db.execQuery(sql,null);
        Map<String,Object> mystudent=al.get(0);
        Student student=new Student();
        student.setSno(studentId);
        student.setSmajor(mystudent.get("Smajor").toString());
        student.setSname(mystudent.get("Sname").toString());
        student.setSsex(mystudent.get("Ssex").toString());
        student.setSpassword(mystudent.get("Spassword").toString());
        System.out.println("student: " + student);
        return student;
    }

    public int changePassword(String studentId, String oldPassword, String newPassword,int message) throws Exception {
        Student mystudent=getStudent(studentId);
        if(oldPassword.equals(mystudent.getSpassword())) {
            String sql = "update Student set Spassword=" + "'" + newPassword + "'" + "where Sno='" + studentId + "'";
            message=db.execUpdate(sql,null);
        }
            return message;
        }

}
