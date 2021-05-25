<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Chose</title>
</head>
<body>
<h2>Вход</h2>
<form method="post" action='<c:url value="/login" />' style="display:inline;">
    <label>
        Почта:
        <input type="text" name="email"/>
    </label><br><br>
    <label>
        Пароль:
        <input type="password" name="password"/>
    </label><br><br>
    <input type="submit" value="Войти">
    <p style="color: red"><c:out value="${error}" default=" "/></p>
</form>
</body>
</html>
