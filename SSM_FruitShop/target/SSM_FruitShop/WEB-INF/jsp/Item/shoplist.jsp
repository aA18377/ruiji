<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>首页</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/reourcesHtml/user/css/style.css">
    <script src="${pageContext.request.contextPath}/reourcesHtml/user/js/jquery-1.8.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/reourcesHtml/user/js/jquery.luara.0.0.1.min.js"></script>
</head>
<body>
<%@include file="../common/utop.jsp"%>
<!--导航条-->
<div class="width100" style="height: 45px;background: #dd4545;margin-top: 40px;position: relative;z-index: 100;">
    <!--中间的部分-->
    <div class="width1200 center_yh relative_yh" style="height: 45px;">
        <!--普通导航-->
        <div class="left_yh font16" id="pageNav">
            <a href="${pageContext.request.contextPath}/login/uIndex">首页</a>
            <a href="${pageContext.request.contextPath}/news/list">公告</a>
            <a href="${pageContext.request.contextPath}/message/add">留言</a>
        </div>
    </div>
</div>

<div class="width1200 center_yh hidden_yh font14" style="height: 40px;line-height: 40px;">
    <span>当前位置：</span><a href="${pageContext.request.contextPath}/login/uIndex" class="c_66">首页</a>><a href="#" class="c_66">商品列表</a>
</div>
<div class="width1198 center_yh" style="height: 35px;background: #f0f0f0; border: 1px solid #ddd;margin-top:25px;">
    <a href="${pageContext.request.contextPath}/item/shoplist?categoryIdTwo=${obj.categoryIdTwo}" class="mR">默认</a>
    <a href="${pageContext.request.contextPath}/item/shoplist?price=1&categoryIdTwo=${obj.categoryIdTwo}" class="mR">
        价格
        <img src="${pageContext.request.contextPath}/reourcesHtml/user/images/gg.png" alt="">
    </a>
    <a href="${pageContext.request.contextPath}/item/shoplist?gmNum=1&categoryIdTwo=${obj.categoryIdTwo}" class="mR">
        销量
        <img src="${pageContext.request.contextPath}/reourcesHtml/user/images/gg.png" alt="">
    </a>

</div>
<!--商品列表-->
<div class="width1200 center_yh hidden_yh" style="margin-top: 25px;">
    <ul class="listSs">
        <c:forEach items="${datas.datas}" var="data" varStatus="l">
            <li>
                <a href="${pageContext.request.contextPath}/item/view?id=${data.id}" class="bjK">
                    <img src="${data.url1}" alt="">
                </a>
                <h3 class="spName">${data.name}</h3>
                <p class="center_yh block_yh hidden_yh" style="width: 202px;">
                    <font class="left_yh red font16">￥${data.price}</font>
                    <c:if test="${data.zk!=null}">
                        <font class="right_yh font14">
                            ${data.zk}
                            <span style="color: red">折</span>
                        </font>
                    </c:if>
                </p>
                <div class="wCa">
                    <a href="${pageContext.request.contextPath}/sc/exAdd?itemId=${data.id}" class="fav">
                        <div class="wCa1">
                            <b><img src="${pageContext.request.contextPath}/reourcesHtml/user/images/star.png" alt=""></b>
                            <font>收藏</font>
                        </div>
                    </a>

                    <div class="wCa2" onclick="addcar('${data.id}')">
                        <b><img src="${pageContext.request.contextPath}/reourcesHtml/user/images/sar.png" alt=""></b>
                        <font>加入购物车</font>
                    </div>

                </div>
            </li>
        </c:forEach>
    </ul>
</div>
<div id="navs">
    <div class="pagelist">
        <!--分页开始-->
        <input type="hidden" id="totalPageCount" value="${datas.totalPageCount}"/>
        <c:import url="../rollpage.jsp">
            <c:param name="totalCount" value="${datas.totalCount}"/>
            <c:param name="currentPageNo" value="${datas.currentPageNo}"/>
            <c:param name="totalPageCount" value="${datas.totalPageCount}"/>
        </c:import>
        <form action="${pageContext.request.contextPath}/item/shoplist?getCategoryIdTwo="${obj.categoryIdTwo}>
            <input type="hidden" name="inputPage" value="1"/>
        </form>
    </div>
</div>
<script>
    function addcar(id) {
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/car/exAdd?itemId="+id+"&num=1",
            success:function (result) {
                var re = result;
                if(re===0){
                    alert("请登录");
                    window.location.href="${pageContext.request.contextPath}/login/loginPage";
                }else {
                    window.location.href="${pageContext.request.contextPath}/car/findBySql";
                }
            }
        });
    }
</script>
<%@include file="../common/ufooter.jsp"%>
</body>
</html>



















