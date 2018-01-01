<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Gionee</title>
<script>
	document.getElementById('id01').style.display = 'block';
</script>
</head>
<body class="w3-light-grey w3-content" style="max-width: 1600px"
	ng-app="myApp" ng-Controller="myCtrl">

	<!-- Sidenav/menu -->
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


		<div class="w3-row-padding w3-border w3-border-deep-orange" id="myMain">
			
			
			
			<br> 
			
<form:form action="/Gionee/POC/updateUser" method="Post"
							id="form" commandName="regisBean">
							<br />
							<div class="w3-row">


	<form:input path="id" class="w3-input w3-border" type="hidden" />

								<lable class="w3-col" style="width:150px">username</lable>
								<form:input class="w3-rest w3-input w3-border" id="username"
									path="usename" />
								<br />



								<lable class="w3-col" style="width:150px">Password</lable>
								<form:input type="text" class="w3-rest w3-input w3-border"
									path="password" id="passwordfield" />

								<lable class="w3-col" style="width:150px">Email</lable>
								<form:input type="text" class="w3-rest w3-input w3-border"
									path="email" id="passwordfield" />
									
									
									<br>
									
									<form:select path="expiryDate" style="width:100%;height:7%"
								class="w3-select w3-border w3-hover-grayscale">

								<option  disabled>Select User Level</option>
								
								
				
								<c:forEach items="${UserLevelList}" var="role">
									
								
											<c:choose> 
											
			
  <c:when test="${role==regisBean.expiryDate}">
		<option value="${role}" selected>${role}</option>

  </c:when>
  <c:otherwise>

		<option value="${role}">${role}</option>

  </c:otherwise>
</c:choose>
								
					
									
								</c:forEach>
								
							</form:select>	<br />	<br />
	<form:select path="actor" style="width:100%;height:7%"
								class="w3-select w3-border w3-hover-grayscale" required="required">

								<option  disabled>Select UserGroup</option>
								<c:forEach items="${orderList}" var="role">
									
								
											<c:choose> 
											
			
  <c:when test="${role.amount==regisBean.actor}">
		<option value="${role.amount}" selected>${role.amount}</option>

  </c:when>
  <c:otherwise>

		<option value="${role.amount}">${role.amount}</option>

  </c:otherwise>
</c:choose>
								
							
								</c:forEach>
							</form:select>
						
								
								<br />	<br />	
									 <form:select path="role" style="width:100%;height:7%"
								class="w3-select w3-border w3-hover-grayscale">

								<option  disabled>Select Role</option>
								<c:forEach items="${userRole}" var="role">
								
									
	
								 <c:choose> 
											
									
								  <c:when test="${role.roleName==regisBean.role}">
		<option value="${role.roleName}" selected>${role.roleName}</option>

  </c:when>
  <c:otherwise>

		<option value="${role.roleName}">${role.roleName}</option>

  </c:otherwise>
</c:choose>	
									
									
									
									
									
									
								</c:forEach>
							</form:select><br /><br /> 
									
									
								<br /> <input type="submit" value="Update"
									class="w3-btn w3-grey" style="width: 150px"><br />
									
									

							</div>
						</form:form>




			

			</div>
		</div>
	
<!-- Footer -->
	<%@include file="Footer.jsp"%>
	</div>
	


</body>

</html>