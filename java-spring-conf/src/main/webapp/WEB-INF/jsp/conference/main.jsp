<%--
  Created by IntelliJ IDEA.
  User: vavav
  Date: 11.05.2021
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Приложение конференций</title>
</head>
<body>
<h2>Приложение конференций</h2>
<p><a href='<c:url value="/index.jsp" />'>На начальную страницу</a></p>
<p><a href='<c:url value="/addConf" />'>Создать конференцию</a></p>
<table border="1">
    <tr><th>Номер конференции</th><th>Время начала</th><th>Время конца</th>
        <th>Количество докладов</th><th>Место</th><th></th></tr>
    <c:forEach var="conf" items="${confs}">
        <tr><td>${conf.id}</td>
            <td>${conf.startTime}</td>
            <td>${conf.endTime}</td>
            <td>${conf.reportsCount}</td>
            <td>${conf.place}</td>
            <td>
                <form method="post" action='<c:url value="/deleteConf" />' style="display:inline;">
                    <input type="hidden" name="id" value="${conf.id}">
                    <input type="submit" value="Delete">
                </form>
            </td></tr>
    </c:forEach>
</table>
</body>
</html>