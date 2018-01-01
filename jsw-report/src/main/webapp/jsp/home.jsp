<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JSW</title>
<style>
.myMain {
	background-image: url("<c:url value='/img/faur.jpg'/>");
	min-height: 100%;
	height: 600px;
	background-position: center;
	background-size: cover;
	opacity: 1;
	filter: alpha(opacity = 60);
}

#myForm {
	opacity: 1.0;
}
</style>
</head>
<body class="w3-light-grey w3-content" style="max-width: 1600px"
	ng-app="myApp" ng-Controller="myCtrl">

	<!-- Sidenav/menu -->
	<%@include file="Navigation.jsp"%>

	<!-- Overlay effect when opening sidenav on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
<div class="w3-main w3-border" style="margin-left: 340px; margin-right: 40px">
		<!-- Header -->
		<%@include file="Header.jsp"%>


		<div class="w3-row-padding myMain">

			<div class=" w3-container w3-margin-bottom myOpacity">
				<div class="w3-container w3-white">
					<div class="w3-container w3-margin w3-text-blue" id="myHome">

						<span>${message}</span><br />
					</div>

				</div>
			</div>


		</div>
		
		
		
			<%@ include file="Footer.jsp"%>
	</div>


	



</body>
</html>