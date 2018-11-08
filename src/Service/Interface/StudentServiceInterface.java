package Service.Interface;

import Entity.Student;


/**
 * Created by 闻新 on 2018/6/21.
 */
public interface StudentServiceInterface {
    public Student getStudent(String studentId)throws Exception;

    public int changePassword(String studentId, String oldPassword, String newPassword,int message) throws Exception;
}
