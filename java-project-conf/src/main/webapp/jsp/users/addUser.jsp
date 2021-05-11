
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>New user</h3>
<form method="post" action="/hAddUser">
    <label>First Name</label><br>
    <input name="firstName"/><br><br>
    <label>Last Name</label><br>
    <input name="lastName"/><br><br>
    <label>Role id</label><br>
     <select name = "roleId">
         <option value="2">2</option>
         <option value="3">3</option>
     </select>
    <input type="submit" value="Save" />
</form>
</body>
</html>
