<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Reports</title>
</head>
<body>
<c:forEach var="report" items="${reports}">
    <li><c:out value="${report}" /></li>
</c:forEach>


</body>
</html>