<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${sessionScope.menu_presentation == 'newsList' or sessionScope.menu_presentation == null}">
	<c:import url="/WEB-INF/pages/tiles/newsList.jsp" />
</c:if>

