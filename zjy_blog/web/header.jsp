<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" >
    <title>zjy博客</title>
    <%--    <link rel="shortcut icon" href="favicon.ico"/>--%>
    <link rel="stylesheet" href="js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/lkblog.css">
    <link rel="stylesheet" href="css/blog_con.css">
    <link rel="stylesheet" href="${ctx }/css/pageStyle.css">
    <script src="${ctx}/js/jquery.min.js"></script>
    <script src="${ctx}/js/paging.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${ctx}/js/umedit/ueditor.config.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${ctx}/js/umedit/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${ctx}/js/umedit/lang/zh-cn/zh-cn.js"></script>
</head>
<body background="img/tim.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<%--<div id="top_bar" class="container hidden-xs hidden-sm">--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-offset-1 col-md-7">--%>
<%--            <ul class="top-bar-left">--%>
<%--                <li style="margin-right: 20px;">--%>
<%--                    <a href="#" target="_blank">--%>
<%--                        <span class="icon-tel"></span>--%>
<%--                        &lt;%&ndash;顶部&ndash;%&gt;--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--                <li>--%>
<%--                    <a href="#" target="_blank">--%>
<%--                        <span class="icon-email"></span>--%>
<%--                        &lt;%&ndash;                        顶部&ndash;%&gt;--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--        <div class="col-md-offset-3">--%>
<%--            &lt;%&ndash;            <span class="top-bar-right pull-right">博<span style="color: red">客</span></span>&ndash;%&gt;--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<!--导航-->
<nav class="navbar navbar-default navbar-lk">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <i class="navbar-brand"  style="margin-left: 180px">
            </i>

            <ul class="nav navbar-nav" id="nav" style="font-size: 20px">
                <li class="active"><a href="index.jsp">博客首页</a></li>
            </ul>
            <a href="${ctx}/mgr_login.jsp" style="line-height:95px; height: 95px;font-size: 18px; color: #0a628f">
                发布文章
            </a>
        </div>
    </div>
</nav>
<!--banner-->
<section id="lk_blog_one">
    <img src="images/about_java.png" alt="" class="one-img" width="280">
    <div class="one-right">
        <h1>IT技术专栏</h1>
        <span>喜欢IT,就来撩我吧!</span>
        <div style="position: relative;" class="one-bottom">
            <button>关注我</button>
            <img src="images/wode.jpg" alt="" width="100" class="one-ewm" style="z-index: 999">
        </div>
    </div>
</section>

<script>
    $(function () {
        //获取数据
        $.post("${ctx}/article_getCategory.action", {"parentid": 0}, function (data) {
            // console.log(data);
            $(data).each(function (i, obj,o) {
                $("#nav").append("<li class='active'><a  href='index.jsp?cid="+obj.cid+"'>" + obj.cname + "</a></li>");
            });

        }, "json");

    })
</script>