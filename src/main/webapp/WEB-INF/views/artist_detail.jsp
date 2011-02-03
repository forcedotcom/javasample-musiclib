<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<tags:mainlayout>

	<jsp:attribute name="breadcrumb"><a href="..">Home</a> / <a href=".">Artists</a> / ${artist.name}</jsp:attribute>
	<jsp:body>
		<form:form commandName="artist">
		<table>
			<tr>
				<td>Name:</td><td><form:input size="40" path="name"/></td>
			</tr>
			<tr>
				<td>Origin:</td><td><form:input size="40" path="origin"/></td>
			</tr>
			<tr>
				<td>Active Start:</td><td><form:input size="40" path="activeStart"/></td>
			</tr>
			<tr>
				<td>Active End:</td><td><form:input size="40" path="activeEnd"/></td>
			</tr>
		</table>
		<p/>
		<input type="submit" value="Save"/>
		<input type="button" value="Album List" onclick="location.href='.'"/>
		</form:form>
	</jsp:body>
	
</tags:mainlayout>
