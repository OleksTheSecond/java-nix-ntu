
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Chose</title>
</head>
<body>
<h2>Chose option</h2>
<form method="get" action='<c:url value="/hIndexReport" />' style="display:inline;">
    <input type="submit" value="Приложение докладов">
</form>
<form method="get" action='<c:url value="/hIndexUser" />' style="display:inline;">
    <input type="submit" value="Приложение пользователей">
</form>
<form method="get" action='<c:url value="/hIndexConf" />' style="display:inline;">
    <input type="submit" value="Приложение конференций">
</form>
</body>
</html>
