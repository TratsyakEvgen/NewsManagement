<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${sessionScope.main_presentation == 'view_news' }">
	<c:import url="/WEB-INF/pages/tiles/viewNews.jsp" />
</c:if>