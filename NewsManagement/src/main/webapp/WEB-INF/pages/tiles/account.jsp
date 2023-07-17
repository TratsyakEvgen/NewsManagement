<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
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
<fmt:message bundle="${loc}" key="local.enter.login" var="enter_login" />
<fmt:message bundle="${loc}" key="local.enter.password"
	var="enter_password" />
<fmt:message bundle="${loc}" key="local.registration.date"
	var="registration_date" />
<fmt:message bundle="${loc}"
	key="local.enter.your.login.and.password.to.update.your.details"
	var="local_enter_your_login_and_password_to_update_your_details" />
<fmt:message bundle="${loc}" key="local.update" var="update" />
<fmt:message bundle="${loc}" key="local.delete" var="delete" />



<c:if test="${requestScope.user == null}">
	<c:set var="user" value="${sessionScope.user}"></c:set>
</c:if>
<c:if test="${requestScope.user != null}">
	<c:set var="user" value="${requestScope.user}"></c:set>
</c:if>

<c:if test="${requestScope.marker == null}">
	<c:set var="marker" value="info"></c:set>
</c:if>
<c:if test="${requestScope.marker != null}">
	<c:set var="marker" value="${requestScope.marker}"></c:set>
</c:if>


<div class="row" id="accordion">
	<div
		class="collapse collapse-horizontal <c:if test="${marker == 'info'}">show</c:if>"
		id="info" data-bs-parent="#accordion">
		<div class="row">
			<div class="col-2">
				<h5>${first_name}</h5>
			</div>
			<div class="col-10">
				<h5>${user.name}</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<h5>${last_name}</h5>
			</div>
			<div class="col-10">
				<h5>${user.surname}</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<h5>${email}</h5>
			</div>
			<div class="col-10">
				<h5>${user.email}</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<h5>${registration_date}</h5>
			</div>
			<div class="col-10">
				<h5>
					<fmt:formatDate type="date" value="${user.registerDate}" />
				</h5>
			</div>
		</div>
	</div>


	<div
		class="collapse collapse-horizontal <c:if test="${marker == 'updateInfo'}">show</c:if>"
		id="updateInfo" data-bs-parent="#accordion">
		<form action="controller" method="post">

			<input type="hidden" name="command" value="do_update_account">
			<label class="form-label">${first_name}</label>
			<div class="row p-1">
				<input type="text" name="name" class="form-control"
					value="${user.name}">
			</div>

			<label class="form-label">${last_name}</label>
			<div class="row p-1">
				<input type="text" name="surname" class="form-control"
					value="${user.surname}">
			</div>

			<label class="form-label">${email}</label>
			<div class="row p-1">
				<input type="email" name="email" class="form-control"
					value="${user.email}">
			</div>

			<hr class="my-3">

			<h5>${local_enter_your_login_and_password_to_update_your_details}</h5>

			<label class="form-label">${login}</label>
			<div class="row p-1">
				<input type="text" name="login" class="form-control"
					placeholder="${enter_login}" value="${requestScope.login}">
			</div>

			<label class="form-label">${password}</label>
			<div class="row p-1">
				<input type="password" name="password" class="form-control"
					placeholder="${enter_password}" value="${requestScope.password}">
			</div>

			<div class="row">
				<div class="col">
					<input type="submit" class="btn btn-dark btn-outline-light"
						value="${update}">
				</div>
			</div>
		</form>
	</div>


	<div
		class="collapse collapse-horizontal <c:if test="${marker == 'updatePassword'}">show</c:if>"
		id="updatePassword" data-bs-parent="#accordion">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="do_update_password">
			<label class="form-label">${password}</label>
			<div class="row p-1">
				<input type="password" name="new_password" class="form-control"
					placeholder="${enter_password}"
					value="${requestScope.new_password}">
			</div>

			<label class="form-label">${repeat_password}</label>
			<div class="row p-1">
				<input type="password" name="repeat_password" class="form-control"
					placeholder="${repeat_password}"
					value="${requestScope.repeat_password}">
			</div>

			<hr class="my-3">

			<h5>${local_enter_your_login_and_password_to_update_your_details}</h5>

			<label class="form-label">${login}</label>
			<div class="row p-1">
				<input type="text" name="login" class="form-control"
					placeholder="${enter_login}" value="${requestScope.login}">
			</div>

			<label class="form-label">${password}</label>
			<div class="row p-1">
				<input type="password" name="password" class="form-control"
					placeholder="${enter_password}" value="${requestScope.password}">
			</div>

			<div class="row">
				<div class="col">
					<input type="submit" class="btn btn-dark btn-outline-light"
						value="${update}">
				</div>
			</div>
		</form>
	</div>



	<div
		class="collapse collapse-horizontal <c:if test="${marker == 'delete'}">show</c:if>"
		id="delete" data-bs-parent="#accordion">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="do_delete_account">

			<label class="form-label">${login}</label>
			<div class="row p-1">
				<input type="text" name="login" class="form-control"
					placeholder="${enter_login}" value="${requestScope.login}">
			</div>

			<label class="form-label">${password}</label>
			<div class="row p-1">
				<input type="password" name="password" class="form-control"
					placeholder="${enter_password}" value="${requestScope.password}">
			</div>

			<div class="row">
				<div class="col">
					<input type="submit" class="btn btn-dark btn-outline-light"
						value="${delete}">
				</div>
			</div>
		</form>
	</div>
</div>
