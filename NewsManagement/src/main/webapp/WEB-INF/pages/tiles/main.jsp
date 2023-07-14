<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${requestScope.main_presentation == 'view_news' }">
	<c:import url="/WEB-INF/pages/tiles/viewNews.jsp" />
</c:if>

<c:if test="${requestScope.main_presentation == 'start_page' }">
	<c:if test="${sessionScope.user.role != null }">
		<c:import url="/WEB-INF/pages/tiles/startPage.jsp" />
	</c:if>
	<c:if test="${sessionScope.user.role == null }">
		<c:import url="/WEB-INF/pages/tiles/aboutUs.jsp" />
	</c:if>
</c:if>

<c:if test="${requestScope.main_presentation == 'view_account'}">
	<c:import url="/WEB-INF/pages/tiles/account.jsp" />
</c:if>