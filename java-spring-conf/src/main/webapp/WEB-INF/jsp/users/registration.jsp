
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Регистрация</h3>
<form method="post" action='<c:url value="/reg" />'>
    <label>Имя</label>
    <input name="firstName" value='<c:out value="${firstName}" default=""/>'/><br><br>
    <label>Фамилия</label>
    <input name="lastName" value='<c:out value="${lastName}" default=""/>'/><br><br>
    <label>Роль</label>
    <select name = "roleId">
        <option value="2">Докладчик</option>
        <option value="3">Слушатель</option>
    </select><br><br>
    <label>Почта</label>
    <input name="email" value='<c:out value="${email}" default=""/>'/><br><br>

    <label>Пароль</label>
    <input type='password' name="password"/><br><br>
    <p style="color: red"><c:out value="${error}" default=" "/></p>
    <input type="submit" value="Зарегестрироваться"/>
</form>
</body>
</html>
