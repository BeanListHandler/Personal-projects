<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="header.jsp" %>
<script src="${ctx}/js/template.js"></script>
<style>
    .content_item {
        height: 160px;
        position: relative;
    }

    .content_item img {
        position: absolute;
        right: 10px;
        top: 10px;
        width: 250px;
        height: 140px;
    }

    .blog-list li p {
        width: 1000px;
    }
</style>
<!-- 内容区 -->
<section class="layout main-wrap  content">
    <section class='col-main'>

        <article class="mainarea" style="display:block;">
            <div class="blog-tab">

                <div class="tab-content" id="tab-content">
                    <%--                    分类信息--%>
                </div>
            </div>
        </article>
        <!--博客社区-->
        <article class="mainarea" style="display:block;">
            <div class="blog-tab">

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="home">

                        <div id="lk_blog_list" class="container">
                            <div class="row">
                                <ul class="blog-list" id="content">


                                </ul>
                                <%--                                fy--%>
                                <div id="page" class="page_div"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </article>
    </section>

</section>


<%--文章模板--%>
<script id="my" type="text/html">
    {{each list as value}}
    <li class="content_item">
        <div class="blog-list-left" style="float: left;">
            <div class="main-title">
                <a href="detail.jsp?id={{value.article_id}}">{{value.article_title}}</a>
            </div>
            <p class="sub-title">{{value.article_desc}}</p>
            <div class="meta">
                {{value.article_time | dateFormat:'yyyy-MM-dd h:m:s'}}
            </div>
        </div>
        <img src="${ctx}/upload/{{value.article_pic}}" alt="" class="img-rounded">
    </li>
    {{/each}}
</script>

<%--分类信息模板--%>
<script id="my2" type="text/html">

    <div role="tabpanel" class="tab-pane fade in active" id="tab">
        <div id="lk_blog_two" class="container">
            <div class="row">
                {{each list as value}}
                    <button class="btn-tag subset" data-cid="{{value.cid}}">{{value.cname}}</button>
                {{/each}}
            </div>
        </div>
    </div>

</script>

<script>
    //时间戳转换
    template.helper('dateFormat', function (date, format) {

        date = new Date(date);

        var map = {
            "M": date.getMonth() + 1, //月份
            "d": date.getDate(), //日
            "h": date.getHours(), //小时
            "m": date.getMinutes(), //分
            "s": date.getSeconds(), //秒
            "q": Math.floor((date.getMonth() + 3) / 3), //季度
            "S": date.getMilliseconds() //毫秒
        };
        format = format.replace(/([yMdhmsqS])+/g, function (all, t) {
            var v = map[t];
            if (v !== undefined) {
                if (all.length > 1) {
                    v = '0' + v;
                    v = v.substr(v.length - 2);
                }
                return v;
            } else if (t === 'y') {
                return (date.getFullYear() + '').substr(4 - all.length);
            }
            return all;
        });
        return format;
    });

    //获取当前参数
    function getParams(key) {
        var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }

    //加载子级
    var params = getParams("cid");
    if (params != null) {
        $.post("${ctx}/article_getCategory.action", {"parentid": params}, function (data) {
            var html = template('my2', {list: data});
            $("#tab-content").html(html);
            console.log(data);
        }, "json");

    //    加载数据in
        getPageList(1,params,null);
    } else {
        //分页列表
        getPageList(1,null,null);
    }

    $("body").on("click",".subset",function () {
       var cid = $(this).data("cid");
       getPageList(1,null,cid);
    });

    function getPageList(curPage,params,cid) {
        $.post("${ctx}/web_getList.action", {"currPage": curPage,params:params,cid:cid}, function (data) {
            // console.log(JSON.parse(data).list);
            console.log(data);
            // var data = {list:data.list};
            var html = template('my', {list: data.list});
            $("#content").html(html);

            //分页
            $("#page").paging({
                pageNo: data.currentPage,  //当前页
                totalPage: data.totalPage, //总页数
                totalSize: data.totalCount, //总记录数
                callback: function (num) {
                    getPageList(num,params,cid);
                }
            })
        });

    }
</script>
</body>
</html>