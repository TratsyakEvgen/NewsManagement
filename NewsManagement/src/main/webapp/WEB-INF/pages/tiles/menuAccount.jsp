<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.info" var="info" />
<fmt:message bundle="${loc}" key="local.update.info" var="update_info" />
<fmt:message bundle="${loc}" key="local.update.password" var="update_password" />
<fmt:message bundle="${loc}" key="local.delete" var="delete" />
<fmt:message bundle="${loc}" key="local.back" var="back" />


<ul class="nav flex-column">

	<li><a class="text-decoration-none text-reset"
		data-bs-toggle="collapse" role="button" data-bs-target="#info">${info}</a>
		<hr class="my-3"></li>

	<li><a class="text-decoration-none text-reset"
		data-bs-toggle="collapse" role="button" data-bs-target="#updateInfo">${update_info}</a>
		<hr class="my-3"></li>

	<li><a class="text-decoration-none text-reset"
		data-bs-toggle="collapse" role="button"
		data-bs-target="#updatePassword">${update_password}</a>
		<hr class="my-3"></li>

	<li><a class="text-decoration-none text-reset"
		data-bs-toggle="collapse" role="button" data-bs-target="#delete">${delete}</a>
		<hr class="my-3"></li>
	<li><a class="text-decoration-none text-reset" href="controller?command=go_to_base_page">${back}</a>
</ul>