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


<script>
	$(function() {
		$('#lstFruits').multiselect({
			includeSelectAllOption : true
		});
	});
</script>
<script type="text/javascript">
	$(function() {
		$('#serviceType').multiselect({
			includeSelectAllOption : true
		});
	});
</script>

</head>
<body ng-App="myApp" ng-Controller="myCtrl">


	<!-- Sidenav/menu -->
	<%@ include file="Navigation.jsp"%>


	<!-- Overlay effect when opening sidenav on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()"
		style="cursor: pointer" title="close side menu" id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 340px; margin-right: 40px">


	<!-- Top menu on small screens -->
	<%@include file="Header.jsp"%>


		<div class="w3-padding-right-64" id="form3">
			<h3 style="text: bold">New DocumentType</h3>
			<form:form commandName="documentType" method="POST"
				class="w3-padding-right-60"
				action="/jsw-report/mainController/submitDocumentType">




				<div class="w3-padding-0">










					<p>
						<form:input path="name" class="w3-input w3-border" name="approver"
							type="text" placeholder="Documentname" />
					</p>

					<p>
						<form:checkbox path="isActive" id="chk2" value="y"
							checked="checked" />
						isActive <label class="w3-text-orange"></label>
					</p>








				</div>
				<br />


				<input type="submit" class="w3-btn w3-right"
					value="submit">
				</p>

			</form:form>
		</div>
		<br />





		<!-- End page content -->
	</div>

	<%@ include file="Footer.jsp"%>
</body>
</html>