
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Work with users</title>
</head>
<body>
<h2>Приложение юзеров</h2>
<p><a href='<c:url value="/index.jsp" />'>На начальную страницу</a></p>
<p><a href='<c:url value="/addUs" />'>Создать юзера</a></p>
<table>
    <tr><th>Имя</th><th>Фамилия</th><th></th></tr>
    <c:forEach var="user" items="${users}">
        <tr><td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>
                <form method="post" action='<c:url value="/deleteUs" />' style="display:inline;">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Delete">
                </form>
            </td></tr>
    </c:forEach>
</table>
</body>
</html>
