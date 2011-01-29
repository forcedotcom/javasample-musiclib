<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<tags:mainlayout>

	<jsp:attribute name="breadcrumb"><a href="..">Home</a> / Genre</jsp:attribute>
	
	<jsp:body>
		<table class="itemlist" width="100%">
			<tr>
				<th>Freebase ID</th><th>Name</th>
			</tr>
			<c:forEach items="${list}" var="genre">
				<tr>
					<td><a href="http://www.freebase.com/view/${genre.freebaseId}">${genre.freebaseId}</a></td>
					<td>${genre.name}</td>
				</tr>
			</c:forEach>
		</table>
		<p/>
		<form action="" method="post">
			<input type="button" name="New Genre" value="New Genre" onclick="location.href='new'"/>
		</form>
	</jsp:body>
</tags:mainlayout>

