

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
				<a href="" class="navbar-brand"> Loan Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listLoans"
					class="nav-link">Loans</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${userLoans != null}">
					<form action="updateloan" method="post" id="userForm">
				</c:if>
				<c:if test="${userLoans == null}">
					<form action="insertloan" method="post" id="userForm">
				</c:if>

				<caption>
					<h2>
						<c:if test="${userLoans != null}">
            			Edit Leave
            		</c:if>
						<c:if test="${userLoans == null}">
            			Add New Leave
            		</c:if>
					</h2>
				</caption>

				<c:if test="${userLoans != null}">
					<input type="hidden" name="id" value="<c:out value='${userLoans.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Employee ID</label> <input type="text" 
						value="<c:out value='${userLoans.eid}' />" class="form-control"
						name="eid"  id="eid">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Loan Type</label> <input type="text" 
						value="<c:out value='${userLoans.loneType}' />" class="form-control"
						name="loneType" id="loneType">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Start Date</label> <input type="text" required
						value="<c:out value='${userLoans.startDate}' />" class="form-control"
						name="startDate" id="startDate">
				</fieldset>

				<fieldset class="form-group">
					<label>End Date</label> <input type="text" required
						value="<c:out value='${userLoans.endDate}' />" class="form-control"
						name="endDate" id="endDate">
				</fieldset>

				<fieldset class="form-group">
					<label>Total Payroll</label>
					 <input type="text" required
						value="<c:out value='${userLoans.total}'/>" class="form-control"
						name="total" id="total">
				</fieldset>

				<button type="button" class="btn btn-success" onClick="validateInputs()">Save</button>
				</form>
			</div>
		</div>
	</div>
	
	
	<script>
	
	function validateInputs() {
		console.log("running");
		if(document.getElementById("eid").value == "") {
			alert("Employee ID is required");		
			//return false;
		} else if(document.getElementById("loneType").value == "") {
			alert("Loan type is required");
		}
		else if(document.getElementById("startDate").value == "") {
			alert("Start date is required");
		}
		else if(document.getElementById("endDate").value == "") {
			alert("End Date is required");
		}
		else if(document.getElementById("total").value == "") {
			alert("Total is required");
			
		}
		else {
			
			
			
			document.getElementById("userForm").submit();
			<c:if test="${userLoans == null}">
			alert("Insert data successfully!!!");
		    </c:if>
		    
		    <c:if test="${userLoans != null}">
			alert("Updated data successfully!!!");
		    </c:if>
		}
	}
	
	</script>
	
</body>
</html>