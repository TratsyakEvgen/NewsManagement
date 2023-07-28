<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.first.name" var="first_name" />
<fmt:message bundle="${loc}" key="local.last.name" var="last_name" />
<fmt:message bundle="${loc}" key="local.email" var="email" />
<fmt:message bundle="${loc}" key="local.registration.date"
	var="registration_date" />
<fmt:message bundle="${loc}" key="local.id" var="id" />
<fmt:message bundle="${loc}" key="local.role" var="role" />
<fmt:message bundle="${loc}" key="local.select" var="select" />
<fmt:message bundle="${loc}" key="local.user" var="user_local" />
<fmt:message bundle="${loc}" key="local.admin" var="admin" />
<fmt:message bundle="${loc}" key="local.deleted" var="deleted" />



<div class="row table-responsive">
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">${id}</th>
				<th scope="col">${first_name}</th>
				<th scope="col">${last_name}</th>
				<th scope="col">${email}</th>
				<th scope="col">${registration_date}</th>
				<th scope="col">${role}</th>
				<th scope="col">${select}</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${requestScope.user_list}">
				<tr>
					<th scope="row">${user.id}</th>
					<td>${user.name}</td>
					<td>${user.surname}</td>
					<td>${user.email}</td>
					<td><fmt:formatDate type="date" value="${user.registerDate}" /></td>
					<td><c:if test="${user.role == 'admin'}">${admin}</c:if> <c:if
							test="${user.role == 'user'}">${user_local}</c:if> <c:if
							test="${user.role == 'delete'}">${deleted}</c:if></td>
					<td>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="do_select_user">
							<input type="hidden" name="id" value="${user.id}">
							<input type="hidden" name="id_news" value="${requestScope.news.id}">
							<div class="row">
								<div class="col">
									<input type="submit" class="btn btn-dark btn-outline-light"
										value="${select}">
								</div>
							</div>
						</form>
					</td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
</div>


