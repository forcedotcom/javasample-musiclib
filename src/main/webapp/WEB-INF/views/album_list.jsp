<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<tags:mainlayout>

	<jsp:attribute name="breadcrumb"><a href="..">Home</a> / Albums</jsp:attribute>
	
	<jsp:body>
		<table class="itemlist" width="100%">
			<tr>
				<th>Name</th><th>Artist</th><th>Genre</th><th>Release Date</th>
			</tr>
			<c:forEach items="${list}" var="album">
				<tr>
					<td><a href="${album.id}">${album.name}</a></td>
					<td><a href="../artist/${album.artist.id}">${album.artist.name}</a></td>
					<td>${album.genre.name}</td>
					<td><span class="nobr"><fmt:formatDate pattern="yyyy-MM-dd" value="${album.releaseDate}"/></span></td>
				</tr>
			</c:forEach>
		</table>
		<p/>
		<form action="" method="post">
			<input type="button" name="New Album" value="New Album" onclick="location.href='new'"/>
		</form>
	</jsp:body>
</tags:mainlayout>

