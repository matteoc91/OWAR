<html>
	<head>
		<title>Social page</title>
		<jsp:include page="headComponent.jsp" />
	</head>
	<body>
		<div class="wrap">
			<jsp:include page="hiddenComponent.jsp" />
			<jsp:include page="menuComponent.jsp" />
			<div class="container">
				<h1 class="text-primary">Accedi tramite social</h1>
				<div id="errorMsg" class="alert alert-danger hiddenMsg"></div>
				<div id="successMsg" class="alert alert-success hiddenMsg"></div>
				<div class="row">
					<div class="col-md-12">
						<div class="row">
							<div class="col-md-12">
								<p>
									Esegui l'accesso tramite social network.
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12" id="socialLoginSection">
								<script type="in/Login"></script>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footerComponent.jsp" />
	</body>
</html>