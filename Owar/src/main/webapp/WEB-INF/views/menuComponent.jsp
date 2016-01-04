<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-default topMenu">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}">OWAR</a>
		</div>
		<div>
			<ul class="nav navbar-nav navbarToActive">
				<li><a href="${pageContext.request.contextPath}">Home</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right navbarToActive">
				<li>
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<c:if test="${userBean != 'null'}">
							<span class="text-info" id="userSessionHead"></span>
						</c:if>
						<c:if test="${userBean == 'null'}">
							<span class="glyphicon glyphicon-user"></span>
						</c:if>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
					
						<c:if test="${userBean != 'null'}">
							<li><a href="${pageContext.request.contextPath}/profilePage">My Profile</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#" id="logoutBtn">Log Out</a></li>
						</c:if>
						
						<c:if test="${userBean == 'null'}">
							<li><a href="${pageContext.request.contextPath}/loginPage">Log In</a></li>
							<li><a href="${pageContext.request.contextPath}/registrationPage">Sign In</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="${pageContext.request.contextPath}/socialPage">Social</a></li>
						</c:if>
						
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>