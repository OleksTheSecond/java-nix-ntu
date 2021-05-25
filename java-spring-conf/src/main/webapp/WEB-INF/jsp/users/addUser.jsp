
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Новый юзер</h3>
<form method="post" action='<c:url value="/addUsPost" />'>
    <label>Имя</label><br>
    <input name="firstName"/><br><br>
    <label>Фамилия</label><br>
    <input name="lastName"/><br><br>
    <label>Роль</label><br>
     <select name = "roleId">
         <option value="2">Докладчик</option>
         <option value="3">Слушатель</option>
     </select>
    <input type="submit" value="Save" />
</form>
</body>
</html>
