<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<tags:mainlayout>

	<jsp:attribute name="breadcrumb"><a href="..">Home</a> / Artists</jsp:attribute>
	
	<jsp:body>
		<table class="itemlist" width="100%">
			<tr>
				<th>Name</th><th>Origin</th><th>Career Start</th><th>Career End</th>
			</tr>
			<c:forEach items="${list}" var="artist">
				<tr>
					<td><a href="${artist.id}">${artist.name}</a></td>
					<td>${artist.origin}</td>
					<td><span class="nobr"><fmt:formatDate pattern="yyyy-MM-dd" value="${artist.activeStart}"/></span></td>
					<td><span class="nobr"><fmt:formatDate pattern="yyyy-MM-dd" value="${artist.activeEnd}"/></span></td>
				</tr>
			</c:forEach>
		</table>
		<p/>
		<form action="" method="post">
			<input type="button" name="New Artist" value="New Artist" onclick="location.href='new'"/>
		</form>
	</jsp:body>

</tags:mainlayout>