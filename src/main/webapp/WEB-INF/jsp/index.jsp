<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<%--
  Главная страница
  Created by IntelliJ IDEA.
  User: Grey
  Date: 19.10.2019
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DB Site</title>
    <style type="text/css">
        a {
            padding: 10px 20px;
            text-decoration: none;
            background: #4479BA;
            color: #FFF;
            margin: 10px 20px;
        }
    </style>
</head>
<body>
<div align="center">
    <p>Work with DB over ORM</p>
    <br>
    <a href="<c:url value="/notes/users"/>">Work with Users</a>
    <a href="<c:url value="/notes/notes"/>">Work with Notes</a>
    <a href="<c:url value="/notes/groups"/>">Work with Note Groups</a>
</div>
</body>
</html>
