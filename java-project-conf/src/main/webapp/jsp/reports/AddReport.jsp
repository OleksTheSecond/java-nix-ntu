<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Report</title>
</head>
<body>
<h3>New report</h3>
<form method="post" action="/addReport">
    <label>Title</label><br>
    <input name="title"/><br><br>
    <label>User id</label><br>
    <input name="userId"/><br><br>


    <input type="submit" value="Save" />
</form>
</body>
</html>