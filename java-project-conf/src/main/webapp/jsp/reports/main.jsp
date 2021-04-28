<%--
  Created by IntelliJ IDEA.
  User: vavav
  Date: 28.04.2021
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title></title>
</head>
<body>
<h2>Work with reports</h2>
<p><a href='<c:url value="/addReport" />'>Create new</a></p>
<table>
    <tr><th>Title</th><th>User ID</th><th></th></tr>
    <c:forEach var="report" items="${reports}">
        <tr><td>${report.title}</td>
            <td>${report.userId}</td>
            <td>
                <form method="post" action='<c:url value="/deleteReport" />' style="display:inline;">
                    <input type="hidden" name="id" value="${report.id}">
                    <input type="submit" value="Delete">
                </form>
            </td></tr>
    </c:forEach>
</table>
</body>
</html>
