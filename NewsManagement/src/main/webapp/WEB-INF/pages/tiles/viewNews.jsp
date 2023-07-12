<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.news.title" var="news_title" />
<fmt:message bundle="${loc}" key="local.news.date" var="news_date" />
<fmt:message bundle="${loc}" key="local.news.author" var="author" />

<div class="row">
	<div class="col">
		<div class="row">
			<div class="col-2">
				<h5>${news_title}</h5>
			</div>
			<div class="col-10">
				<h5>${requestScope.news.title}</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<h5>${news_date}</h5>
			</div>
			<div class="col-10">
			<h5><fmt:formatDate type="both" dateStyle="long" timeStyle="long"
					value="${requestScope.news.newsDate}" /></h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<h5>${author}</h5>
			</div>
			<div class="col-10">
				<h5>${requestScope.news.author.surname}
					${requestScope.news.author.name}</h5>
			</div>
		</div>
	</div>
</div>



<c:if test="${!requestScope.news.images.isEmpty()}">
	<div class="row justify-content-center">
		<div class="col-md-10 col-lg-8">
			<div id="carouselExample" class="carousel slide carousel-fade">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="${requestScope.news.images.get(0).link}"
							class="d-block w-100">
					</div>
					<c:if test="${requestScope.news.images.size() > 1}">
						<c:forEach var="image" items="${requestScope.news.images}"
							begin="1">

							<div class="carousel-item">
								<img src="${image.link}" class="d-block w-100">
							</div>
						</c:forEach>
					</c:if>
				</div>

				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExample" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExample" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">next</span>
				</button>
			</div>
		</div>
	</div>
</c:if>

<div class="row">
	<div class="col">
		<iframe src="${requestScope.news.link}" width="100%" frameborder=0
			scrolling=no
			onload="this.style.height=this.contentDocument.body.scrollHeight + 100 +'px';">
		</iframe>
	</div>
</div>