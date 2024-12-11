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
    <!-- 引入 wangEditor 的 CSS 和 JS -->
    <link rel="stylesheet" href="https://unpkg.com/wangeditor@5.1.23/dist/css/style.css">
    <link
            href="https://unpkg.com/@wangeditor/editor@latest/dist/css/style.css"
            rel="stylesheet"
    />
    <style>
        /* 自定义编辑器容器样式 */
        #editor—wrapper {
            border: 1px solid #ccc;
            z-index: 100; /* 按需定义 */
        }
        #toolbar-container {
            border-bottom: 1px solid #ccc;
        }
        #editor-container {
            height: 500px;
        }
    </style>



    <script type="text/css" src="${pageContext.request.contextPath}/resources/css/index.css" />

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
    <div class="container">
        <div class="container-body">
            <%--        <form onsubmit="submitForm(event)" enctype="multipart/form-data">--%>
            <form action="${pageContext.request.contextPath}/report/submitContext" method="post">
                <input type="text" name="id" value="${report.id}" hidden="hidden">

                <label for="reportCode">实验报告编号:</label>
                <input type="text" id="reportCode" name="reportCode" value="${report.reportCode}" readonly="readonly"><br><br>

                <label for="reportName">实验报告名称:</label>
                <input type="text" id="reportName" name="reportName" value="${report.reportName}" required><br><br>

                <label for="reportIntroduction">实验报告简介:</label>
                <input type="text" id="reportIntroduction" name="reportIntroduction" value="${report.reportIntroduction}"><br><br>

                <label for="reportContext">实验报告内容要求:</label>
                <!-- 编辑器容器 -->
                <div id="editor—wrapper">
                    <div id="toolbar-container"><!-- 工具栏 --></div>
                    <div id="editor-container"><!-- 编辑器 --></div>
                </div>
                <input type="hidden" id="reportContext" name="reportContext"><br><br>

                <label for="deadlineTime">报告截止时间:</label>
                <input type="date" id="deadlineTime" name="deadlineTime" value="${report.formateDate(report.deadlineTime)}" required><br><br>

<%--                <label for="filesInput">选择文件:</label>--%>
<%--                <input type="file" id="filesInput" multiple="multiple" name="filesInput" onchange="uploadFile(event)"><br><br>--%>

<%--                <input type="hidden" id="attachments" name="attachments"><br><br>--%>

                <input type="submit" value="提交">
                <input type="reset" value="重置">
            </form>
        </div>
    </div>
</body>

<script src="https://unpkg.com/@wangeditor/editor@latest/dist/index.js"></script>
<script>
    const { createEditor, createToolbar } = window.wangEditor

    // 初始化 wangEditor
    const editorConfig = {
        placeholder: '请输入内容...',
        onChange(editor) {
            // 当内容改变时，将内容写入隐藏的 input 中
            document.getElementById('reportContext').value = editor.getHtml()
            // console.log('editor content', editor.getHtml())
            // 也可以同步到 <textarea>
        },
    }

    const editor = createEditor({
        selector: '#editor-container',
        html: '${report.reportContext}',
        config: editorConfig,
        mode: 'default', // or 'simple'
    })

    const toolbarConfig = {}

    const toolbar = createToolbar({
        editor,
        selector: '#toolbar-container',
        config: toolbarConfig,
        mode: 'default', // or 'simple'
    })
</script>
</html>
