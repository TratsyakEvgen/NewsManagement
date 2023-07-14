<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />


<c:forEach var="news" items="${requestScope.news_list}">
	<a class="text-decoration-none text-reset"
		<c:if test="${sessionScope.user.role == 'user' or sessionScope.user.role == 'admin'}">
					<c:out value="href=controller?command=go_to_view_news&id=${news.id}" />
				</c:if>>
		<c:out value="${news.title}" />
	</a>
	<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
		value="${news.newsDate}" />
	<hr class="my-3">
</c:forEach>
