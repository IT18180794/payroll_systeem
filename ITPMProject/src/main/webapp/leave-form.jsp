

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
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="" class="navbar-brand"> Payroll Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listLeaves"
					class="nav-link">Leaves</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${userLeave != null}">
					<form action="updateleave" method="post" id="userForm">
				</c:if>
				<c:if test="${userLeave == null}">
					<form action="insertleave" method="post" id="userForm">
				</c:if>

				<caption>
					<h2>
						<c:if test="${userLeave != null}">
            			Edit Leave
            		</c:if>
						<c:if test="${userLeave == null}">
            			Add New Leave
            		</c:if>
					</h2>
				</caption>

				<c:if test="${userLeave != null}">
					<input type="hidden" name="id" value="<c:out value='${userLeave.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Department</label> <input type="text" 
						value="<c:out value='${userLeave.department}' />" class="form-control"
						name="department"  id="department">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Leave Type</label> <input type="text" 
						value="<c:out value='${userLeave.leavetype}' />" class="form-control"
						name="leavetype" id="leavetype">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Evidence</label> <input type="text" required
						value="<c:out value='${userLeave.evidence}' />" class="form-control"
						name="evidence" id="evidence">
				</fieldset>

				<fieldset class="form-group">
					<label>Date</label> <input type="text" required
						value="<c:out value='${userLeave.date}' />" class="form-control"
						name="date" id="date">
				</fieldset>

				<fieldset class="form-group">
					<label>Available Leaves</label>
					 <input type="text" required
						value="<c:out value='${userLeave.availableLeaves}'/>" class="form-control"
						name="availableLeaves" id="availableLeaves">
				</fieldset>

				<button type="button" class="btn btn-success" onClick="validateInputs()">Save</button>
				</form>
			</div>
		</div>
	</div>
	
	
	<script>
	
	function validateInputs() {
		console.log("running");
		if(document.getElementById("department").value == "") {
			alert("department is required");		
			//return false;
		} else if(document.getElementById("leavetype").value == "") {
			alert("Leave type is required");
		}
		else if(document.getElementById("evidence").value == "") {
			alert("Evidance of birth is required");
		}
		else if(document.getElementById("date").value == "") {
			alert("Date is required");
		}
		else if(document.getElementById("availableLeaves").value == "") {
			alert("Available leaves is required");
			
		}
		else {
			
			
			
			document.getElementById("userForm").submit();
			<c:if test="${userLeave == null}">
			alert("Insert data successfully!!!");
		    </c:if>
		    
		    <c:if test="${userLeave != null}">
			alert("Updated data successfully!!!");
		    </c:if>
		}
	}
	
	</script>
	
</body>
</html>