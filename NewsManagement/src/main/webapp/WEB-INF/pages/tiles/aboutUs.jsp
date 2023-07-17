<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.news.management"
	var="news_management" />
<fmt:message bundle="${loc}" key="local.about.us" var="about_us" />

<div class="row text-center">
	<h1>${news_management}</h1>
</div>
<div class="row">
	<p>${about_us}</p>
</div>