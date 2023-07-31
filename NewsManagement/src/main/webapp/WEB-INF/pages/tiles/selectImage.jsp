<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.id" var="id" />
<fmt:message bundle="${loc}" key="local.link" var="link" />
<fmt:message bundle="${loc}" key="local.status" var="status" />
<fmt:message bundle="${loc}" key="local.active" var="active" />
<fmt:message bundle="${loc}" key="local.deleted" var="deleted" />
<fmt:message bundle="${loc}" key="local.select" var="select" />



<div class="row table-responsive">
	<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th scope="col"></th>
				<th scope="col">${id}</th>
				<th scope="col">${link}</th>
				<th scope="col">${status}</th>
				<th scope="col">${select}</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="image" items="${requestScope.image_list}">
				<tr>
					<td scope="row"><img src="${image.link}" class="img-fluid"></td>
					<td>${image.id}</td>
					<td>${image.link}</td>
					<td><c:if test="${image.status}">${active}</c:if> <c:if
							test="${!image.status}">${deleted}</c:if></td>
					<td>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="do_select_image">
							<input type="hidden" name="id" value="${image.id}"> <input
								type="hidden" name="id_news" value="${requestScope.news.id}">
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


