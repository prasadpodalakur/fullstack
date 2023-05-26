<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<form:form action="processForm" >
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			<h2>
				<font color='red'>Student Home</font>
			</h2>
			<hr />

			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Student Menu</div>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-bordered">
						<tr>
							<td>1. Add Student</td> 
							<td>
								<input type="radio" value="Add Student" onclick="window.location.href='showForm'; return false;" class="btn btn-primary" />
							</td>
						</tr>
						<tr>
							<td>2. Display all students</td>
							<td>
								<input type="radio" value="Display all students" onclick="window.location.href='listStudents'; return false;" class="btn btn-primary" />
							</td>
						</tr>
						<tr>
							<td>3. find student by id</td>
							<td>
								<input type="radio" value="find student by id" onclick="window.location.href='showStudentFormById'; return false;" class="btn btn-primary" />
							</td>
						</tr>
						<tr>
							<td>4. find student by name</td>
							<td>
								<input type="radio" value="find student by name" onclick="window.location.href='showStudentFormByName'; return false;" class="btn btn-primary" />
							</td>
						</tr>						
						<tr>
							<td>5. find student by entering date</td>
							<td>
								<input type="radio" value="find student by entering date" onclick="window.location.href='showStudentFormByEnteringDate'; return false;" class="btn btn-primary" />
							</td>
						</tr>
						<tr>
							<td>6. update student by id</td>
							<td>
								<input type="radio" value="update student by id" onclick="window.location.href='updateStudentFormById'; return false;" class="btn btn-primary" />
							</td>
						</tr>
						<tr>
							<td>7. update student by name</td>
							<td>
								<input type="radio" value="update student by name" onclick="window.location.href='updateStudentFormByName'; return false;" class="btn btn-primary" />
							</td>
						</tr>
						<tr>
							<td>8. delete by id</td>
							<td>
								<input type="radio" value="delete by id" onclick="window.location.href='deleteStudentFormById'; return false;" class="btn btn-primary" />
							</td>
						</tr>	
						<tr>
							<td>9. delete all</td>
							<td>
								<input type="radio" value="delete all" onclick="window.location.href='deleteAllStudentsForm'; return false;" class="btn btn-primary" />
							</td>
						</tr>	
					</table>
				</div>
			</div>
		</div>

	</div>
	</form:form>
</body>

</html>









