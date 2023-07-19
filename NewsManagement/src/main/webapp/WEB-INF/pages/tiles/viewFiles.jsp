<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.upload" var="upload" />
<fmt:message bundle="${loc}" key="local.update" var="update" />
<fmt:message bundle="${loc}" key="local.delete" var="delete" />
<fmt:message bundle="${loc}" key="local.file" var="file" />


<div class="row">
	<div class="col">
		<form action="controller" method="post" enctype="multipart/form-data">
			<input type="hidden" name="command" value="do_upload_file"> <input
				type="hidden" name="dir" value="${requestScope.dir}"> <input
				type="file" name="file" /> <input type="submit"
				class="btn btn-dark btn-outline-light" value="${upload}">
		</form>
	</div>
</div>

<div class="row table-responsive">
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">${file}</th>
				<th scope="col">${update}</th>
				<th scope="col">${delete}</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="file" items="${requestScope.files}">
				<tr>
					<th scope="row"><a href="${file}">${file}</a></th>
					<td>
						<form action="controller" method="post"
							enctype="multipart/form-data">
							<input type="hidden" name="command" value="do_update_file">
							<input type="hidden" name="dir" value="${requestScope.dir}">
							<input type="hidden" name="link" value="${file}"> <input
								type="file" name="file" /> <input type="submit"
								class="btn btn-dark btn-outline-light" value="${update}">
						</form>
					</td>
					<td>
						<form action="controller" method="post"
							enctype="multipart/form-data">
							<input type="hidden" name="command" value="do_delete_file">
							<input type="hidden" name="dir" value="${requestScope.dir}">
							<input type="hidden" name="link" value="${file}"> <input
								type="submit" class="btn btn-dark btn-outline-light"
								value="${delete}">
						</form>
					</td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
</div>


