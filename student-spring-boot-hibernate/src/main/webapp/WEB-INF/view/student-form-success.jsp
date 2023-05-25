<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring-Hibernate</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>
	<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<h2 class="text-center">Student success</h2>
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title green">Student has been added/updated/deleted successfully.....</div>
				</div>
				<div class="panel-body">
					<c:url var="goHome" value="/">
					</c:url>
					<a href="${goHome}">Go Home</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>