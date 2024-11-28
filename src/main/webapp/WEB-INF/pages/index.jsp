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
    <style>
        .report_sector {
            margin: 30px 25%;

        }
        .report_sector_border {
            border: 1px solid;
        }
        .report_item {
            margin: 10px 20px;
        }
        .report_context_p {
            padding-left: 20px;
            font-size: 15px;
        }
        .report_button {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 10px;

            button {
                margin: 0 5px;
            }
        }
    </style>
</head>
<body>
    <div>
        <h3>欢迎登录，${sessionScope.get("user").name}</h3>
    </div>

    <div>
        <div class="report_sector">
            <button>新增</button>
        </div>
        <c:forEach items="${reportList}" var="report">
            <div class="report_sector report_sector_border">
                <div class="report_item">
                    <label>报告名称：</label><strong>${report.reportName}</strong>
                </div>
                <div class="report_item">
                    <label>报告编码：</label><span>${report.reportCode}</span>
                </div>
                <div class="report_item">
                    <label>上交截止日期：</label><span>${report.formateDate(report.deadlineTime)}</span>
                </div>
                <div class="report_item">
                    <label>报告内容：</label><p class="report_context_p">${report.reportContext}</p>
                </div>
                <div class="report_button">
                    <button>详情</button>
                    <button>编辑</button>
                    <button>删除</button>
                </div>
            </div>

        </c:forEach>
<%--        <table>--%>
<%--            <c:forEach items="${reportList}" var="report">--%>
<%--                <tr>--%>
<%--                    <td>报告编号：</td><td>${report.id}</td>--%>
<%--                    <td>报告名称：</td><td>${report.reportName}</td>--%>
<%--                    <td>报告内容：</td><td>${report.reportContext}</td>--%>
<%--                    <td>报告截止提交日期：</td><td>${report.formateDate(report.deadlineTime)}</td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </table>--%>
    </div>
</body>
</html>
