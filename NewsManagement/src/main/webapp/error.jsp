<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="status" value="${pageContext.errorData.statusCode}" />
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.back" var="back" />
<fmt:message bundle="${loc}" key="local.page.error" var="page_error" />
<fmt:message bundle="${loc}" key="local.opps" var="opps" />
<fmt:message bundle="${loc}" key="local.page.not.found" var="page_not_found" />
<fmt:message bundle="${loc}" key="local.internal.server.error" var="server_error" />
<fmt:message bundle="${loc}" key="local.the.page.youre.looking.for.doesnt.exist" var="page_exist" />
<fmt:message bundle="${loc}" key="local.we.are.already.working.on.the.problem" var="working_on_problem" />

<!DOCTYPE html>
<html lang="en">


<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>${page_error}</title>
<link rel="stylesheet" href="styles/bootstrap.css">
</head>


<body>
	<div class="d-flex align-items-center justify-content-center vh-100">
		<div class="text-center">
			<h1 class="display-1 fw-bold">
				<c:out value="${status}" />
			</h1>
			<p class="fs-3">
				<span class="text-danger">${opps}</span>
				<c:if test="${status == '404'}">${page_not_found}<p
						class="lead">${page_exist}</p>
				</c:if>
				<c:if test="${status != '404'}">${server_error}<p
						class="lead">${working_on_problem}</p>
				</c:if>
			</p>
			<a href="${sessionScope.go_to_back}" class="btn btn-dark">${back}</a>
		</div>
	</div>
</body>


</html>