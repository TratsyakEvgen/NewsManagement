<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<link rel="stylesheet" href="styles/bootstrap.css">
<title>News Management</title>
</head>


<body>
	<div class="container-fluid">
	
		<div class="row">
			<c:import url="/WEB-INF/pages/tiles/header.jsp" />
		</div>

		<div class="row justify-content-center">
			<div class="col-10">			
			
				<c:if test="${sessionScope.user.role == null}">
					<c:import url="/WEB-INF/pages/tiles/guestInfo.jsp" />
				</c:if>
				<c:if test="${sessionScope.user.role != null}">
					<c:import url="/WEB-INF/pages/tiles/body.jsp" />
				</c:if>
				
			</div>
		</div>
	</div>
	<script src="script/popper.min.js"></script>
	<script src="script/bootstrap.js"></script>
</body>
</html>