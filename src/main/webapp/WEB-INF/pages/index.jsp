<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/11/14
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
    <h3>欢迎登录，${sessionScope.get("user").name}</h3>

    <div>
        <table>
            <c:forEach items="${reportList}" var="report">
                <tr>
                    <td>报告编号：</td><td>${report.id}</td>
                    <td>报告名称：</td><td>${report.reportName}</td>
                    <td>报告内容：</td><td>${report.reportContext}</td>
                    <td>报告截止提交日期：</td><td>${report.formateDate(report.deadlineTime)}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
