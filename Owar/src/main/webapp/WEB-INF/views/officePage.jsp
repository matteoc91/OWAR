<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Office Page</title>
		<jsp:include page="headComponent.jsp" />
	</head>
	<body>
		<div class="wrap">
			<jsp:include page="hiddenComponent.jsp" />
			<jsp:include page="menuComponent.jsp" />
			<div class="container">
				<h1 class="text-primary">Office Page</h1>
				<div id="errorMsg" class="alert alert-danger hiddenMsg"></div>
				<div id="successMsg" class="alert alert-success hiddenMsg"></div>
				<c:if test="${status != null && status == 'OK'}">
					<input type="hidden" id="returnMessage">
				</c:if>
				<input type="hidden" id="numOfPage">
				<div class="row">
					<div class="col-md-6 filterContainer">
						<div class="row locationFilter">
							<div class="col-md-8">
								<div class="form-group first-group">
									<label class="text-info" for="officeFilterDistrict">District:</label>
									<select id="officeFilterDistrict" class="userInput form-control locationFilterInner">
										<option></option>
									</select>
								</div>
							</div>
							<div class="col-md-4"></div>
						</div>
						<div class="row locationFilter">
							<div class="col-md-8">
								<div class="form-group second-group">
									<label class="text-info" for="officeFilterTown">Town:</label>
									<select id="officeFilterTown" class="userInput form-control locationFilterInner">
										<option></option>
									</select>
								</div>
							</div>
							<div class="col-md-4"></div>
						</div>
						<div class="row locationFilter">
							<div class="col-md-8">
								<div class="form-group third-group">
									<label class="text-info" for="officeFilterRegion">Region:</label>
									<select id="officeFilterRegion" class="userInput form-control locationFilterInner">
										<option></option>
									</select>
								</div>
							</div>
							<div class="col-md-4"></div>
						</div>
						<div class="row">
							<div id="serviceFilterContainer" class="col-md-12"></div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<button type="button" class="btn btn-default" id="addServiceToFilterBtn">Add Service</button>
								<button type="button" class="btn btn-primary" id="applyFilterBtn">Apply Filter</button>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-8">
								<c:if test="${isEditMode}">
									<button class="btn btn-primary" id="addOffice">Add Office</button>
								</c:if>
							</div>
							<div class="col-md-4"></div>
						</div>
						<div class="row">
							<div id="officesContainer" class="col-md-12">
								<div class="row">
									<div class="col-md-12 defaultHide" id="newOfficeForm">
										<jsp:include page="newOfficeComponent.jsp"></jsp:include>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6"></div>
									<div class="col-md-6">
										<span class="text-primary  alignRight" id="officePagination"></span>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12" id="officesList"></div>
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