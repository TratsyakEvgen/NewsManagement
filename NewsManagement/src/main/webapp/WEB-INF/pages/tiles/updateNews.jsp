<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.id" var="id" />
<fmt:message bundle="${loc}" key="local.news.title" var="news_title" />
<fmt:message bundle="${loc}" key="local.link" var="link" />
<fmt:message bundle="${loc}" key="local.news.date" var="news_date" />
<fmt:message bundle="${loc}" key="local.status" var="status" />
<fmt:message bundle="${loc}" key="local.active" var="active" />
<fmt:message bundle="${loc}" key="local.deleted" var="deleted" />
<fmt:message bundle="${loc}" key="local.images" var="images" />
<fmt:message bundle="${loc}" key="local.language" var="language" />
<fmt:message bundle="${loc}" key="local.ru" var="ru" />
<fmt:message bundle="${loc}" key="local.en" var="en" />
<fmt:message bundle="${loc}" key="local.news.author" var="author" />
<fmt:message bundle="${loc}" key="local.first.name" var="first_name" />
<fmt:message bundle="${loc}" key="local.last.name" var="last_name" />
<fmt:message bundle="${loc}" key="local.email" var="email" />
<fmt:message bundle="${loc}" key="local.registration.date"
	var="registration_date" />
<fmt:message bundle="${loc}" key="local.role" var="role" />
<fmt:message bundle="${loc}" key="local.user" var="user_local" />
<fmt:message bundle="${loc}" key="local.admin" var="admin" />
<fmt:message bundle="${loc}" key="local.add" var="add" />
<fmt:message bundle="${loc}" key="local.select" var="select" />

<c:set var="news" value="${requestScope.news}" />
<div class="row table-responsive">
	<table class="table table-hover table-bordered">
		<tbody>
			<tr>
				<td>${id}</td>
				<td>${news.id}</td>
			</tr>

			<tr>
				<td>${news_date}</td>
				<td><fmt:formatDate type="both" dateStyle="long"
						timeStyle="long" value="${news.newsDate}" /></td>
			</tr>

			<tr>
				<td>${images}</td>
				<td><c:forEach var="image" items="${news.images}">
						<div>${id}:${image.id}</div>
						<div>${link}:
							<a href="${image.link}">${image.link}</a>
						</div>
						<div>${status}:
							<c:if test="${image.status}">${active}</c:if>
							<c:if test="${!image.status}">${deleted}</c:if>
						</div>
						<hr class="my-3">
					</c:forEach></td>
			</tr>

			<tr>
				<td>${author}</td>
				<c:set var="user" value="${requestScope.news.author}" />
				<td>
					<div>${id}:${user.id}</div>
					<div>${first_name}:${user.name}</div>
					<div>${last_name}:${user.surname}</div>
					<div>${email}:${user.email}</div>
					<div>${registration_date}:
						<fmt:formatDate type="date" value="${user.registerDate}" />
					</div>
					<div>${role}:
						<c:if test="${user.role == 'admin'}">${admin}</c:if>
						<c:if test="${user.role == 'user'}">${user_local}</c:if>
						<c:if test="${user.role == 'delete'}">${deleted}</c:if>
					</div>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="go_to_select_user">
						<input type="hidden" name="id" value="${news.id}">
						<div class="col">
							<input type="submit" class="btn btn-dark btn-outline-light"
								value="${select}">
						</div>
					</form>

				</td>
			</tr>

			<tr>
				<td>${content}</td>
				<td><c:forEach var="localContent"
						items="${news.listLocalContentNews}">
						<div>${id}:${localContent.id}</div>

						<div>${language}:

							<c:if test="${localContent.local == 'en'}">${en}</c:if>
							<c:if test="${localContent.local == 'ru'}">${ru}</c:if>

						</div>

						<div>${news_title}:
							<a
								href="href=controller?command=go_to_view_news_as_admin&id=${localContent.id}">
								${localContent.title} </a>
						</div>

						<div>${link}:
							<a href="${localContent.link}">${localContent.link}</a>
						</div>
						<hr class="my-3">
					</c:forEach></td>
			</tr>
		</tbody>
	</table>
</div>

<c:if test="${requestScope.select == 'user'}">
	<c:import url="/WEB-INF/pages/tiles/selectUser.jsp" />
</c:if>



