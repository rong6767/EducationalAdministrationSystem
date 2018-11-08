<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="Service.Impl.StudentService"%>
<%@ page import="Entity.Student"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        * {
            font-family: "微软雅黑";
        }

        .navbar-brand {
            color: white !important;
            font-weight: bold;
        }

        body {
            padding-top: 60px;
        }
    </style>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/framework/bootstrap/css/bootstrap.min.css" />
    <script
            src="<%=request.getContextPath()%>/framework/jquery-1.11.3.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/framework/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/framework/holder.min.js"></script>
    <title>Insert title here</title>
</head>
<body>
<%
    StudentService studentService=new StudentService();
    Student student=new Student();
   try{
      student = studentService.getStudent(session.getAttribute("id").toString());
   }
   catch (Exception e){
       e.printStackTrace();
   }
%>
<div class="container">
    <%--上方的导航栏 --%>
    <%@ include file="../public/top.html"%>
    <div class="row">
        <%--左边的菜单 --%>
        <%@ include file="../public/left.html"%>
        <div class="col-md-10" id="leftDiv">
            <div class="panel panel-default">
                <div class="panel-heading">个人信息</div>
                <div class="panel-body form-group" style="margin-bottom: 0px;">
                    <table class="table table-bordered table-striped">
                        <tr>
                            <td>学号：</td>
                            <td><%=student.getSno()%></td>
                        </tr>
                        <tr>
                            <td>姓名：</td>
                            <td><%=student.getSname()%></td>
                        </tr>
                        <tr>
                            <td>性别：</td>
                            <td><%=student.getSsex()%></td>
                        </tr>
                        <tr>
                            <td>班级：</td>
                            <td><%=student.getSmajor()%></td>
                        </tr>


                    </table>
                </div>
            </div>
        </div>
</body>

</html>