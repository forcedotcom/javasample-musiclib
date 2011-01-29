<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page session="false" %>

<tags:mainlayout>

<jsp:attribute name="breadcrumb">Home</jsp:attribute>
<jsp:attribute name="hidesearch">true</jsp:attribute>

<jsp:body>
<p><a href="album/">Albums</a></p>

<p><a href="artist/">Artists</a></p>

<p><a href="genre/">Genres</a></p>

</jsp:body>
</tags:mainlayout>
