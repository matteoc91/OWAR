<html>
	<head>
		<title>Service Page</title>
		<jsp:include page="headComponent.jsp" />
	</head>
	<body>
		<div class="wrap">
			<jsp:include page="hiddenComponent.jsp" />
			<jsp:include page="menuComponent.jsp" />
			<div class="container">
				<h1 class="text-primary">Service Page</h1>
				<div id="errorMsg" class="alert alert-danger hiddenMsg"></div>
				<div id="successMsg" class="alert alert-success hiddenMsg"></div>
				<input type="hidden" id="numOfPage">
				<div class="row">
					<div class="col-md-12">
						<c:if test="${isEditMode}">
							<button class="btn btn-primary" id="addService">Add Service</button>
						</c:if>
					</div>
				</div>
				<div class="row">
					<div class="col-md-2"></div>
					<div id="officesContainer" class="col-md-8">
						<div class="row">
							<div class="col-md-12 defaultHide" id="newServiceForm">
								<jsp:include page="newServiceComponent.jsp"></jsp:include>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12" id="servicesList"></div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</div>
	    <jsp:include page="footerComponent.jsp" />
	</body>
</html>