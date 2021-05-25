<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Chose</title>
</head>
<body>
<h2>Начальная страница</h2>
<form action='<c:url value="/loginRedirect" />' style="display:inline;">
    <input type="submit" value="Войти">
</form>
<form action='<c:url value="/regRedirect" />' style="display:inline;">
    <input type="submit" value="Зарегестрироваться">
</form>
</body>
</html>
