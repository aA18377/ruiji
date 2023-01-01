<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/reourcesHtml/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/reourcesHtml/css/admin.css">
    <script src="${pageContext.request.contextPath}/reourcesHtml/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/reourcesHtml/pintuer.js"></script>
</head>
<body style="background-color: #f2f9fd">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1>管理员后台</h1>
    </div>
    <div class="head-l">
        <a class="button button-little bg-green" href="${ctx}/login/uIndex" target="_blank">
            <span class="icon-home"></span>前台首页
        </a>
        <a class="button button-little bg-red" href="${pageContext.request.contextPath}/login/aLoginOut">
            <span class="icon-power-off"></span>退出登录
        </a>
    </div>
</div>
<div class="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list">菜单列表</span> </strong></div>
    <h2><span class="icon-user"></span>基本设置 </h2>
    <ul style="display: block">
        <li><a href="${pageContext.request.contextPath}/itemCategory/findBySql" target="right"><span class="icon-caret-right"></span>类目管理</a></li>
        <li><a href="${pageContext.request.contextPath}/user/findBySql" target="right"><span class="icon-caret-right"></span>用户管理</a></li>
        <li><a href="${pageContext.request.contextPath}/item/findBySql" target="right"><span class="icon-caret-right"></span>商品管理</a></li>
        <li><a href="${pageContext.request.contextPath}/itemOrder/findBySql" target="right"><span class="icon-caret-right"></span>订单管理</a></li>
        <li><a href="${pageContext.request.contextPath}/news/findBySql" target="right"><span class="icon-caret-right"></span>公告管理</a></li>
        <li><a href="${pageContext.request.contextPath}/message/findBySql" target="right"><span class="icon-caret-right"></span>留言管理</a></li>
    </ul>
</div>
<ul class="bread">
    <li><a href="{:}" target="right" class="icon-home">首页</a></li>
    <li><a href="#">网站信息</a></li>
    <li><b>当前语言：</b><span style="color:red">中文</span>
        &nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="#">中文</a>&nbsp;&nbsp;<a href="#">英文</a>
    </li>
</ul>
<div class="admin">
    <iframe scrolling="auto" rameborder="0" src="${pageContext.request.contextPath}/itemCategory/tj" name="right" width="100%"
            height="100%"></iframe>
</div>
</body>
</html>


