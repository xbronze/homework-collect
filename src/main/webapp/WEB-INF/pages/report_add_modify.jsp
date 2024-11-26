<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/11/25
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>实验报告</title>
    <script>
        function uploadFile(event) {
            event.preventDefault(); // 阻止表单提交
            var filesInput = document.getElementById('filesInput');
            var formData = new FormData();
            // formData.append('files', filesInput);

            // 遍历文件输入元素的 files 属性中的文件列表
            Array.from(filesInput.files).forEach((file, index) => {
                // 由于你使用的是 MultipartFile[]，你可以简单地将所有文件附加到 'files' 键下
                // 后端将接收一个文件数组，其中每个文件都是通过 'files' 参数发送的
                // 注意：这种方法依赖于后端能够处理具有相同键名的多个文件
                formData.append('files', file);
                // 如果你想要为每个文件提供一个唯一的标识符（例如索引），你可以这样做：
                // formData.append('files[' + index + ']', file);
                // 但是，这要求后端以不同的方式解析文件（例如，作为一个 Map 或 List<MultipartFile> 的数组）
            });

            var xhr = new XMLHttpRequest();
            // xhr.open('POST', 'http://117.78.2.154:8091/file/multipart/upload', true); // 替换为你的文件上传端点
            xhr.open('POST', 'http://localhost:8091/file/multipart/upload', true); // 替换为你的文件上传端点
            xhr.onload = function () {
                if (xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    document.getElementById('attachments').value = response.files; // 假设服务器返回了 { "files": "..." }
                    alert('文件已上传，路径已添加到表单中。');
                } else {
                    alert('文件上传失败！');
                }
            };
            xhr.send(formData);
        }
    </script>
</head>
<body>
    <div>
<%--        <form onsubmit="submitForm(event)" enctype="multipart/form-data">--%>
        <form action="${pageContext.request.contextPath}/report/add" method="post">
            <label for="reportName">实验报告名称:</label>
            <input type="text" id="reportName" name="reportName" required><br><br>

            <label for="reportContext">实验报告内容要求:</label>
            <input type="text" id="reportContext" name="reportContext" required><br><br>

            <label for="deadlineTime">报告截止时间:</label>
            <input type="date" id="deadlineTime" name="deadlineTime" required><br><br>

            <label for="filesInput">选择文件:</label>
            <input type="file" id="filesInput" multiple="multiple" name="filesInput" onchange="uploadFile(event)"><br><br>

            <input type="hidden" id="attachments" name="attachments"><br><br>

            <input type="submit" value="提交">
        </form>
    </div>
</body>
</html>
