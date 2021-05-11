<%--
  Created by IntelliJ IDEA.
  User: vavav
  Date: 28.04.2021
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title></title>
</head>
<body>
<h2>Приложение докладов</h2>
<p><a href='<c:url value="/index.jsp" />'>На начальную страницу</a></p>
<p><a href='<c:url value="/hAddReport" />'>Создать доклад</a></p>
<table>
    <tr><th>Докладчик</th><th>Тема</th><th></th></tr>
    <c:forEach var="i" begin="0" end="${end}">
        <tr>
            <td>${speakersWithRep[i]}</td>
            <td>${titles[i]}</td>
            <td>
                <form method="post" action='<c:url value="/hDeleteReport" />' style="display:inline;">
                    <input type="hidden" name="id" value="${repWithSpeakers[i]}">
                    <input type="submit" value="Delete">
                </form>
            </td></tr>
    </c:forEach>
</table>
</body>
</html>
