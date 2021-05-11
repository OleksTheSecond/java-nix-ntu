<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Новая конференция</h3>
<form method="post" action="/hAddConf">
    <label>Время начала</label><br>
    <input type="datetime-local" name="startTime"/><br><br>
    <label>Время конца</label><br>
    <input type="datetime-local" name="endTime"/><br><br>
    <label>Место</label><br>
    <input name="place"/><br><br>
    <input type="submit" value="Save" />
</form>
</body>
</html>