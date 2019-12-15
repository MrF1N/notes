<%--
  Страница создания/изменения групп
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
    <title>Group</title>
    <style type="text/css">
        .error {
            color: #ff0000;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div align="center">
    <h2>Add/Edit Group</h2>
    <c:set var="act" scope="page" value="add"/>
    <c:if test="${!empty group.id}">
        <c:set var="act" scope="page" value="edit"/>
    </c:if>
    <form:form action="${act}" method="post" modelAttribute="group">
        <table border="0" cellpadding="5">
            <tr>
                <td>ID:</td>
                <td>${group.id} <form:hidden path="id"/></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><form:input type="text" path="name"/></td>
                <td><form:errors path="name" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${!empty group.id}">
                        <input type="submit"
                               value="<spring:message text="Edit Group"/>"/>
                    </c:if>
                    <c:if test="${empty group.id}">
                        <input type="submit"
                               value="<spring:message text="Add Group"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
</div>

</body>
</html>