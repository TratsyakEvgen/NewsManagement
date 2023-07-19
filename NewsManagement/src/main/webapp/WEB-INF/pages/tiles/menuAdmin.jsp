<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.users" var="users" />
<fmt:message bundle="${loc}" key="local.file.system.images" var="file_system_images" />
<fmt:message bundle="${loc}" key="local.file.system.documents" var="file_system_documents" />


<ul class="nav flex-column">

	<li><a class="text-decoration-none text-reset" href="controller?command=go_to_list_users">${users}</a>
		<hr class="my-3"></li>
	<li><a class="text-decoration-none text-reset" href="controller?command=go_to_file_system&dir=html">${file_system_images}</a>
		<hr class="my-3"></li>
	<li><a class="text-decoration-none text-reset" href="controller?command=go_to_file_system&dir=images">${file_system_documents}</a>
	</li>


	<li><a class="text-decoration-none text-reset"
		href="controller?command=go_to_base_page">${back}</a>
</ul>