<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.news.title" var="news_title" />
<fmt:message bundle="${loc}" key="local.news.date" var="news_date" />
<fmt:message bundle="${loc}" key="local.news.author" var="author" />
<fmt:message bundle="${loc}" key="local.back" var="back" />
<fmt:message bundle="${loc}" key="local.update" var="update" />

<c:set var="news" value="${requestScope.news}"></c:set>
<c:set var="localContent" value="${news.listLocalContentNews.get(0)}"></c:set>
<c:set var="user" value="${sessionScope.user}"></c:set>

<div class="row">
	<div class="d-flex flex-row  mb-3">
		<c:if test="${user.role == 'admin'}">
			<a class="btn btn-dark btn-outline-light"
				href="controller?command=go_to_update_news&id=${news.id}">${update}</a>
		</c:if>

		<a href="controller?command=go_to_base_page"
			class="btn btn-dark btn-outline-light">${back}</a>
	</div>
	<div class="col">
		<div class="row">
			<div class="col-2">
				<h5>${news_title}</h5>
			</div>
			<div class="col-10">
				<h5>${localContent.title}</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<h5>${news_date}</h5>
			</div>
			<div class="col-10">
				<h5>
					<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
						value="${news.newsDate}" />
				</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<h5>${author}</h5>
			</div>
			<div class="col-10">
				<c:set var="author" value="${news.author}"></c:set>
				<h5>${author.surname} ${author.name}</h5>
			</div>
		</div>
	</div>
</div>


<c:set var="images" value="${requestScope.news.images}"></c:set>
<c:if test="${!images.isEmpty()}">
	<div class="row justify-content-center">
		<div class="col-md-10 col-lg-8">
			<div id="carousel" class="carousel slide carousel-fade">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="${images.get(0).link}" class="d-block w-100" style="height: 500px;">
					</div>
					<c:if test="${images.size() > 1}">
						<c:forEach var="image" items="${images}" begin="1">
							<div class="carousel-item">
								<img src="${image.link}" class="d-block w-100" style="height: 500px;">
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

<hr class="my-3">

<div class="row">
	<div class="col">
		<iframe src="${localContent.link}" width="100%" frameborder=0
			scrolling=no
			onload="this.style.height=this.contentDocument.body.scrollHeight + 100 +'px';">
		</iframe>
	</div>
</div>