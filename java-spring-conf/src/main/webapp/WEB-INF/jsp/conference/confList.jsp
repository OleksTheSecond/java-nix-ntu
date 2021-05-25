<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
</head>
<body>
<h2>Приложение конференций</h2>
<form method="get" action='<c:url value="/backToProfile" />' style="display:inline;">
    <input type="submit" value="Назад к профилю">
<table border="1">
    <tr><th>Номер конференции</th><th>Время начала</th><th>Время конца</th>
        <th>Количество докладов</th><th>Место</th></tr>
    <c:forEach var="conf" items="${confs}">
        <tr><td>${conf.id}</td>
            <td>${conf.startTime}</td>
            <td>${conf.endTime}</td>
            <td>${conf.reportsCount}</td>
            <td>${conf.place}</td>
           </tr>
    </c:forEach>
</table>
</form>
</body>
</html>
