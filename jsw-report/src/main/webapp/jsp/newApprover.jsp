<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.jsp"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>JSW</title>


<style>
body, h1, h2, h3, h4, h5 {
	font-family: "Poppins", sans-serif
}

body {
	font-size: 16px;
}

.w3-half img {
	margin: 25px;
	opacity: 0.8;
	cursor: pointer;
	height: 180px;
	width: 50px;
}

.w3-half img:hover {
	opacity: 1
}

.myheader {
	margin-left: 300px;
}
</style>
</head>
<body ng-App="myApp" ng-Controller="myCtrl">


	<!-- Sidenav/menu -->
	<%@ include file="Navigation.jsp"%>



	<!-- Overlay effect when opening sidenav on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()"
		style="cursor: pointer" title="close side menu" id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px;">
	<!-- Top menu on small screens -->
	<%@ include file="Header.jsp"%>
		<h3>
			<b>New Approver</b>
		</h3>
		<br />
		<form:form class="w3-container w3-card-4" method="POST"
			commandName="approverBean" action="/jsw-report/mainController/addApprover">



			<p>

				<form:select class="w3-select w3-border" path="userName"
					name="option">
					<option value="" disabled selected>User</option>
					<option value="User 1">User 1</option>
					<option value="User 2">User 2</option>
					<option value="User 3">User 3</option>
				</form:select>
			</p>
			<p>
				<form:select class="w3-select w3-border" path="approverName"
					name="option">
					<option value="" disabled selected>Approver</option>
					<option value="User 1">User 1</option>
					<option value="User 2">User 2</option>
					<option value="User 3">User 3</option>
				</form:select>
			</p>
			<p>
				<form:select class="w3-select w3-border" path="backupName"
					name="option">
					<option value="" disabled selected>Backup</option>
					<option value="User 1">User 1</option>
					<option value="User 2">User 2</option>
					<option value="User 3">User 3</option>
				</form:select>
			</p>
			<p>

				<input type="submit" class="w3-btn w3-right">
			</p>
		</form:form>

		<!-- Modal for full size images on click-->

		<br />
		<br />	<%@ include file="Footer.jsp"%>
	</div>


	<!-- End page content -->


</body>
</html>