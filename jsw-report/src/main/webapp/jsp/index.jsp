<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.jsp"%>





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JSW</title>






<style>
.myMain {
	background-image: url("<c:url value='/img/fourecia.jpg'/>");
	min-height: 100%;
	height: 200px;
	background-position: center;
	background-size: cover;
	opacity: 0.2;
	filter: alpha(opacity = 60);
}

#myForm {
	opacity: 1.0;
}

nav a {
	color: white;
}
</style>
</head>
<body class="w3-light-grey w3-content" style="max-width: 1600px"
	ng-app="myApp" ng-Controller="myCtrl">

	<!-- Sidenav/menu -->
	<nav class="w3-sidenav w3-collapse w3-Indigo w3-animate-left"
		style="z-index:3;width:300px;" id="mySidenav">
	<br>
	<div class="w3-container">
		<a href="#" onclick="w3_close()"
			class="w3-hide-large w3-right w3-jumbo w3-padding" title="close menu">
			<i class="fa fa-remove"></i>
		</a>
		<%--  <img src="<c:url value='/img/vw.jpg'/>" style="width:100%;height:25%" class="w3-round"><br><br>
   
   --%>
		<h2 class="w3-center">
			<b>JSW</b>
		</h2>
		<br />
		<hr style="width: 100%; border: 3px solid white" class="w3-round">
		<br />
		<h4 class="w3-padding-0">
			<b>JSW</b>
		</h4>
		<p class="w3-text-grey"></p>
	</div>
	<a href="#search" onclick="w3_close()"
		class="w3-padding w3-border w3-round-xxlarge"><i
		class="fa fa-search fa-fw w3-margin-right"></i>SEARCH</a> <a href="#about"
		onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge"><i
		class="fa fa-male fa-fw w3-margin-right"></i>ABOUT</a> <a href="#contact"
		onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge"><i
		class="fa fa-envelope fa-fw w3-margin-right"></i>CONTACT</a> </nav>

	<!-- Overlay effect when opening sidenav on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
