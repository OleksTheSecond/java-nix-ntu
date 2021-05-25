<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Update</title>
</head>
<body>
<h3>Обновить конференцию</h3>
<form method="post" action='<c:url value="/updateConfPost" />'>
    <input type="hidden" value="${conf.id}" name="id" />
    <input type="hidden" value="${conf.reportsCount}" name="reportsCount" />
    <label>
        Время начала:
        <input type="datetime-local" value="${conf.startTime}" name="startTime"/>
    </label>
    <label>
        Время конца:
        <input type="datetime-local" value="${conf.endTime}" name="endTime"/>
    </label>

    <label>
        Место:
        <input type="text" value="${conf.place}" name="place"/>
    </label>

    <input type="submit" value="Обновить" />
</form>
</body>
</html>
