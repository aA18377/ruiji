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
    <form action="${pageContext.request.contextPath}/item/findBySql" id="listform" method="post">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left: 10px;">
                <li>
                    <a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath}/item/add">添加商品</a>
                </li>
                <li>
                    <input type="text" placeholder="请输入商品名称" name="name" class="input" value="${name}"
                        style="width: 250px;line-height: 17px;display: inline-block" />
                    <input type="hidden" name="inputPage" value="1"/>
                    <a href="javascript:void(0)" onclick="changeSearch()" class="button border-main icon-search">搜索</a>
                </li>
            </ul>
        </div>
    </form>
    <table class="table table-hover text-center">
        <tr>
            <th>商品名称</th>
            <th>商品主图</th>
            <th>商品价格</th>
            <th>商品一级分类</th>
            <th>商品二级分类</th>
            <th>操作</th>
        </tr>
    <c:forEach items="${datas.datas}" var="data" varStatus="l">
        <tr>
            <td>${data.name}</td>
            <td><img src="${data.url1}" alt="" style="width: 100px;height: 100px;"></td>
            <td>${data.price}</td>
            <td>${data.yiji.name}</td>
            <td>${data.erji.name}</td>
            <td>
                <a class="button border-main" href="${pageContext.request.contextPath}/item/update?id=${data.id}"><span class="icon-edit">修改</span> </a>
                <a class="button border-red" href="${pageContext.request.contextPath}/item/delete?id=${data.id}"><span class="icon-trash-o">下架</span> </a>
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