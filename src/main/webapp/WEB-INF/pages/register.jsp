<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/11/14
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .register-container {
            text-align: center;
        }
        .register-container input {
            width: 100%;
            margin-top: 10px;
        }
        .register-container button {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h3>注册</h3>
        <form action="${pageContext.request.contextPath}/user/register" method="post">
            <input type="text" name="name" placeholder="姓名" required><br/>
            <input type="text" name="code" placeholder="学号" required><br/>
            <input type="text" name="classname" placeholder="班级" required><br/>
            <input type="text" name="username" placeholder="用户名" required><br/>
            <input type="password" name="password" placeholder="密码" required><br/>
            <button type="submit">注册</button><a href="${pageContext.request.contextPath}/login">登录</a>
        </form>
    </div>
</body>
</html>
