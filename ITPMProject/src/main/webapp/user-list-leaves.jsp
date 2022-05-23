<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Payroll Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="" class="navbar-brand"> Payroll
					Management System </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listLeaves" class="nav-link">Leaves</a></li>
				<li><a href="user-list-loans.jsp" class="nav-link">Loans</a></li>
			</ul>
			
			
		</nav>
	</header>
	<br>


<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Leaves</h3>
			<hr>
			<div class="container text-left">
			
				<a href="<%=request.getContextPath()%>/newLeave" class="btn btn-success">Add
					New Leave</a>
				
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Department</th>
						<th>Leave Type</th>
						<th>Evidence</th>
						<th>Date</th>
						<th>Available Leaves</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="userLeave" items="${listUserLeave}">

						<tr>
							<td><c:out value="${userLeave.id}" /></td>
							<td><c:out value="${userLeave.department}" /></td>
							<td><c:out value="${userLeave.leavetype}" /></td>
							<td><c:out value="${userLeave.evidence}" /></td>
							<td><c:out value="${userLeave.date}" /></td>
							<td><c:out value="${userLeave.availableLeaves}" /></td>
							<td><a href="editleave?id=<c:out value='${userLeave.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deleteleave?id=<c:out value='${userLeave.id}' />" onclick="return confirm('Are you sure you want to delete this item')">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	
		
	</div>
</body>
</html>