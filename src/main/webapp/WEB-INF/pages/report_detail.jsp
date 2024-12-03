<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/12/3
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
  <script type="application/javascript">
    function deleteAttachment(id) {
      var xhr = new XMLHttpRequest();
      xhr.open('DELETE', '')
      console.log("id:", id)
    }
  </script>
</head>
<body>
<div>
  <div>
    <label>报告名称：</label><strong>${report.reportName}</strong>
  </div>
  <div>
    <label>报告编码：</label><span>${report.reportCode}</span>
  </div>
  <div>
    <label>上交截止日期：</label><span>${report.formateDate(report.deadlineTime)}</span>
  </div>
  <div>
    <label>报告内容：</label><p class="report_context_p">${report.reportContext}</p>
  </div>
  <div>
    附件：<br>
    <c:forEach items="${reportAttachmentVOList}" var="reportAttachmentVO">
      <a href="${reportAttachmentVO.downloadUrl}">${reportAttachmentVO.fileName}</a>
      <button onclick="deleteAttachment(${reportAttachmentVO.id})">删除</button>
      <br/>
    </c:forEach>

  </div>
</div>
</body>
</html>
