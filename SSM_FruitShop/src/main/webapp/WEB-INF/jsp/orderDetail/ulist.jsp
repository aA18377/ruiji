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
    <table class="table table-hover text-center">
        <tr>
            <th>商品名称</th>
            <th>商品主图</th>
            <th>商品单价</th>
            <th>购买数量</th>
            <th>小计</th>
            <th>状态</th>
        </tr>
    <c:forEach items="${datas.datas}" var="data" varStatus="l">
        <tr>
            <td>${data.item.name}</td>
            <td><img src="${data.item.url1}" alt="" style="width: 100px;height: 100px;"></td>
            <td>${data.item.price}</td>
            <td>${data.num}</td>
            <td>${data.total}</td>
            <td style="color: red">
                <c:if test="${data.status == 0}">
                        未退货
                </c:if>
                <c:if test="${data.status == 1}">
                    已退货
                </c:if>
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
                    <form action="${pageContext.request.contextPath}/orderDetail/ulist">
                        <input type="hidden" value="1">
                    </form>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>