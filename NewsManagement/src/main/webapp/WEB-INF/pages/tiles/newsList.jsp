<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.news.not.found"
	var="news_not_found" />

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<c:if test="${news_list != null}">
	<c:forEach var="news" items="${requestScope.news_list}">
		<c:set var="localContentNews"
			value="${news.listLocalContentNews.get(0)}"></c:set>
		<a class="text-decoration-none text-reset"
			<c:if test="${sessionScope.user.role == 'user' or sessionScope.user.role == 'admin'}">
					<c:out value="href=controller?command=go_to_view_news&id=${localContentNews.id}" />
				</c:if>>
			<c:out value="${localContentNews.title}" />
		</a>
		<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
			value="${news.newsDate}" />
		<hr class="my-3">
	</c:forEach>
</c:if>
<c:if test="${news_list == null}">${news_not_found}</c:if>
