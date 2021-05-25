
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
        <tr><th>Докладчик</th><th>Тема</th><th></th></tr>
        <c:forEach var="i" begin="0" end="${end}">
            <tr>
                <td>${speakersWithRep[i]}</td>
                <td>${titles[i]}</td>
                <td>
                    <form method="post" action='<c:url value="/deleteRep" />' style="display:inline;">
                        <input type="hidden" name="id" value="${repWithSpeakers[i]}">
                        <input type="submit" value="Delete">
                    </form>
                </td></tr>
        </c:forEach>
    </table>

</form>
</body>
</html>
