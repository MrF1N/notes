<%--
  Created by IntelliJ IDEA.
  User: Grey
  Date: 19.10.2019
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <style type="text/css">
        .error {
            color: #ff0000;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div align="center">
    <h2>Add/Edit User</h2>
    <c:set var="act" scope="page" value="add"/>
    <c:if test="${!empty user.id}">
        <c:set var="act" scope="page" value="edit"/>
    </c:if>
    <form:form action="${act}" method="post" modelAttribute="user">
        <table border="0" cellpadding="5">
            <tr>
                <td>ID:</td>
                <td>${user.id}
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>
                <td>Login:</td>
                <td><form:input path="login"/></td>
                <td><form:errors path="login" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input path="password"/></td>
                <td><form:errors path="password" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Role Id:</td>
                <td><form:input type="number" min="1" max="10" path="roleId"/></td>
                <td><form:errors path="roleId" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="2">
                <c:if test="${!empty user.id}">
                    <input type="submit"
                           value="<spring:message text="Edit User"/>"/>
                </c:if>
                <c:if test="${empty user.id}">
                    <input type="submit"
                           value="<spring:message text="Add User"/>"/>
                </c:if>
                </td>
            </tr>
        </table>
    </form:form>
</div>

</body>
</html>
