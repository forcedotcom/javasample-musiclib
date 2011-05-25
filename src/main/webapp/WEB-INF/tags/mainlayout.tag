<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="breadcrumb" required="true" rtexprvalue="true"%>
<%@ attribute name="hidesearch" required="false" rtexprvalue="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>VMforce Music Library</title>
	<link href="${pageContext.request.contextPath}/resources/layout.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="header"><div id="app_title">Music Library</div></div>
	<form action="" method="get">
		<div id="navigation">${breadcrumb}
			<c:if test="${hidesearch==null}">
				<div style="float:right; white-space: nowrap">
					 JPQL Where caluse i.e. <code>o.name='a'</code><input size="40" type="text" name="q" value="${param.q}"/>
					<input type="submit" value="Go"/>
				</div>
			</c:if>
		</div>
	</form>
	<div id="content">
	
		<jsp:doBody/>
	
	</div>
	<div id="footer"></div>
</body>
</html>
