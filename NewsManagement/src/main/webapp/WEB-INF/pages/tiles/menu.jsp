<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${requestScope.menu_presentation == 'news_list'}">
	<c:import url="/WEB-INF/pages/tiles/newsList.jsp" />
</c:if>

<c:if test="${requestScope.menu_presentation == 'view_account'}">
	<c:import url="/WEB-INF/pages/tiles/menuAccount.jsp" />
</c:if>

<c:if test="${requestScope.menu_presentation == 'view_admin'}">
	<c:import url="/WEB-INF/pages/tiles/menuAdmin.jsp" />
</c:if>

