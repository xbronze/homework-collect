<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/12/10
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导航栏</title>
</head>
<body>
    <nav>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/index">首页</a>
            </li>
            <li>
                <a href="#">学生管理</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/report/list">实验报告列表</a>
            </li>
            <li>
                欢迎登录，${sessionScope.get("user").name}
            </li>
        </ul>
    </nav>
</body>
</html>
