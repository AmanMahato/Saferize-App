<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<c:choose>
    <c:when test="${startFromPhilosophy==true}">
        You are already on philosophy wiki page. Please try again!
    </c:when>

    <c:when test="${urlNotValid==true}">
        URL provided is Invalid. Please try again!
    </c:when>

    <c:when test="${hopExceeded==true}">
        Maximum Hop Limit of 500 exceeded. Philosophy was not found within the depth of 500 hops. Please try again with some other starting wiki page.
    </c:when>

    <c:otherwise>

    </c:otherwise>
</c:choose>
</body>
</html>