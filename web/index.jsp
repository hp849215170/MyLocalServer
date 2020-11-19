<%@ page import="m.hp.entity.UserDataEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: HP-PC
  Date: 2020/11/15
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户信息管理</title>
</head>
<body>
<h1 align="center">客户信息列表</h1>

<form action="SyncUserDataServlet" method="post">
    <input type="text" name="browerOption">
    <input type="submit" value="查询">
</form>
</body>
</html>
