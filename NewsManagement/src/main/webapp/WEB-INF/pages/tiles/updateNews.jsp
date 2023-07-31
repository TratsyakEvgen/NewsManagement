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
<fmt:message bundle="${loc}" key="local.delete" var="delete" />
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
<fmt:message bundle="${loc}" key="local.update" var="update" />
<fmt:message bundle="${loc}" key="local.save" var="save" />

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
						<form action="controller" method="post">
							<input type="hidden" name="command"
								value="do_delete_image_in_news"> <input type="hidden"
								name="id" value="${image.id}"> <input type="hidden"
								name="id_news" value="${news.id}">
							<div class="col">
								<input type="submit" class="btn btn-dark btn-outline-light"
									value="${delete}">
							</div>
						</form>
						<hr class="my-3">
					</c:forEach>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="go_to_select_image">
						<input type="hidden" name="id" value="${news.id}">
						<div class="col">
							<input type="submit" class="btn btn-dark btn-outline-light"
								value="${add}">
						</div>
					</form></td>
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
				<td><c:set var="marker" value="${requestScope.marker}" /> <c:forEach
						var="localContent" items="${news.listLocalContentNews}">
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
						<div class="d-flex flex-row  mb-3">
							<button class="btn btn-dark btn-outline-light" type="button"
								data-bs-toggle="collapse" data-bs-target="#${localContent.id}">
								${update}</button>


							<form action="controller" method="post">
								<input type="hidden" name="command" value="do_delete_content">
								<input type="hidden" name="id" value="${localContent.id}">
								<input type="hidden" name="id_news" value="${news.id}">
								<input type="submit" class="btn btn-dark btn-outline-light"
									value="${delete}">

							</form>
						</div>
						<div
							class="collapse <c:if test="${marker == localContent.id}">show</c:if>"
							id="${localContent.id}">
							<form action="controller" method="post">
								<input type="hidden" name="command" value="do_update_content">
								<input type="hidden" name="id" value="${localContent.id}">
								<input type="hidden" name="id_news" value="${news.id}">
								<label class="form-label">${language}:</label> <select
									class="form-select" name="local">
									<option value="ru"
										<c:if test="${(marker != localContent.id) && (localContent.local == 'ru')}">selected</c:if>
										<c:if test="${(marker == localContent.id) && (requestScope.local == 'ru')}">selected</c:if>>${ru}</option>
									<option value="en"
										<c:if test="${(marker != localContent.id) && (localContent.local == 'en')}">selected</c:if>
										<c:if test="${(marker == localContent.id) && (requestScope.local == 'en')}">selected</c:if>>${en}</option>
								</select> <label class="form-label">${news_title}:</label> <input
									type="text" name="title" class="form-control"
									<c:if test="${marker == localContent.id}">value="${requestScope.title}"</c:if>
									<c:if test="${marker != localContent.id}">value="${localContent.title}"</c:if>>
								<label class="form-label">${link}:</label> <input type="text"
									name="link" class="form-control"
									<c:if test="${marker == localContent.id}">value="${requestScope.link}"</c:if>
									<c:if test="${marker != localContent.id}">value="${localContent.link}"</c:if>>
								<input type="submit" class="btn btn-dark btn-outline-light"
									value="${save}">

							</form>
						</div>
						<hr class="my-3">
					</c:forEach>

					<button class="btn btn-dark btn-outline-light" type="button"
						data-bs-toggle="collapse" data-bs-target="#0">${add}</button>
					<div class="collapse <c:if test="${marker == 0}">show</c:if>"
						id="0">
						<form action="controller" method="post">
							<input type="hidden" name="command" value="do_add_content">
							<input type="hidden" name="id_news" value="${news.id}"> <label
								class="form-label">${language}:</label> <select
								class="form-select" name="local">
								<option value="ru"
									<c:if test="${(marker == 0) && (requestScope.local == 'ru')}">selected</c:if>>${ru}</option>
								<option value="en"
									<c:if test="${(marker == 0) && (requestScope.local == 'en')}">selected</c:if>>${en}</option>
							</select> <label class="form-label">${news_title}:</label> <input
								type="text" name="title" class="form-control"
								<c:if test="${marker == 0}">value="${requestScope.title}"</c:if>>
							<label class="form-label">${link}:</label> <input type="text"
								name="link" class="form-control"
								<c:if test="${marker == 0}">value="${requestScope.link}"</c:if>>
							<input type="submit" class="btn btn-dark btn-outline-light"
								value="${save}">

						</form>
					</div></td>
			</tr>
		</tbody>
	</table>
</div>

<c:if test="${requestScope.select == 'user'}">
	<c:import url="/WEB-INF/pages/tiles/selectUser.jsp" />
</c:if>
<c:if test="${requestScope.select == 'image_list'}">
	<c:import url="/WEB-INF/pages/tiles/selectImage.jsp" />
</c:if>



