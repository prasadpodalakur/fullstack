<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><%@ page isELIgnored="false"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.7.0.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			<h2>
				<font color='red'>Student Management</font>
			</h2>
			<hr />

			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Student List</div>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-bordered">
						<tr>
							<th>Student Id</th>
							<th>Name</th>
							<th>Entering Date</th>
							<th>Nationality</th>
							<th>Code</th>
						</tr>

						<!-- loop over and print our Students -->
						<c:forEach var="tempStudent" items="${students}">
							<tr>
								<td>${tempStudent.id}</td>
								<td>${tempStudent.name}</td>
								<td>${tempStudent.enteringDate}</td>
								<td>${tempStudent.nationality}</td>
								<td>${tempStudent.code}</td>
							</tr>
						</c:forEach>
					</table>
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









