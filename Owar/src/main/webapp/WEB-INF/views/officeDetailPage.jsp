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
							<div class="col-md-12 main defaultHide imgDivContainer">
								<img alt="office" src="">
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 sub defaultHide imgDivContainer">
								<img alt="office" src="">
							</div>
							<div class="col-md-4 sub defaultHide imgDivContainer">
								<img alt="office" src="">
							</div>
							<div class="col-md-4 sub defaultHide imgDivContainer">
								<img alt="office" src="">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-12 text-info">
								<span id="officeDescription"></span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 text-info">
								<div class="row">
									<div class="col-md-12">
										<span id="officeAddress"></span> <span id="officeHomeNumber"></span>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<span id="officeLocation"></span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 text-info">
								<div class="row">
									<div class="col-md-12">
										From: </span><span id="officeBeginDate"></span>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										To: </span><span id="officeEndDate"></span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 text-info">
								Daily Price: <span id="officeDailyPrice"></span> EURO
							</div>
						</div>
						<div class="row">
							<div class="col-md-12" id="servicesList"></div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<button type="button" class="btn btn-default" id="displayFeedbackBtn">Display feedback</button>
								<c:if test="${checkTenant}">
									<button type="button" class="btn btn-primary" id="rentBtn">Rent</button>
									<div class="form-group">
										<label class="text-info" for="rentBeginDate">From </label>
										<input type="date" id="rentBeginDate" class="userInput form-control">
									</div>
									<div class="form-group">
										<label class="text-info" for="rentEndDate">To </label>
										<input type="date" id="rentEndDate" class="userInput form-control">
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