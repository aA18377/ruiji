<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <form action="${pageContext.request.contextPath}/news/findBySql" id="listform" method="post">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left: 10px;">
                <li>
                    <a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath}/news/add">添加公告</a>
                </li>
                <li>
                    <input type="text" placeholder="请输入公告名称" name="name" class="input" value="${name}"
                        style="width: 250px;line-height: 17px;display: inline-block" />
                    <input type="hidden" name="inputPage" value="1">
                    <a href="javascript:void(0)" onclick="changeSearch()" class="button border-main icon-search">搜索</a>
                </li>
            </ul>
        </div>
    </form>
    <table class="table table-hover text-center">
        <tr>
            <th>名称</th>
            <th>发布时间</th>
            <th>操作</th>
        </tr>
    <c:forEach items="${datas.datas}" var="data" varStatus="l">
        <tr>
            <td>${data.name}</td>
            <td><fmt:formatDate value="${data.addTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            <td>
                <a class="button border-main" href="${pageContext.request.contextPath}/news/update?id=${data.id}"><span class="icon-edit">修改</span> </a>
                <a class="button border-red" href="${pageContext.request.contextPath}/news/delete?id=${data.id}"><span class="icon-trash-o">删除</span> </a>
            </td>

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