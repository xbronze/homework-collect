<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/11/14
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <style>
      body {
        height: 95%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
      }
      .login-container input {
        width: 100%;
        margin-top: 10px;
      }
      .login-container button {
        margin-top: 10px;
      }
    </style>
  </head>
<body>

  <br />
  <div class="login-container" style="text-align: center">
    <h3>登录</h3>
    <form action="${pageContext.request.contextPath}/user/loginCheck" method="post">
      <input type="text" name="username" placeholder="用户名" required><br/>
      <input type="password" name="password" placeholder="密码" required><br/>
      <button type="submit">登录</button>
      <%--      <a href="${pageContext.request.contextPath}/register">还没有账号？注册</a>--%>
    </form>
  </div>
  <div>
    <h5 style="color: red">${msg}</h5>
  </div>

</body>
</html>
