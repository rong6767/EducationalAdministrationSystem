<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Signin</title>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/framework/bootstrap/css/bootstrap.min.css" />
    <script
            src="<%=request.getContextPath()%>/framework/jquery-1.11.3.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/framework/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="dist/ladda-themeless.min.css">
    <script src="dist/spin.min.js"></script>
    <script src="dist/ladda.min.js"></script>
</head>

<body>

<div class="container-fluid"  style="margin-top:30px">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading"><h3 class="panel-title"><strong>教务管理系统 </strong></h3></div>
            <div class="panel-body">
                <form role="form" >
                    <div class="form-group">
                        <label for="InputUsername">Username</label>
                        <input type="text" class="form-control" name="InputUsername" id="InputUsername" placeholder="Enter username">
                    </div>
                    <div class="form-group">
                        <label for="InputPassword">Password <a href="#">(forgot password)</a></label>
                        <input type="password" class="form-control" name="InputPassword" id="InputPassword" placeholder="Password">
                    </div>
                    <button type="button" id="signbot" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off">
                        Sign in
                    </button>
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-primary active">
                            <input type="radio" name="role" value="student" autocomplete="off" checked> 学生
                        </label>
                        <label class="btn btn-primary">
                            <input type="radio" name="role"  value="teacher" autocomplete="off"> 教师
                        </label>
                        <label class="btn btn-primary">
                            <input type="radio" name="role"  value="admin" autocomplete="off"> 管理员
                        </label>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">


   $("#signbot").on('click', function Check() {
       var $btn = $(this).button('loading');
       var role=$("input:radio[name='role']:checked").val();
        $.ajax({
            type:"POST",
            url:"./servlet/Login",//web.xml中的url-pattern，使用struts改成：命名空间/action_**即可.0
            data:{
                "InputUsername":$("#InputUsername").val(),
                "InputPassword":$("#InputPassword").val(),
                "role":$("input:radio[name='role']:checked").val()
            },
            dataType:"text",//类型
            beforeSend:function(XMLHttpRequest){
            },
            success:function(data){
                $btn.button('reset')
                if(data==="no_id")
                    alert("用户名不存在");
                if(data==="wrong_password")
                    alert("密码错误");
                if(data==="pass") {
                    if (role === "student")
                        window.location.assign("/StuPages/index.jsp");
                    if (role === "teacher")
                        window.location.assign("/TeaPages/index.jsp");
                }

            },
    });
       return false;
});
</script>
</html>
