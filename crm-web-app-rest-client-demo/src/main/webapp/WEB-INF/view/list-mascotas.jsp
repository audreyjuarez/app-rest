<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Mascotas</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Mascota Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add mascota -->
		
			<input type="button" value="Add Mascota"
				   onclick="window.location.href='showFormForAdd'; return false;"
				   class="add-button"
			/>
		
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- loop over and print our mascotas -->
				<c:forEach var="tempMascota" items="${mascotas}">
				
					<!-- construct an "update" link with mascota id -->
					<c:url var="updateLink" value="/mascota/showFormForUpdate">
						<c:param name="mascotaId" value="${tempMascota.id}" />
					</c:url>					

					<!-- construct an "delete" link with mascota id -->
					<c:url var="deleteLink" value="/mascota/delete">
						<c:param name="mascotaId" value="${tempMascota.id}" />
					</c:url>					
					
					<tr>
						<td> ${tempMascota.firstName} </td>
						<td> ${tempMascota.lastName} </td>
						<td> ${tempMascota.email} </td>
						
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('Are you sure you want to delete this mascota?'))) return false">Delete</a>
						</td>
						
					</tr>
				
				</c:forEach>
						
			</table>
				
		</div>
	
	</div>
	

</body>

</html>









