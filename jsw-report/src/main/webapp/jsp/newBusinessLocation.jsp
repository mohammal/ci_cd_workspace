<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="include.jsp"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dessault Aviation</title>
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

		<!-- Photo grid (modal) -->


		<h3>
			<b>Business Details</b>
		</h3>
		<br />
		<form:form class="w3-container" commandName="BusinessManagementBean" method="POST"  action="/DessaultAviation/POC/submitbusinesslocation" >
			


			<%-- <p>

				<form:input path="id" class="w3-input w3-border" name="dealer"
					type="text" placeholder=" Id" />
			</p> --%>
			<%-- <p>
				<form:select path="partnertype" class="w3-select w3-border"
					name="option">
					<option value="" disabled selected>---Partner Type----</option>
					<option value="IOP">IOP</option>
					<option value="IOP">OEM</option>
					<option value="IOP">...</option>
				</form:select>
			</p> --%>
			<p>

				<form:input path="businessnumber" class="w3-input w3-border"
					name="approver" type="text" placeholder="Business Number" />
			</p>

			<%-- <p>
  

  <form:input path="address" class="w3-input w3-border" name="approver" type="text"  placeholder="Partner Address"/></p> --%>
			<p>
			<p>
                
				<form:input path="businessname" class="w3-input w3-border"
					name="approver" type="text" placeholder="Business Name" />
			</p>
			
			<p>
              
				<form:input path="description" class="w3-input w3-border"
					name="approver" type="text" placeholder="Description" />
			</p>
		<%-- 	<p>
                  
				<form:input path="contact" class="w3-input w3-border"
					name="approver" type="text" placeholder="Contact" />
			</p> --%>
			<p>
                
		        is Active:<form:checkbox path="isActive" value="yes" class="w3-check"
				style="margin-right:20%" /> 
			</p>
		
			
			<p>
			<input type="submit" class="w3-btn w3-right">
			</p>
   
				
				
		</form:form>
		<!-- <form action="UploadFile" method="post" enctype="multipart/form-data">
       Upload Question Set : <input type="file" name="filetoupload">
</form> -->
       
		<!-- Modal for full size images on click-->

		<br />
		<br />
		<%@ include file="Footer.jsp"%>

		<!-- End page content -->


	</div>
</body>



</body>
</html>