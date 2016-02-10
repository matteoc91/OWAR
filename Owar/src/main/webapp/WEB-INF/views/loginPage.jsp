<html>
	<head>
		<title>Login page</title>
		<jsp:include page="headComponent.jsp" />
	</head>
	<body>
		<div class="wrap">
			<jsp:include page="hiddenComponent.jsp" />
			<jsp:include page="menuComponent.jsp" />
			<div class="container">
				<h1 class="text-primary">Login Page</h1>
				<div id="errorMsg" class="alert alert-danger hiddenMsg"></div>
				<div id="successMsg" class="alert alert-success hiddenMsg"></div>
				<div class="row">
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="text-info" for="userMail">Email</label>
									<input type="text" id="userMail" class="userInput form-control">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="text-info" for="userPassword">Password</label>
									<input type="password" id="userPassword" class="userInput form-control">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<button type="button" class="btn btn-primary" id="resetBtn">Reset</button>
								<button type="button" class="btn btn-primary" id="loginBtn">Login</button>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<img class="img-responsive" src="${pageContext.request.contextPath}/resources/img/login.jpg">
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footerComponent.jsp" />
	</body>
</html>