<div class="w3-main w3-border" style="margin-left: 340px; margin-right: 40px">
		<!-- Header -->
		<header class="w3-container" id="audiapp">

		<div class="w3-row">
			<br>

			<div class="w3-quarter w3-display-top">
				<span class="w3-opennav w3-hide-large w3-xxlarge w3-hover-text-grey"
					onclick="w3_open()"><i class="fa fa-bars"></i></span>
				<div class=" w3-hide-small">
					<img src="<c:url value='/img/jsw.png'/>"
						style="width: 80%; height: 14%" class="w3-round">
					<h3>
						<b>JSW</b>
					</h3>
					<p>India private limited</p>
				</div>


			</div>
			<div class="w3-half">
				<center>
					<h1></h1>
				</center>
			</div>

			<div class="w3-quarter w3-container">
				<img src="<c:url value='/img/jsw.png'/>"
					style="width: 100%; height: 20%" class="w3-round w3-hide-small">
				<br /> <br />
				<p class="w3-center">
					<a class=" w3-hover-text-blue" onClick="showLogin()">Sign In</a>
				</p>
			</div>

		</div>



		</header>


		<div class="w3-row-padding myMain" id="myMain">

			<div class=" w3-container w3-margin-bottom myOpacity">
				<div class="w3-container w3-white">
					<div class="w3-container w3-margin w3-text-blue w3-center" id="myHome">
						<h1>JSW Home page</h1>
						<span>${message}</span><br />
					</div>
					<form:form action="/jsw-report/login"
						class="w3-container w3-hide" method="post" id="loginForm"
						commandName="LoginBean">
						<h2 class="w3-text-blue">
							<b>Login</b>
						</h2>
						<br />
						<%--  <span class="w3-text-red">${message}</span><br/> --%>
						<form:input class="w3-input w3-text-blue" path="username"
							placeholder="username" style="width:100%" />
						<br />


						<div class="w3-row">
							<div class="w3-col" style="width: 95%">
								<form:input type="{{inputType}}" class="w3-input w3-text-blue"
									path="password" placeholder="password" id="passwordfield"
									style="width:100%" />
							</div>
							<div class="w3-col" style="width: 5%">
								<span class="glyphicon glyphicon-eye-open btn-lg w3-text-blue"
									id="myEye" ng-Click="hideShowPassword()"></span>
							</div>

						</div>
						<input type="submit" value="Login" class="w3-btn">
						<br />
						<p class="message w3-hover-blue" onClick="myFunction()">
							Not registered? <a href="#">Create an account</a>
						</p>
					</form:form>
					<form:form class="w3-hide w3-container"
						action="/jsw-report/regis" method="post" id="regisForm"
						commandName="regisBean">
						<h3>Create a New Account</h3>
						<br />


						<div class="w3-row">
							<div class="w3-col s6 w3-center w3-padding">

								<form:input type="text" class="w3-input w3-border"
									path="usename" placeholder="UserI D" style="width:100%" />
								<br />
								<form:input type="text" class="w3-input w3-border" path="name"
									placeholder="Domain" style="width:100%" />
								<br />
								<form:input type="text" class="w3-input w3-border" path="name"
									placeholder="User Name" style="width:100%" />
								<br />

								<form:input type="password" class="w3-input w3-border"
									path="name" placeholder="Password" style="width:100%" />
								<br />
								<form:input type="text" class="w3-input w3-border"
									path="nationality" placeholder="Email ID" style="width:100%" />
								<br />
								<form:input type="text" class="w3-input w3-border"
									path="passportNo" placeholder="Contact Number"
									style="width:100%" />
								<br />

							</div>
							<div class="w3-col s6 w3-center w3-padding">
								<form:input type="text" class="w3-input w3-border"
									path="issueDate" placeholder="Employee ID" style="width:100%" />
								<br />

								<form:select name="gender" class="w3-input w3-border"
									path="gender" style="width:100%">
									<br />
									<option value="Male" selected disabled>Department</option> -->
  <option value="Female">Department 1</option> -->
   <option value="Other">Department 2</option> -->
  </form:select>
								<br />


								<form:select name="actor" class="w3-input w3-border"
									path="actor" style="width:100%">
									<option value="DC" disabled selected>Select
										Application</option>
									<option value="EU">Application 1</option>
									<option value="EU">Application 2</option>
								</form:select>
								<br />

								<form:select name="actor"
									class="w3-input w3-margin-bottom-16 w3-border" path="actor"
									style="width:100%">
									<option value="DC" disabled selected>Select Role</option>
									<option value="EU">Role 1</option>
									<option value="EU">Role 2</option>
								</form:select>
								<br />

								<form:select name="actor" class="w3-input w3-border"
									path="actor" style="width:100%">
									<option value="DC" disabled selected>Select Request
										Approvel</option>
									<option value="EU">Approvel 1</option>
									<option value="EU">Approvel 2</option>
								</form:select>
								<br />



							</div>
						</div>


						<div class="w3-right">
							<input type="submit" id="res" class="w3-btn w3-grey"
								value="Create Account">

							<p class="message1" onClick="myFunction()">
								Already registered? <a href="#">Sign In</a>
							</p>
						</div>
					</form:form>
				</div>
			</div>


		</div>

	</div>
	<script>
		function myFunction() {
			var x = document.getElementById("regisForm");
			if (x.className.indexOf("w3-show") == -1) {
				x.className += " w3-show";
			} else {
				x.className = x.className.replace(" w3-show", "");
			}

			var y = document.getElementById("loginForm");
			if (y.className.indexOf("w3-show") == -1) {
				y.className += " w3-show";
			} else {
				y.className = x.className.replace(" w3-show", "");
			}
		}
		function showLogin() {

			var y = document.getElementById("loginForm");
			if (y.className.indexOf("w3-show") == -1) {

				y.className += " w3-show";

			} else {

				y.className = y.className.replace(" w3-show", "");

			}
			var x = document.getElementById("regisForm");

			x.className = x.className.replace(" w3-show", " w3-hide");
			var m = document.getElementById("myMain");

			m.className = m.className.replace(" myMain", "");

			var h = document.getElementById("myHome");

			h.className += " w3-hide";

		}
		function showRegis() {
			var x = document.getElementById("regisForm");
			if (x.className.indexOf("w3-show") == -1) {
				x.className += " w3-show";
			} else {
				x.className = x.className.replace(" w3-show", "");
			}

			var y = document.getElementById("loginForm");

			y.className = y.className.replace(" w3-show", "");

			var m = document.getElementById("myMain");

			m.className = m.className.replace(" myMain", "");
			var h = document.getElementById("myHome");

			h.className += " w3-hide";

		}
	</script>
	<script>
		var myApp = angular.module('myApp', []);
		myApp.controller('myCtrl', [ '$scope', function($scope) {

			var regis = '${regis}';
			if (regis == "yes") {
				var x = document.getElementById("regisForm");
				x.className = x.className.replace(" w3-hide", "");
				x.className += " w3-show";
				var y = document.getElementById("loginForm");
				y.className = x.className.replace(" w3-show", "");
				y.className += " w3-hide";
			}

			// Set the default value of inputType
			$scope.inputType = 'password';

			// Hide & show password function
			$scope.hideShowPassword = function() {
				if ($scope.inputType == 'password') {
					$scope.inputType = 'text';
					var x = document.getElementById("myEye");

					var y = document.getElementById("myEye2");

					x.className += " w3-green";
					y.className += " w3-green";

				} else {
					$scope.inputType = 'password';
					var x = document.getElementById("myEye");
					x.className = x.className.replace(" w3-green", "");
					var y = document.getElementById("myEye2");
					y.className = x.className.replace(" w3-green", "");

				}
			};

		} ]);
	</script>


</body>
</html>