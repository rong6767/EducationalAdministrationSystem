<%--
  Created by IntelliJ IDEA.
  User: 闻新
  Date: 2018/6/21
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%--学生选课中心的首页 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <script
            src="<%=request.getContextPath()%>/framework/bootstrap-table/bootstrap-table.min.js"></script>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/framework/bootstrap-table/bootstrap-table.min.css" />
    <script
            src="<%=request.getContextPath()%>/framework/bootstrap-table/bootstrap-table-zh-CN.js"
            charset="UTF-8"></script>
    <title>Insert title here</title>
</head>
<body>
<div class="container">
    <%--上方的导航栏 --%>
    <%@ include file="../public/top.html"%>
    <div class="row">
        <%--左边的菜单 --%>
        <%@ include file="../public/left.html"%>
        <div class="col-md-10" id="leftDiv">
            <table id="mytab" class="table table-hover"></table>
            <!-- 右侧设计结束 -->
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    window.operateEvents={
        "click #select":function (e,value,row,index) {
            $.ajax({
                type:"POST",
                url:"/servlet/CourseSelect",//web.xml中的url-pattern，使用struts改成：命名空间/action_**即可.0
                data:{
                    "Teachingno":row['Teachingno']
                },
                dataType:"text",//类型
                beforeSend:function(XMLHttpRequest){
                },
                success:function(data){

                    if(data==="exsisted")
                        alert("选课重复");
                    if(data==="fail")
                        alert("选课失败");
                    if(data==="success")
                        alert("选课成功！");


                },
            });
            return false;
        }
    }

</script>
<script type="text/javascript">
    function intoAddCourseCenter(teachno){

    }

    //生成用户数据
    $('#mytab').bootstrapTable({
        method : 'post',
        contentType : "application/x-www-form-urlencoded",//必须要有！！！！
        url : "/servlet/CourseList",//要请求数据的文件路径
        dataType:"json",
        height : tableHeight(),//高度调整
        //toolbar : '#toolbar',//指定工具栏
        striped : true, //是否显示行间隔色
        pageNumber : 1, //初始化加载第一页，默认第一页
        pagination : true,//是选否分页
        queryParamsType : 'limit',//查询参数组织方式
        sidePagination : 'client',//指定服务器端分页
        pageSize : 8,//单页记录数
        pageList : [2, 5, 8, 10, 20, 30 ],//分页步进值
        /*     showRefresh:true,//刷新按钮
         showColumns:true, */

        clickToSelect : true,//是否启用点击选中行
        toolbarAlign : 'right',//工具栏对齐方式
        buttonsAlign : 'right',//按钮对齐方式
        //toolbar : '#toolbar',//指定工作栏
        columns : [
            {
                title : '选课ID',
                field : 'Teachingno'
            },{
                title : '星期',
                field : 'Tday'
            },{
                title : '第x节课',
                field : 'Tserial'
            },{
                title : '上课地点',
                field : 'Raddr'
            },{
                title :'操作',
                field :'id',
                formatter: operateFormatter,
                events:operateEvents,
            }],
        locales : 'zh-CN', //中文支持,
        responseHandler : function(res) {
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        }
    });

    //三个参数，value代表该列的值
    function operateFormatter(value, row, index) {
        var button =  '<button class="btn btn-sm" id="select">选择</button>';
        return button;
    }




    //tableHeight函数
    function tableHeight() {
        //可以根据自己页面情况进行调整
        return $(window).height() - 254;
    }

</script>
</html>