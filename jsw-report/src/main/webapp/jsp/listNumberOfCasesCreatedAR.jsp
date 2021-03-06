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

	<!-- Top menu on small screens -->

	<!-- Overlay effect when opening sidenav on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()"
		style="cursor: pointer" title="close side menu" id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 340px; margin-right: 40px">
	<%@ include file="Header.jsp"%>
		<!-- Header -->
		<div class="w3-container" style="margin-top: 0px" id="showcase">
			<br />

		</div>

		<!-- Photo grid (modal) -->
		<div class="w3-row-padding">
			<div id="myTableList">
				<div class="w3-container">

					<h3 class="w3-text-blue">
						<b>Number of Cases Created</b>
					</h3>
				</div>
 
<form:form commandName="FunctionAppBean" action="/jsw-report/mainController/submitNumberOfCasesCreatedAR">				
				<table
					class="w3-table w3-hoverable w3-striped w3-bordeorange ">
				
					<%int count=0; %>
					<c:forEach var="functionName" items="${functionTable}">
			 			<%
						
						
						if(count%2==0){%> 
	<tr class="">
	
 <% }%> 
	<td><label>${functionName.name}</label></td>
	
	
	<td>
<select name="${functionName.name}">
<c:choose>
    <c:when test="${functionName.name!='DisplayBy'}">
 <option value="ALL">ALL</option>
     </c:when>    
  
</c:choose>
    <c:forEach items="${functionName.value}" var="value">
    
        <option value="${value}">${value}</option>
    </c:forEach>
</select>
					</td>		
				
		 	<% if(count%2==0){}
			else{%>
			
			</tr>
			
		<%} 	count++; %> 
	
<c:choose>
    <c:when test="${functionName.name=='Location'}">
        <tr><td><label>Date From </label></td><td><input type="date" name="fromDate" ></td>
       <td><label>Date To S</label></td><td><input type="date" name="toDate" ></td></tr>
    </c:when>    
  
</c:choose>
			</c:forEach>		
				</table>
				<br />
				<input type="submit" onClick="getCaseCreatedTable()" class="w3-btn w3-right" style="width:15%" value="Go">
</form:form> 
			</div>

			<br /> <br />
		<c:if test="${not empty listCasesCreated}"> 
    
<table
					class="w3-table-all w3-hoverable w3-striped w3-border w3-small w3-center">
					<thead>
						<b><tr class="w3-text-white w3-blue w3-center w3-medium">
						
						
							<td class="w3-center">Business / Location</td>
						
						
						<td class="w3-center">Period 1</td>
						
						<td class="w3-center">Period 2</td>
						
						<td class="w3-center">Period 3</td>
						<td class="w3-center">Count</td>
							
						</tr></b>

<% String companyName=""; %>


						<c:forEach var="listCases" items="${listCasesCreated}">
							<tr class="">
	 <c:set var="companyName" value="${listCases.company}"/>
 <%
 
    if(companyName.equals((String)pageContext.getAttribute("companyName"))){}
    else{
    	
    
  %>
  			<tr class="w3-light-blue w3-medium"><td class="">${listCases.company}</td>
  			<td class="w3-center">Total of all ${listCases.company} Locations</td>
  			<td class="w3-center">Total of all ${listCases.company} Locations</td>
  			<td class="w3-center">Total of all ${listCases.company} Locations</td>
    			<td>Total of all period on Table</td>
  
  </tr>
  
  <%}
 
 companyName=(String)pageContext.getAttribute("companyName");%>
					
							<td class="w3-hide">${listCases.company}</td>
								<td class="w3-center">${listCases.location}</td>
									<td class="w3-center">${listCases.period1}</td>
										<td class="w3-center">${listCases.period2}</td>
											<td class="w3-center">${listCases.period3}</td>
								<td onclick="document.getElementById('id01').style.display='block',getCaseStudiedTable(this)" class="w3-center w3-text-blue"><u class="w3-hover-blue w3-hover-text-black">${listCases.count}</u></td>
								

							</tr>
						</c:forEach>
						
						
						<tr class="w3-text-white w3-black w3-center w3-medium">
						
						
							<td class="w3-center">Grand Total</td>
						
						
						<td class="w3-center">Total of all Business Locations</td>
  			<td class="w3-center">Total of all Business Locations</td>
  			<td class="w3-center">Total of all Business Locations</td>
    			<td>Total of all column on totals</td>
							
						</tr>
				</table><br><br>

</c:if>	 
			<!-- End page content -->
		</div>
		
		
		<div id="id01" class="w3-modal" style="width:100%">
    <div class="w3-modal-content"  style="width:100%">
      <header class="w3-container "> 
        <span onclick="document.getElementById('id01').style.display='none'" 
        class="w3-button w3-display-topright">&times;</span>
        <h2>Case Detail</h2>
      </header>
      <div class="w3-container" id="caseTable"   style="width:100%">
     
      </div>
      <footer class="w3-container ">
        <p></p>
      </footer>
    </div>
  </div>
	<%@ include file="Footer.jsp"%>
	</div>
 <script>

function getCaseStudiedTable(thisVariable){
	$("#caseTable").html("");
	var firstColumnValue=$(thisVariable).closest('tr').children('td:first').text();
	    
	 var  secondColumnValue = $(thisVariable).closest("tr").find("td:eq(1)").text();
  
    
    $.ajax({                    
    	  url: '/jsw-report/NW/getCaseCreatedCompanyAR/'+firstColumnValue+"@"+secondColumnValue,     
    	  type: 'GET',
    	async:false,
    	cache:false,
    	  success: function(data)         
    	  {
    		  
    	   var newJsonresultData=JSON.parse(data);
    	   var tableString="<table class='w3-table-all w3-hoverable w3-striped w3-border '>";
    	
    		
    	   
    	   
    	   
    		tableString+="<tr class='w3-blue w3-text-white'><td>FCaseFolderID</td><td class='w3-light-blue w3-text-white'>UseName</td><td>DateCreated</td><td class='w3-light-blue w3-text-white'>gbllocation</td><td>GblBarCodeDC</td><td class='w3-light-blue w3-text-white'>GblDocumentSource</td>"
    	   

        		tableString+="<td>GblAmount</td><td class='w3-light-blue w3-text-white'>StepName</td><td>StartTime</td><td class='w3-light-blue w3-text-white'>EndTime</td></tr>";
    	   
    	   for (var jsonOuterKey in newJsonresultData){
    		   tableString+="<tr>";
    		
    		   var newInnerJsonresultData=(newJsonresultData[jsonOuterKey]).toString();
    		
    		   var newInnerJsonresultDataArray = new Array();
    		 
    		   newInnerJsonresultDataArray = newInnerJsonresultData.split(",");
    		   
    		   for (a in newInnerJsonresultDataArray ) {
    		
    			  
    			  tableString+="<td>"+newInnerJsonresultDataArray[a]+"</td>";
    			}
    		   
    		     
    		     tableString+="</tr>";
    		    
    	   }
    	   tableString+="</table>";
    	   
    	
    	   $(tableString).appendTo("#caseTable");
    	  } 
    	});
    
    
    
    
    
    
    
    
    
    
    
}
/* 	

	
	 $('table').find('select,input').each(function() {
		 
	        alert($(this).val());
	        
	    }); */
	
	

</script> 
</body>
</html>