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
<fmt:message bundle="${loc}" key="local.update" var="update" />
<fmt:message bundle="${loc}" key="local.id" var="id" />
<fmt:message bundle="${loc}" key="local.role" var="role" />
<fmt:message bundle="${loc}" key="local.select" var="select" />
<fmt:message bundle="${loc}" key="local.user" var="user_local" />
<fmt:message bundle="${loc}" key="local.admin" var="admin" />
<fmt:message bundle="${loc}" key="local.deleted" var="deleted" />

<form action="controller" method="post">
	<input type="hidden" name="command" value="do_update_role">

	<div class="row">
		<div class="col">
			<input type="submit" class="btn btn-dark btn-outline-light"
				value="add">
		</div>
	</div>
</form>
<div class="row table-responsive">
	<table class="table table-hover">
		<tbody>
			<c:forEach var="file" items="${requestScope.files}">
				<tr>
					<td scope="row"><a href="${file}">${file}</a></td>
					<td>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="do_update_role">
							<input type="submit" class="btn btn-dark btn-outline-light"
								value="update">

						</form>
					</td>
					<td>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="do_update_role">
							<input type="submit" class="btn btn-dark btn-outline-light"
								value="delete">
						</form>
					</td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
</div>


