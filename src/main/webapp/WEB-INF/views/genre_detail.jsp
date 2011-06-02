<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<tags:mainlayout>

	<jsp:attribute name="breadcrumb"><a href="..">Home</a> / <a href=".">Genres</a> / ${genre.name}</jsp:attribute>
	<jsp:body>
		<form:form commandName="genre">
		<table>
            <tr>
                <td>Name:</td><td><form:input size="40" path="name"/></td>
            </tr>
            <tr>
                <td>Freebase ID:</td><td><form:input size="40" path="freebaseId"/></td>
            </tr>
		</table>
		<p/>
		<input type="submit" value="Save"/>
		<input type="button" value="Genre List" onclick="location.href='.'"/>
		</form:form>
	</jsp:body>
	
</tags:mainlayout>
