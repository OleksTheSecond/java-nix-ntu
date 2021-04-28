
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Chose</title>
</head>
<body>
<h2>Chose option</h2>
<form method="get" action='<c:url value="/indexReport" />' style="display:inline;">
    <input type="submit" value="Work with Reports">
</form>
<form method="get" action='<c:url value="/indexUser" />' style="display:inline;">
    <input type="submit" value="Work with Users">
</form>
</form>
</body>
</html>
