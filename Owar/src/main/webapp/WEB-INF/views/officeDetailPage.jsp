<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Office Detail Page</title>
		<jsp:include page="headComponent.jsp" />
	</head>
	<body>
		<c:if test="${officeId != null}">
			<input type="hidden" id="officeId" value="${officeId}">
		</c:if>
		<input type="hidden" id="detailPage">
		<div class="wrap">
			<jsp:include page="hiddenComponent.jsp" />
			<jsp:include page="menuComponent.jsp" />
			<div class="container">
				<h1 class="text-primary">Office Detail Page</h1>
				<div id="errorMsg" class="alert alert-danger hiddenMsg"></div>
				<div id="successMsg" class="alert alert-success hiddenMsg"></div>
				<div class="row">
					<div class="col-md-6 imgList">
						<div class="row">
							<div class="col-md-6 defaultHide imgDivContainer">
								<a href="" class="thumbnail"><img alt="office" src=""></a>
							</div>
							<div class="col-md-6 defaultHide imgDivContainer">
								<a href="" class="thumbnail"><img alt="office" src=""></a>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 defaultHide imgDivContainer">
								<a href="" class="thumbnail"><img alt="office" src=""></a>
							</div>
							<div class="col-md-6 defaultHide imgDivContainer">
								<a href="" class="thumbnail"><img alt="office" src=""></a>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="jumbotron">
							<h2 class="text-primary"><strong>Office Detail</strong></h2>
							<div class="row">
								<div class="col-md-12">
									<span class="text-muted">Description:</span> <br />
									<strong><span id="officeDescription" class="officeDescriptionInput"></span></strong>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<span class="text-muted">Address:</span> <br />
									<div class="row">
										<div class="col-md-12">
											<strong><span id="officeAddress" class="officeDescriptionInput"></span> <span id="officeHomeNumber"></span></strong>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<strong><span id="officeLocation" class="officeDescriptionInput"></span></strong>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<span class="text-muted">Available Date:</span> <br />
									<div class="row">
										<div class="col-md-2">
											<span class="officeDescriptionInput">From:</span>
										</div>
										<div class="col-md-10">
											<strong><span id="officeBeginDate" class="officeDescriptionInput"></span></strong>
										</div>
									</div>
									<div class="row">
										<div class="col-md-2">
											<span class="officeDescriptionInput">To:</span>
										</div>
										<div class="col-md-10">
											<strong><span id="officeEndDate" class="officeDescriptionInput"></span></strong>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<span class="text-muted">Daily Price:</span> <br />
									<span class="officeDescriptionInput"><strong><span id="officeDailyPrice"></span> <abbr title="EURO">&euro;</abbr></strong></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12" id="officeServicesList"></div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<button type="button" class="btn btn-default" id="displayFeedbackBtn">Display feedback</button>
								<c:if test="${checkTenant}">
									<button type="button" class="btn btn-primary" id="rentBtn">Rent</button>
									<div class="jumbotron">
										<div class="form-group">
											<label class="text-info" for="rentBeginDate">From </label>
											<input type="date" id="rentBeginDate" class="userInput form-control">
										</div>
										<div class="form-group">
											<label class="text-info" for="rentEndDate">To </label>
											<input type="date" id="rentEndDate" class="userInput form-control">
										</div>
									</div>
								</c:if>
								<c:if test="${!checkTenant}">
									<span class="text-warning">Complete your profile to rent an office.</span>
								</c:if>
							</div>
						</div>
						<div class="row feedBackList"></div>
					</div>
				</div>
			</div>
		</div>
	    <jsp:include page="footerComponent.jsp" />
	</body>
</html>