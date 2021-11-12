
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload File</title>
</head>
<body>
<h3> Upload File</h3>
<form method="post" action="/employee/UploadFileServlet" enctype="multipart/form-data">
    Select file to upload: <input type="file" name="file" size="60" /><br /><br />
    <input type="submit" value="Upload" />
</form>
<h2>${requestScope.message}</h2>

</body>
</html>
