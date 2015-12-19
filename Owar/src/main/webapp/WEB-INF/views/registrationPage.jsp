<html>
	<head>
		<title>Registration page</title>
		<jsp:include page="headComponent.jsp" />
	</head>
	<body>
		<jsp:include page="hiddenComponent.jsp" />
		<jsp:include page="menuComponent.jsp" />
		<div class="container">
			<h1 class="text-primary">Registration Page</h1>
			<div id="errorMsg" class="alert alert-danger hiddenMsg"></div>
			<div id="successMsg" class="alert alert-success hiddenMsg"></div>
			<div class="row">
				<div class="col-sm-4">
					<span class="text-info">Email (*)</span>
				</div>
				<div class="col-sm-4">
					<input type="text" id="userMail" class="userInput">
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<span class="text-info">Password (*)</span>
				</div>
				<div class="col-md-4">
					<input type="password" id="userPassword" class="userInput">
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<span class="text-info">Conferma Password (*)</span>
				</div>
				<div class="col-md-4">
					<input type="password" id="userConfirmPassword" class="userInput">
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<button type="button" class="btn btn-primary" id="resetBtn">Reset</button>
					<button type="button" class="btn btn-primary" id="registrationBtn">Register</button>
				</div>
				<div class="col-md-6"></div>
			</div>
		</div>
	</body>
</html>