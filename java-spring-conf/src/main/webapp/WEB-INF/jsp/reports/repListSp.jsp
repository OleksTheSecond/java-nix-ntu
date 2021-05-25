
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action='<c:url value="/backToProfile" />' style="display:inline;">
    <input type="submit" value="Назад к профилю">
    <table>
        <tr><th>Тема доклада</th></tr>
        <c:forEach var="rep" items="${reports}">
            <tr>
                <td>${rep.title}</td>
            </tr>
        </c:forEach>
    </table>

</form>
</body>
</html>
