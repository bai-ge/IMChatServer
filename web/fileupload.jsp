<%--
  Created by IntelliJ IDEA.
  User: baige
  Date: 2018/5/11
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>

</head>
<body>

<form action="file/upload.action" method="post" enctype="multipart/form-data">

    上传文件 : <input type="file" name="upload" /> <br>
    <input type="submit" value="上传">
</form>

</html>
