<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="header.jsp" %>
<!-- 内容区 -->
<section class="layout main-wrap  content">
    <section class='col-main'>
        <p class="tips">温馨提示：为了您的体验更佳，请在PC端使用</p>
        <!--博客社区-->
        <article class="mainarea" style="display:block;">
            <div class="blog-tab">

                <div class="tab-content">
                    <div role="tabpanel" style="margin-left: 100px;" class="tab-pane fade in active" id="home">

                        <div id="lk_blog_list" style="padding-left: 20px; width: 1000px" class="container">
                            <div class="row">
                                <ul class="blog-list">
                                    <li>
                                        <div class="blog-list-left">
                                            <div class="main-title" id="title"></div>
                                            <div style="color: #c2c2c2" id="time"></div>
                                            <hr>
                                            <div id="content">

                                            </div>
                                            <textarea cols="50" rows="10"  id="contactus" name="contactus" placeholder="想对作者说点什么" ></textarea>
                                        </div>
                                    </li>



                                </ul>





                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </article>
    </section>

</section>

<script>


    <%--    获取当前参数--%>

    function getParams(key) {
        var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }

    //获取参数
    var id = getParams("id");
    //    请求数据
    $.post("${ctx}/web_getDetail.action", {id: id}, function (data) {
        console.log(data);
        $("#title").html(data.article_title);
        $("#time").html(new Date(data.article_time).toLocaleDateString() );
        $("#content").html(data.article_content);

    })

</script>
</body>
</html>