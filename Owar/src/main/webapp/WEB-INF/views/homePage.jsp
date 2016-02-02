<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Home Page</title>
		<jsp:include page="headComponent.jsp" />
	</head>
	<body>
		<c:if test="${isValidated != null && isValidated == true}">
			<input type="hidden" id="isValidated">
		</c:if>
		<div class="wrap">
			<jsp:include page="hiddenComponent.jsp" />
			<jsp:include page="menuComponent.jsp" />
			<div class="container">
				<jsp:include page="carouselComponent.jsp" />
			</div>
		</div>
	    <jsp:include page="footerComponent.jsp" />
	</body>
</html>