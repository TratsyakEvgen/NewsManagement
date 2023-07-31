<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.lastet.news" var="lastet_news" />
<fmt:message bundle="${loc}" key="local.news.not.found"
	var="news_not_found" />




<div class="row text-center">
	<h1>${lastet_news}</h1>
</div>

<c:set var="news_list" value="${requestScope.news_list}"></c:set>
<div class="row justify-content-center">
	<div class="col-10">
		<c:if test="${news_list != null}">
			<div id="carousel" class="carousel slide carousel-fade"
				data-bs-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<p>
							<a							
								<c:set var="localContent" value="${news_list.get(0).listLocalContentNews.get(0)}"/>
								href="controller?command=go_to_view_news&id=${localContent.id}">
								<c:set var="listImages" value="${news_list.get(0).images}"/>
								<c:if test="${listImages.isEmpty()}">
									<img src="images/gray.png" class="d-block w-100" style="height: 500px;">
								</c:if> <c:if test="${!listImages.isEmpty()}">
									<img src="${listImages.get(0).link}" class="d-block w-100" style="height: 500px;">
								</c:if>
							</a>
						</p>
						<div class="carousel-caption d-noned md-block ">
							<h1>${localContent.title}</h1>
						</div>

					</div>

					<c:if test="${news_list.size() > 0}">
						<c:forEach var="news" items="${news_list}" begin="1">
							<div class="carousel-item">
								<p>
									<a
										<c:set var="localContent" value="${news.listLocalContentNews.get(0)}"/>
										href="controller?command=go_to_view_news&id=${localContent.id}">
										<c:set var="listImages" value="${news.images}"/> <c:if
											test="${listImages.isEmpty()}">
											<img src="images/gray.png" class="d-block w-100" style="height: 500px;">
										</c:if> <c:if test="${!listImages.isEmpty()}">
											<img src="${listImages.get(0).link}" class="d-block w-100" style="height: 500px;">
										</c:if>
									</a>
								</p>
								<div class="carousel-caption d-noned md-block ">
									<h1>${localContent.title}</h1>
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
		</c:if>
		<c:if test="${news_list == null}">${news_not_found}</c:if>
	</div>
</div>


