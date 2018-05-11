<%--
  Created by IntelliJ IDEA.
  User: baige
  Date: 2018/5/11
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
uploaded success<br/>
第一行表示上传后被放在tomcat的work目录下，并且以.tmp命名<br/>
${upload}
<br/>
第二行是上传文件本来的名字<br/>
${uploadFileName}
<br/>
第三行是上传文件的mime type<br/>
${uploadContentType}
</body>
</html>
