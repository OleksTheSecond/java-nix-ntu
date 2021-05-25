<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chose</title>
</head>
<body>
<h2>Обновление профиля</h2>
<form method="post" action='<c:url value="/updateUserPost" />' style="display:inline;">
    <label>
        Имя:
        <input type="text" name="firstName" value='${user.firstName}'>
    </label><br>
    <label>
        Фамилия:
        <input type="text" name="lastName" value='${user.lastName}'>
    </label><br>
    <label>
        Почта:
        <input type="text" name="email" value='${user.email}'>
    </label><br>
    <input type="submit" value="Обновить">
</form>
</body>
</html>
