<html>
	<head>
		<title>Registration page</title>
		<jsp:include page="headComponent.jsp" />
	</head>
	<body>
		<div class="wrap">
			<jsp:include page="hiddenComponent.jsp" />
			<jsp:include page="menuComponent.jsp" />
			<div class="container">
				<h1 class="text-primary">Registration Page</h1>
				<div id="errorMsg" class="alert alert-danger hiddenMsg"></div>
				<div id="successMsg" class="alert alert-success hiddenMsg"></div>
				<div class="row">
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="text-info" for="userMail">Email (*)</label>
									<input type="text" id="userMail" class="userInput form-control">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="text-info" for="userPassword">Password (*)</label>
									<input type="password" id="userPassword" class="userInput form-control">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="text-info" for="userConfirmPassword">Conferma Password (*)</label>
									<input type="password" id="userConfirmPassword" class="userInput form-control">
								</div>
							</div>
						</div>
						<div class="row moreInfo">
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userName">Name</label>
											<input type="text" id="userName" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userSurname">Surname</label>
											<input type="text" id="userSurname" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userBirthDate">Birth Date</label>
											<input type="date" id="userBirthDate" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userDistrict">District</label>
											<select id="userDistrict" class="userInput form-control">
												<option></option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userAddress">Address</label>
											<input type="text" id="userAddress" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userHouseNumber">House Number</label>
											<input type="number" min="1" id="userHouseNumber" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userTaxCode">Tax Code</label>
											<input type="text" id="userTaxCode" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userPhoneNumber">Phone Number</label>
											<input type="text" id="userPhoneNumber" class="userInput form-control">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<button id="toggleMoreInfo" class="btn btn-default">
									<span class="glyphicon glyphicon-menu-down"></span>
									More info
								</button>
								<button type="button" class="btn btn-primary" id="resetBtn">Reset</button>
								<button type="button" class="btn btn-primary" id="registrationBtn">Register</button>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<img class="img-responsive" src="${pageContext.request.contextPath}/resources/img/registration.jpg">
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footerComponent.jsp" />
	</body>
</html>