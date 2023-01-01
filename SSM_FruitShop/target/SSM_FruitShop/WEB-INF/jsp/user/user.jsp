<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>管理员后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/reourcesHtml/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/reourcesHtml/css/admin.css">
    <script src="${pageContext.request.contextPath}/reourcesHtml/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/reourcesHtml/js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <form action="${pageContext.request.contextPath}/user/findBySql" id="listform" method="post">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left: 10px;">
                <li>
                    <input type="text" placeholder="请输入用户名" name="userName" class="input" value="${obj.userName}"
                        style="width: 250px;line-height: 17px;display: inline-block" />
                    <input type="hidden" name="inputPage" value="1"/>
                    <a href="javascript:void(0)" onclick="changeSearch()" class="button border-main icon-search">搜索</a>
                </li>
            </ul>
        </div>
    </form>
    <table class="table table-hover text-center">
        <tr>
            <th>用户名</th>
            <th>手机号</th>
            <th>真实姓名</th>
            <th>性别</th>
            <th>邮箱</th>
            <th>地址</th>
        </tr>
    <c:forEach items="${datas.datas}" var="data" varStatus="l">
        <tr>
            <td>${data.userName}</td>
            <td>${data.phone}</td>
            <td>${data.realName}</td>
            <td>${data.sex}</td>
            <td>${data.email}</td>
            <td>${data.address}</td>

        </tr>
    </c:forEach>
        <tr>
            <td colspan="8">
                <div class="pagelist">
                    <!--分页开始-->
                    <input type="hidden" id="totalPageCount" value="${datas.totalPageCount}"/>
                    <c:import url="../rollpage.jsp">
                        <c:param name="totalCount" value="${datas.totalCount}"/>
                        <c:param name="currentPageNo" value="${datas.currentPageNo}"/>
                        <c:param name="totalPageCount" value="${datas.totalPageCount}"/>
                    </c:import>

                </div>
            </td>
        </tr>
    </table>
</div>
<script>
    function changeSearch(){
        $("#listform").submit();
    }
</script>
</body>

</html>