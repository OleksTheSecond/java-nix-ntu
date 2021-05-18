<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Report</title>
</head>
<body>
<h3>New report</h3>
<form method="post" action='<c:url value="/addRepPost" />'>
    <label>Тема доклада</label><br>
    <input name="title"/><br><br>
    <label>Имя докладчика</label><br><br>
    <select name="userId">
        <c:forEach var='userValue' items='${user}'>
            <option value='${userValue.id}'>
                    ${userValue.firstName} ${userValue.lastName}
            </option>
        </c:forEach>
    </select><br><br>
    <label>Номер конференции</label><br><br>
    <select name="confId">
        <c:forEach var='confValue' items='${conf}'>
            <option value='${confValue.id}'>
                ${confValue.id}
            </option>
        </c:forEach>
    </select><br><br>
    <input type="submit" value="Создать" />
</form>
</body>

</html>