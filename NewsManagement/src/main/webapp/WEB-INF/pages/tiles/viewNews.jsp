<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.news" var="news" />
<fmt:message bundle="${loc}" key="local.view.news" var="view_news" />
<fmt:message bundle="${loc}" key="local.news.title" var="news_title" />
<fmt:message bundle="${loc}" key="local.news.date" var="news_date" />
<fmt:message bundle="${loc}" key="local.brief" var="brief" />



<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${news_title}</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.title }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${news_date}</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${requestScope.news.newsDate}" />
				</div></td>
		</tr>
	</table>

	<iframe
		src="${requestScope.news.link}"
		width="100%"  frameborder=0 scrolling=no
		onload="this.style.height=this.contentDocument.body.scrollHeight +'px';">
	</iframe>


</div>



<c:if test="${sessionScope.user.role eq 'admin'}">
	<div class="first-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="edit" /> <input
				type="hidden" name="id" value="${requestScope.news.id}" /> <input
				type="submit" value="Edit" />
		</form>
	</div>

	<div class="second-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="delete" /> <input
				type="hidden" name="id" value="${requestScope.news.id}" /> <input
				type="submit" value="Delete" />
		</form>
	</div>
</c:if>