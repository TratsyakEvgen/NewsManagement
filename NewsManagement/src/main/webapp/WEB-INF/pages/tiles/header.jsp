<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.language" var="language" />
<fmt:message bundle="${loc}" key="local.lang" var="lang" />
<fmt:message bundle="${loc}" key="local.ru" var="ru" />
<fmt:message bundle="${loc}" key="local.en" var="en" />
<fmt:message bundle="${loc}" key="local.sign.in" var="sign_in" />
<fmt:message bundle="${loc}" key="local.sign.out" var="sign_out" />
<fmt:message bundle="${loc}" key="local.news.management"
	var="news_management" />
<fmt:message bundle="${loc}" key="local.find" var="find" />





<header class="navbar bg-dark sticky-top navbar-expand-md " data-bs-theme="dark">
	<div class="container-fluid">
		
		<a class="navbar-brand mb-2" href="controller?command=go_to_base_page">${news_management}</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false">
			<span class="dropdown-toggle"></span>
		</button>
		
		<button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
			data-bs-target="#sidebarMenu"
			aria-controls="sidebarMenu" aria-expanded="false">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto">
				<li class="nav-item mb-2"><a class="nav-link active">${language}</a>
				</li>
				<li class="nav-item dropdown mb-2">
					<button class="btn btn-dark btn-outline-light dropdown-toggle"
						type="button" data-bs-toggle="dropdown" aria-expanded="false">${lang}</button>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item"
							href="controller?command=change_local&local=en">${en}</a></li>
						<li><a class="dropdown-item"
							href="controller?command=change_local&local=ru">${ru}</a></li>
					</ul>
				</li>
			</ul>
			
			
			<c:set var= "user" value="${sessionScope.user}"></c:set>

			<c:if test="${user == null}">
				<a href="controller?command=go_to_authentication"
					class="btn btn-dark btn-outline-light mb-2 me-2">${sign_in}</a>
			</c:if>

			<c:if test="${user != null}">
				<form class="d-flex mb-2 me-2" role="search">
					<input class="form-control me-2" type="search" placeholder="${find}">
					<button class="btn btn-dark btn-outline-light"
						type="submit">${find}</button>
				</form>
				<a href="controller?command=do_sign_out"
					class="btn btn-dark btn-outline-light mb-2 me-2">${user.surname} ${user.name}</a>
				<a href="controller?command=do_sign_out"
					class="btn btn-dark btn-outline-light mb-2 me-2">${sign_out}</a>
			</c:if>

		</div>
	</div>
</header>





