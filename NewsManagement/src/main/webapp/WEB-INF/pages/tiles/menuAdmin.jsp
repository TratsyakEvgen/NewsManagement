<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.users" var="users" />



<ul class="nav flex-column">

	<li><a href="controller?command=go_to_list_users">${users}</a>
		<hr class="my-3"></li>
		<li><a href="controller?command=go_to_file_system">fileSystem</a>
		<hr class="my-3"></li>

	
	<li><a class="text-decoration-none text-reset" href="controller?command=go_to_base_page">${back}</a>
</ul>