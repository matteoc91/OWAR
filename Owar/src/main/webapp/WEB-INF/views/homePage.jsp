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
				<div class="jumbotron">
					<h2 class="text-center text-capitalize">
						<strong>
							are you looking for a place to start your business?
						</strong>
					</h2>
					<p>
						<blockquote class="blockquote-reverse">
							<p>
								Start where you are. Use what you have. Do what you can.
							</p>
							<footer>Arthur Ashe</footer>
						</blockquote>
						<blockquote class="blockquote">
							<p>
								Everybody has to start somewhere. You have your whole future ahead of you. Perfection doesn't happen right away.
							</p>
							<footer>Haruki Murakami</footer>
						</blockquote>
						<blockquote class="blockquote">
							<p>
								If I must start somewhere, right here and now is the best place imaginable.
							</p>
							<footer>Richelle E. Goodrich</footer>
						</blockquote>
						<blockquote class="blockquote">
							<p>
								Your present circumstances don't determine where you can go; they merely determine where you start.
							</p>
							<footer>Nido Qubein</footer>
						</blockquote>
					</p>
				</div>
			</div>
		</div>
	    <jsp:include page="footerComponent.jsp" />
	</body>
</html>