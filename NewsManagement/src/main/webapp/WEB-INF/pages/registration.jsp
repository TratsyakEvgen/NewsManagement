<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.lang" var="lang" />
<fmt:message bundle="${loc}" key="local.ru" var="ru" />
<fmt:message bundle="${loc}" key="local.en" var="en" />
<fmt:message bundle="${loc}" key="local.first.name" var="first_name" />
<fmt:message bundle="${loc}" key="local.enetr.first.name"
	var="enter_first_name" />
<fmt:message bundle="${loc}" key="local.last.name" var="last_name" />
<fmt:message bundle="${loc}" key="local.enetr.last.name"
	var="enter_last_name" />
<fmt:message bundle="${loc}" key="local.email" var="email" />
<fmt:message bundle="${loc}" key="local.enetr.email" var="enter_email" />
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.repeat.password"
	var="repeat_password" />
<fmt:message bundle="${loc}" key="local.language" var="language" />
<fmt:message bundle="${loc}" key="local.enter.login" var="enter_login" />
<fmt:message bundle="${loc}" key="local.enter.password"
	var="enter_password" />
<fmt:message bundle="${loc}" key="local.register" var="register" />
<fmt:message bundle="${loc}" key="local.registration" var="registration" />
<fmt:message bundle="${loc}" key="local.back" var="back" />



<!DOCTYPE html>
<html lang="en" style="height: 100%">

<head>
<meta charset="utf-8">
<link rel="stylesheet" href="styles/bootstrap.css">
<title>${registration}</title>
</head>

<body style="height: 100%">

	<div
		class="container d-flex h-100 justify-content-center align-items-center">
		<div class="row text-bg-dark rounded p-3">
			<div class="col d-flex flex-column">

				<form action="controller" method="post">

					<input type="hidden" name="command" value="do_registration">
					<label class="form-label">${first_name}</label>
					<div class="row p-1">
						<input type="text" name="name" class="form-control"
							placeholder="${enter_first_name}">
					</div>

					<label class="form-label">${last_name}</label>
					<div class="row p-1">
						<input type="text" name="surname" class="form-control"
							placeholder="${enter_last_name}">
					</div>

					<label class="form-label">${email}</label>
					<div class="row p-1">
						<input type="email" name="email" class="form-control"
							placeholder="${enter_email}">
					</div>

					<label class="form-label">${login}</label>
					<div class="row p-1">
						<input type="text" name="login" class="form-control"
							placeholder="${enter_login}">
					</div>

					<label class="form-label">${password}</label>
					<div class="row p-1">
						<input type="password" name="password" class="form-control"
							placeholder="${enter_password}">
					</div>

					<label class="form-label">${repeat_password}</label>
					<div class="row p-1">
						<input type="password" name="repeat_password" class="form-control"
							placeholder="${repeat_password}">
					</div>

					<div class="row p-1">
						<div class="col">
							<p>${language}</p>
						</div>
						<div class="col">
							<div class="dropdown">
								<button class="btn btn-dark btn-outline-light dropdown-toggle"
									type="button" data-bs-toggle="dropdown" aria-expanded="false">${lang}</button>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item"
										href="controller?command=change_local&local=en">${en}</a></li>
									<li><a class="dropdown-item"
										href="controller?command=change_local&local=ru">${ru}</a></li>
								</ul>
							</div>
						</div>
					</div>

					<div class="row d-flex justify-content-center p-1">
						<input type="submit" class="btn btn-dark btn-outline-light"
							value="${register}">
					</div>
				</form>
				<div class="row d-flex justify-content-center p-1">
					<a class="btn btn-dark btn-outline-light"
						href="controller?command=go_to_authentication">${back}</a>
				</div>

				<div class="row d-flex justify-content-center p-1 text-danger">
					<c:if test="${requestScope.error != null}">
						<fmt:message bundle="${loc}" key="${requestScope.error}" var="error" />
						<c:out value="${error}" />
					</c:if>
				</div>

			</div>
		</div>
	</div>

	<script src="script/popper.min.js"></script>
	<script src="script/bootstrap.js"></script>
</body>

</html>