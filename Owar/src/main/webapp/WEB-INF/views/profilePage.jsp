<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Profile Page</title>
		<jsp:include page="headComponent.jsp" />
	</head>
	<body>
		<div class="wrap">
			<jsp:include page="hiddenComponent.jsp" />
			<jsp:include page="menuComponent.jsp" />
			<div class="container">
				<h1 class="text-primary">Profile Page</h1>
				<div id="errorMsg" class="alert alert-danger hiddenMsg"></div>
				<div id="successMsg" class="alert alert-success hiddenMsg"></div>
				<div class="row">
					<div class="col-md-12">
						<c:if test="${isAdminMode}">
							<button class="btn btn-primary" id="getAdminPrivilege">Admin Privilege</button>
						</c:if>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<c:if test="${checkTenant}">
							<span class="text-success">Your profile is ready to start renting office.</span>
							<div class="row">
								<div class="col-md-12 lessorList"></div>
							</div>
							<div class="row">
								<div class="col-md-12 defaultHide" id="lessorData">
									<span class="text-warning">Complete the following fields to add your own offices.</span>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label class="text-info" for="userPiva">P.IVA</label>
												<input type="text" id="userPiva" class="userInput form-control">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<button type="button" class="btn btn-primary" id="completeProfile">Complete profile</button>
										</div>
									</div>
								</div>
								<div class="col-md-12 defaultHide" id="lessorDataComplete">
									<span class="text-success">Your profile is complete, now you can start creating your own offices.</span>
									<div class="row">
										<div class="col-md-12 tenantList"></div>
									</div>
								</div>
							</div>
						</c:if>
						<div class="row profileDetailContainer defaultHide">
							<div class="col-md-12">
								<span class="text-info">Profile Data</span>
								<div class="row defaultHide">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userName">Name</label>
											<input type="text" id="userName" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row defaultHide">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userSurname">Surname</label>
											<input type="text" id="userSurname" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row defaultHide">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userBirthDate">Birth Date</label>
											<input type="date" id="userBirthDate" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row defaultHide">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userDistrict">District</label>
											<select id="userDistrict" class="userInput form-control">
												<option></option>
											</select>
										</div>
									</div>
								</div>
								<div class="row defaultHide">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userAddress">Address</label>
											<input type="text" id="userAddress" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row defaultHide">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userHouseNumber">House Number</label>
											<input type="number" min="1" id="userHouseNumber" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row defaultHide">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userTaxCode">Tax Code</label>
											<input type="text" id="userTaxCode" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row defaultHide">
									<div class="col-md-12">
										<div class="form-group">
											<label class="text-info" for="userPhoneNumber">Phone Number</label>
											<input type="text" id="userPhoneNumber" class="userInput form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary" id="updateBtn">Update</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-12 profileOfficeContainer"></div>
						</div>
						<div class="row feedbackForm defaultHide">
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-12">
										<label class="radio-inline">
											<input type="radio" name="feedbackValue" value="1">1
										</label>
										<label class="radio-inline">
											<input type="radio" name="feedbackValue" value="2">2
										</label>
										<label class="radio-inline">
											<input type="radio" name="feedbackValue" value="3">3
										</label>
										<label class="radio-inline">
											<input type="radio" name="feedbackValue" value="4">4
										</label>
										<label class="radio-inline">
											<input type="radio" name="feedbackValue" value="5">5
										</label>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<label class="text-info" for="feedbackComment">Comment (*)</label>
										<textarea id="feedbackComment" class="userInput form-control" row="5"></textarea>
										<input type="hidden" id="officeRefFeedback">
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary" id="sendFeedbackBtn">Send feedback</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	    <jsp:include page="footerComponent.jsp" />
	</body>
</html>