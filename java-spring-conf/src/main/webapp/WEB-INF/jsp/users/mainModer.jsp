
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Work with users</title>
</head>
<body>
<h2>Ваш профиль</h2>
<p><a href='<c:url value="/index.jsp" />'>На начальную страницу</a></p>
<table>
    <tr><td>Имя</td><td>${user.firstName}</td></tr>
    <tr><td>Фамилия</td><td>${user.lastName}</td></tr>
    <tr><td>Почта</td><td>${user.email}</td></tr>
    <tr>
        <td>
            <form method="get" action='<c:url value="/addConf" />'>
                <input type="submit" value="Создать конференцию">
            </form>
        </td>
        <td>
            <form method="get" action='<c:url value="/addRep" />'>
                <input type="submit" value="Создать доклад">
            </form>
        </td>
        <td>
            <form method="get" action='<c:url value="/updateUser" />'>
                <input type="hidden" name="id" value="${user.id}">
                <input type="submit" value="Обновить профиль">
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form method="get" action='<c:url value="/repList" />'>
                <input type="submit" value="Список докладов">
            </form>
        </td>

        <td>
            <form method="get" action='<c:url value="/userList" />'>
                <input type="hidden" name="id" value="${user.id}">
                <input type="submit" value="Список пользователей">
            </form>
        </td>
        <td>
            <form method="get" action='<c:url value="/confList" />'>
                <input type="submit" value="Список конференций">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
