<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/11/13
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table style="border: 2px solid black">
        <tr>
            <td style="width: 500px; text-align: center">文件上传</td>
        </tr>
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/file/upload" method="post" enctype="multipart/form-data">
                    <input type="file" name="uploadFiles" multiple="multiple"><br/>
                    <input type="submit" value="上传"><br/>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
