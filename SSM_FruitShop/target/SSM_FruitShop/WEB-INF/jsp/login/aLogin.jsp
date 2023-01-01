<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/reourcesHtml/css/admin.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/reourcesHtml/css/pintuer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/reourcesHtml/css/style.css">
    <script src="${pageContext.request.contextPath}/reourcesHtml/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/reourcesHtml/js/pintuer.js"></script>
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height: 150px;"></div>
            <div class="media media-y margin-big-bottom"></div>
            <form action="${pageContext.request.contextPath}/login/toLogin" method="post">
                <div class="panel login-box">
                    <div class="text-center margin-big padding-big-top"><h1>管理员登录</h1></div>
                    <div class="panel-body" style="padding: 10px 30px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input input-big" name="userName"
                                       placeholder="登录账号" data-validate="required:请填写账号"/>
                                <span class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input input-big" name="passWord"
                                       placeholder="登录密码" data-validate="required:请填写密码"/>
                                <span class="icon icon-key margin-small"></span>
                            </div>
                        </div>
                    </div>
                    <div style="padding: 30px;">
                        <input type="submit" class="button button-block bg-main text-big input-big" value="登录"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>