<%--
  Страница просмотра юзеров
  Created by IntelliJ IDEA.
  User: Grey
  Date: 19.10.2019
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }

        a {
            padding: 10px 20px;
            text-decoration: none;
        }

        .button_link {
            padding: 10px 15px;
            background: #4479BA;
            color: #FFF;
            margin: 10px 90px;
        }
    </style>
</head>
<body>
<div align="center">
    <h3>Users List</h3>
    <c:if test="${!empty listUsers}">
        <table class="tg">
            <tr>
                <th width="80">User ID</th>
                <th width="120">Role ID</th>
                <th width="120">Login</th>
                <th width="120">Password</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${listUsers}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.roleId}</td>
                    <td>${user.login}</td>
                    <td>${user.password}</td>
                    <td><a href="<c:url value='/notes/users/user/${user.id}' />">Edit</a></td>
                    <td><a href="<c:url value='/notes/users/user/delete/${user.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <br>
    <a class="button_link" href="<c:url value="/notes/users/user/new"/>">Add New User</a>
    <a class="button_link" href="<c:url value="/notes/"/>">Back to main page</a>
</div>
</body>
</html>
