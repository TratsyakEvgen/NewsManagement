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

<c:if test="${requestScope.main_presentation == 'user_list'}">
	<c:import url="/WEB-INF/pages/tiles/userList.jsp" />
</c:if>

<c:if test="${requestScope.main_presentation == 'view_files'}">
	<c:import url="/WEB-INF/pages/tiles/viewFiles.jsp" />
</c:if>

<c:if test="${requestScope.main_presentation == 'image_list'}">
	<c:import url="/WEB-INF/pages/tiles/viewGallery.jsp" />
</c:if>

<c:if test="${requestScope.main_presentation == 'news_list'}">
	<c:import url="/WEB-INF/pages/tiles/newsManagementList.jsp" />
</c:if>

<c:if test="${requestScope.main_presentation == 'update_news'}">
	<c:import url="/WEB-INF/pages/tiles/updateNews.jsp" />
</c:if>