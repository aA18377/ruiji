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
    <form action="${pageContext.request.contextPath}/itemOrder/findBySql" id="listform" method="post">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left: 10px;">
                <li>
                    <input type="text" placeholder="订单号" name="code" class="input" value="${code}"
                        style="width: 250px;line-height: 17px;display: inline-block" />
                    <input type="hidden" name="inputPage" value="1"/>
                    <a href="javascript:void(0)" onclick="changeSearch()" class="button border-main icon-search">搜索</a>
                </li>
            </ul>
        </div>
    </form>
    <table class="table table-hover text-center">
        <tr>
            <th>订单号</th>
            <th>下单时间</th>
            <th>总金额</th>
            <th>下单人</th>
            <th>订单状态</th>
            <th>操作</th>
        </tr>
    <c:forEach items="${datas.datas}" var="data" varStatus="l">
        <tr>
            <td>${data.code}</td>
            <td><fmt:formatDate value="${data.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td>${data.total}</td>
            <td>${data.user.userName}</td>
            <td style="color: red">
                <c:if test="${data.status == 0}">待发货</c:if>
                <c:if test="${data.status == 1}">已取消</c:if>
                <c:if test="${data.status == 2}">待收货</c:if>
                <c:if test="${data.status == 3}">已收货</c:if>
            </td>
            <td>
                <a class="button border-main" href="${pageContext.request.contextPath}/orderDetail/ulist?orderId=${data.id}"><span class="icon-edit">查看购买商品</span> </a>
                <c:if test="${data.status == 0}">
                    <a class="button border-red" href="${ctx}/itemOrder/fh?id=${data.id}"><span class="icon-trash-o">去发货</span> </a>
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