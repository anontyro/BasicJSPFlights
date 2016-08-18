<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>World Adventures Airlines</title>
<link rel="stylesheet" href="resources/css/normalize.css" />
<link rel="stylesheet" href="resources/css/theme.css" />
</head>
<body>
	
	<div class ="container">
		<div class = "title">Add a passenger</div>
			
			<%
				if(request.getAttribute("errors") != null){
			%>	
					<fieldset id = "err_fieldset">
						<legend align = "center">Errors</legend>
							<ul>
								<%
									if(request.getAttribute("firstNameError") != null){
								%>
									<li class="error">First Name Error</li>
								<%
									}
								 %>
								 
								 <%
									if(request.getAttribute("lastNameError") != null){
								%>
									<li class="error">Last Name Error</li>
								<%
									}
								 %>
								 
								 <%
									if(request.getAttribute("dateFormatError") != null){
								%>
									<li class="error">Date of birth invalid must be written MM/DD/YYYY</li>
								<%
									}
								 %>
							</ul>
				
				</fieldset>
			<%	
				}
			 %>
		
			<fieldset>
				<legend>Passenger details</legend>
			
				<form action="AddPassenger" method="post">
					
					<div class = "inputField">
						<label for="first-name" class="inputLabel">First name:</label>
						<input name="first-name" type="text" value="<%= request.getAttribute("first-name") %>"></input>
					</div>	
					
					<div class = "inputField">
						<label for="last-name" class="inputLabel">last name:</label>
						<input name="last-name" type="text" value="<%= request.getAttribute("last-name") %>"></input>
					</div>
					
					<div class = "inputField">
						<label for="dob" class="inputLabel">Date of Birth:</label>
						<input name="dob" type="text" value="<%= request.getAttribute("dob") %>"></input>
					</div>
					
					<div class = "inputField">
						<label for="gender" class="inputLabel">Gender:</label>
						<select name = "gender">
							<option value = "Male">Male</option>
							<option value = "Female">Female</option>
							<option value = "Unspecified">Unspecified</option>
						</select>
					</div>		
				</fieldset>
				
				<div class="inputField" id="submitField">
					<input id="submitBtn" type="submit" value="Add new passenger"></input>
				</div>
				
		</form>	
		</div>		
</body>
</html>