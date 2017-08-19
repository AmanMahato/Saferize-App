<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: amanmahato
 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Pushed To DB-Page</title>
</head>
<body>
<h3>The process is complete and all the paths are successfully added to the Database.</h3>
<h4>The Path Taken To Reach Philosophy are as fallows: </h4>
<c:forEach items="${AllPath}" var="path">
    <h5>${path}</h5>
</c:forEach>
<h4>The total number of hop to reach Philosophy is: ${count}</h4>
</body>
</html>