<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.lastet.news" var="lastet_news" />





<div class="row text-center">
	<h1>${lastet_news}</h1>
</div>

<c:set var="news_list" value="${requestScope.news_list}"></c:set>
<c:if test="${!news_list.isEmpty()}">
	<div class="row justify-content-center">
		<div class="col-10">
			<div id="carousel" class="carousel slide carousel-fade"
				data-bs-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<c:set var="image" value="${news_list.get(0).images.get(0).link}"></c:set>
						<p>
							<a
								href="controller?command=go_to_view_news&id=${news_list.get(0).id}">
								<c:if test="${image == 'null'}">
									<svg
										class="bd-placeholder-img bd-placeholder-img-lg d-block w-100"
										width="800" height="400">
								<rect width="100%" height="100%" fill="#777"></rect>
								</svg>
								</c:if> <c:if test="${image != 'null'}">
									<img src="${image}" class="d-block w-100">
								</c:if>
							</a>
						</p>
						<div class="carousel-caption d-noned md-block ">
							<h1>${news_list.get(0).title}</h1>
						</div>

					</div>

					<c:if test="${news_list.size() > 0}">
						<c:forEach var="news" items="${news_list}" begin="1">
							<div class="carousel-item">
								<c:set var="image" value="${news.images.get(0).link}"></c:set>
								<p>
									<a href="controller?command=go_to_view_news&id=${news.id}">
										<c:if test="${image == 'null'}">
											<svg
												class="bd-placeholder-img bd-placeholder-img-lg d-block w-100"
												width="800" height="400">
												<rect width="100%" height="100%" fill="#777"></rect>
								</svg>
										</c:if> <c:if test="${image != 'null'}">
											<img src="${image}" class="d-block w-100">
										</c:if>
									</a>
								</p>
								<div class="carousel-caption d-none d-md-block">
									<h1>${news.title}</h1>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>

				<button class="carousel-control-prev" type="button"
					data-bs-target="#carousel" data-bs-slide="prev">
					<span class="carousel-control-prev-icon"></span>

				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carousel" data-bs-slide="next">
					<span class="carousel-control-next-icon"></span>

				</button>
			</div>
		</div>
	</div>
</c:if>