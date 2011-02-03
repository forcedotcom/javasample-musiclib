<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<tags:mainlayout>

	<jsp:attribute name="breadcrumb"><a href="..">Home</a> / <a href=".">Albums</a> / ${album.name}</jsp:attribute>
	<jsp:body>
		<form:form commandName="album">
		<table>
			<tr>
				<td>Album:</td><td><form:input size="40" path="name"/></td>
			</tr>
			<tr>
				<td>Artist:</td><td><form:input size="40" path="artist.name"/></td>
			</tr>
			<tr>
				<td>Genre:</td><td><form:input size="40" path="genre.name"/></td>
			</tr>
			<tr>
				<td>Release Date:</td><td><form:input size="40" path="releaseDate"/></td>
			</tr>
		</table>
		<p/>
		<input type="submit" value="Save"/>
		<input type="button" value="Album List" onclick="location.href='.'"/>
		</form:form>
	</jsp:body>
	
</tags:mainlayout>
