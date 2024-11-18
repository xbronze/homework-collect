<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>


<form action="index.jsp" method="post">
    <input type="text" name="username" />
    <input type="submit" value="上传" />

</form>
</body>
</html>