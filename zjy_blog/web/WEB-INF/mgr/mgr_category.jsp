<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx",ctx);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${ctx}/js/pageStyle.css">
    <script src="${ctx}/js/jquery.min.js"></script>

    <style>
        #modal_content2 {
            padding: 30px 20px;
            width: 400px;
            height: 200px;
            background: white;
            position: fixed;
            left: 50%;
            top: 50%;
            margin-left: -200px;
            margin-top: -100px;
            display: none;
        }

        #close2 {
            position: absolute;
            right: 10px;
            top: 10px;
        }
    </style>
</head>
<body>


<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">分类管理</strong>
            <small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加分类
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>parentid</li>
        <li>分类名称</li>
        <li>修改分类</li>
        <li>删除分类</li>
    </ul>

    <s:iterator value="list" var="li">
        <ul class="list_goods_ul">
            <li><s:property value="#li.parentid"/></li>
            <li><s:property value="#li.cname"/></li>
                <%--        data-id只是行内存放数据的一个标签，跟input里面的value作用是一样的--%>
            <li><a href="#" class="updatebtn" data-id="<s:property value="#li.cid"/>"><img class="img_icon"
                                                                                           src="${ctx}/images/edit_icon.png"
                                                                                           alt=""></a></li>
            <li><a href="${ctx}/category_delete.action?cid=<s:property value="#li.cid"/> "><img class="img_icon" src="${ctx}/images/delete_icon.png" alt=""></a></li>
        </ul>
    </s:iterator>
</div>

<div id="modal_view">


</div>
<div id="modal_content" style="height: 250px">
    <div id="close"><img src="${ctx}/images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>添加分类：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>parentid：</span>
                <input type="text" class="am-form-field" id="parentid">&nbsp;&nbsp;
                <br/>
                <span>分类名称：</span>
                <input type="text" class="am-form-field" id="cname">&nbsp;&nbsp;
                <br/>
                <button class="am-btn am-btn-default" type="button" id="addcategory">添加</button>
            </div>
        </div>

    </div>
</div>


<div id="modal_content2" style="height: 250px; display: none">
    <div id="close2"><img src="${ctx}/images/delete_icon.png" alt=""></div>
    <div class="edit_content">
        <div class="item1">
            <div>
                <span>修改分类：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>parentid：</span>
                <input type="text" class="am-form-field" id="parentid2">&nbsp;&nbsp;
                <br/>
                <span>分类名称：</span>
                <input type="text" class="am-form-field" id="cname2">&nbsp;&nbsp;
                <br/>
                <input type="hidden" id="cid2">
                <button class="am-btn am-btn-default" type="button" id="updatebtn">修改</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content").fadeIn();
        });
        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content").fadeOut();
        });

        $("#addcategory").click(function () {
            var parentid = $("#parentid").val();
            var cname = $("#cname").val();
            $(window).attr('location', '${ctx}/category_add.action?parentid=' + parentid + '&cname=' + cname);
        });

        //    监听修改按钮
        $(".updatebtn").click(function () {
            //当前那一条数据
            var cid = $(this).data("id");
            //发送异步请求
            $.post("${ctx}/category_updateui.action", {"cid": cid}, function (data) {
                //将json数据展示到文本框
                $("#parentid2").val(data[0].parentid);
                $("#cname2").val(data[0].cname);
                $("#cid2").val(data[0].cid)
            }, "json");


            $("#modal_view").fadeIn();
            $("#modal_content2").fadeIn();

        });

        $("#close2").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content2").fadeOut();
        });

        $("#updatebtn").click(function () {
            //获取文本框
            var parentid2 = $("#parentid2").val();
            var cname2 = $("#cname2").val();
            var cid2 = $("#cid2").val();
            $(window).attr('location', '${ctx}/category_update.action?parentid=' + parentid2 + '&cname=' + cname2 + '&cid=' + cid2);
        })
    });
</script>
</body>
</html>