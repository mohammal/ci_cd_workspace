<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@include file="include.jsp"%>
               <%@ page import="java.util.*,java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Test Tracking Application</title>
 <script>

 document.getElementById('id01').style.display='block';

 </script>
</head>
<body  class="w3-light-grey w3-content" style="max-width:1600px">

<!-- Sidenav/menu -->
<!-- Sidenav/menu -->
	<%@include file="Navigation.jsp"%>

<!-- Overlay effect when opening sidenav on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px" ng-app="myApp" ng-Controller="myCtrl">
  <!-- Header -->
  		<%@include file="Header.jsp"%>

<div class="w3-row-padding w3-border w3-border-blue" id="myMain">

  <br/> <br/>
  <select path="userName" style="width:100%;height:7%"  class="w3-select w3-border w3-hover-grayscale" >
<option selected="selected" disabled>Select UserName</option>
<c:forEach items="${userList}" var="role">
        <option value="${role}" >${role}</option>
    </c:forEach>
</select>
<br/><br/>
  
  <form class="w3-container w3-card-4">
  <h5>Select user Machine</h5>
  <%
 Properties prop = new Properties();
System.out.println(request.getContextPath());
InputStream input = this.getClass().getResourceAsStream("/config/properties.properties");
System.out.println("input:"+ input);

prop.load(input);
System.out.println(prop.getProperty("OvenMachineNames")); 
String[] ovenArr=prop.getProperty("OvenMachineNames").split(";");
String[] chamberArr=prop.getProperty("ChamberMachineNames").split(";");




for(int i=0;i<ovenArr.length;i++)
{
	%>
  <p>
  <input class="w3-check" type="checkbox" id="C<%=i%>">
  <label class="w3-validate"><%=ovenArr[i]%></label></p>
  <p>
  
  <%
  }

for(int i=0;i<chamberArr.length;i++)
{%>

  <input class="w3-check" type="checkbox" id="C<%=i%>">
  <label class="w3-validate"><%=chamberArr[i]%></label></p>
  <p>
  
    <%}%>
  
  <br/>
   <input type="submit" ng-click="checks()" value="Submit" class="w3-btn w3-hover-blue" style="width:100%"><br/>
     <br/>
</form>
  
  
  
  </div>
<!-- Footer -->
<%@include file="Footer.jsp"%>

  </div>

</body>

</html>