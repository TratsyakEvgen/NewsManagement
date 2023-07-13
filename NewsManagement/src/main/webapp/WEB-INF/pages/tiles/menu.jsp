<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${requestScope.menu_presentation == 'news_list'}">
	<c:import url="/WEB-INF/pages/tiles/newsList.jsp" />
</c:if>

