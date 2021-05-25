<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Новая конференция</h3>
<form method="post" action='<c:url value="/addConfPost" />'>
    <label>Время начала</label><br>
    <input type="datetime-local" name="startTime"/><br><br>
    <label>Время конца</label><br>
    <input type="datetime-local" name="endTime"/><br><br>
    <label>Место</label><br>
    <input name="place"/><br><br>
    <p style="color: red"><c:out value="${error}" default=" "/></p>
    <input type="submit" value="Save" />
</form>
</body>
</html>