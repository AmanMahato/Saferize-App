<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%--
  Created by IntelliJ IDEA.
  User: amanmahato
 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Main Page</title>
</head>
<body>
<p>This page is Working fine!</p>
<form:form action="saveToDob" method="post" modelAttribute="urlDto">
    <label>Please Enter Valid Wikipedia Link:</label>
    <form:input path="url"></form:input>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>