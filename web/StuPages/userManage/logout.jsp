<%--
  Created by IntelliJ IDEA.
  User: 闻新
  Date: 2018/6/21
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>logout</title>
</head>
<body>
<%
    response.setHeader("refresh","3;URL=/login.jsp");
    session.invalidate();
%>
</body>
</html>
