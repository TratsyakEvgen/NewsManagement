<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.page.error" var="error" />
<fmt:message bundle="${loc}" key="local.news.management"
	var="news_management" />


<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
<meta charset="utf-8">
<link rel="stylesheet" href="styles/bootstrap.css">

<title>${news_management}</title>
</head>


<body class="d-flex flex-column h-100">
	<c:import url="/WEB-INF/pages/tiles/header.jsp" />
	<c:if test="${requestScope.error != null}">
		<fmt:message bundle="${loc}" key="${requestScope.error}"
			var="error_code" />
		<div class="toast-container position-fixed top-0 end-0 p-3">
			<div id="liveToast" class="toast fade show" role="alert"
				aria-live="assertive" aria-atomic="true">
				<div class="toast-header">
					<svg class="bd-placeholder-img rounded me-2" width="20" height="20"
						xmlns="http://www.w3.org/2000/svg" aria-hidden="true"
						preserveAspectRatio="xMidYMid slice" focusable="false">
					<rect width="100%" height="100%" fill="red"></rect></svg>
					<strong class="me-auto">${error}</strong>
					<button type="button" class="btn-close" data-bs-dismiss="toast"
						aria-label="close"></button>
				</div>
				<div class="toast-body">${error_code}</div>
			</div>
		</div>
	</c:if>



	<div class="container-fluid flex-shrink-0">
		<div class="row">
			<div class="sidebar border border-right col-md-4 col-lg-3 p-0">
				<div class="offcanvas-md offcanvas-end" tabindex="-1"
					id="sidebarMenu" aria-labelledby="sidebarMenuLabel">
					<div class="offcanvas-header">
						<button type="button" class="btn-close"
							data-bs-dismiss="offcanvas" data-bs-target="#sidebarMenu"
							aria-label="Close"></button>
					</div>
					<div
						class="offcanvas-body d-md-flex flex-column p-1 pt-lg-3 overflow-y-auto">
						<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
					</div>
				</div>
			</div>

			<main class="col-md-8 ms-sm-auto col-lg-9 px-md-4">
				<div class="container">
					<c:import url="/WEB-INF/pages/tiles/main.jsp" />
				</div>
			</main>
		</div>
	</div>

	<footer class="footer mt-auto py-1 bg-dark text-center text-white">
		<div>
			2023 Copyright: <a class="text-white" href="https://Sharaga.com/">Sharaga.com</a>
		</div>
	</footer>

	<script src="script/popper.min.js"></script>
	<script src="script/bootstrap.js"></script>
</body>
</html>