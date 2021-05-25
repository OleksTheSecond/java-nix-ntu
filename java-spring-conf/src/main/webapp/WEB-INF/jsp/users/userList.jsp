<%--
  Created by IntelliJ IDEA.
  User: vavav
  Date: 25.05.2021
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
    <title></title>
</head>
<body>
<form method="get" action='<c:url value="/backToProfile" />' style="display:inline;">
    <input type="hidden" name="MyId" value="${MyId}">
    <input type="submit" value="Назад к профилю">
    <table border="1">
        <tr><th>Имя</th><th>Фамилия</th></tr>
        <c:forEach var="user" items="${users}">
            <tr><td>${user.firstName}</td>
                <td>${user.lastName}</td>
            </tr>
        </c:forEach>
    </table>

</form>
</body>
</html>
