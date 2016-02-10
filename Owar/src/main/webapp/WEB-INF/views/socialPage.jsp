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
				<h1 class="text-primary">Social connection</h1>
				<div id="errorMsg" class="alert alert-danger hiddenMsg"></div>
				<div id="successMsg" class="alert alert-success hiddenMsg"></div>
				<div class="row">
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-12">
								<p>
									Connect with socials.
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12" id="socialLoginSection">
								<script type="in/Login"></script>
							</div>
						</div>
						<div class="row defaultMarginTop">
							<div class="col-md-12">
								<blockquote class="blockquote">
									<p>
										Social media spark a revelation that we, the people, have a voice, 
										and through the democratization of content and ideas we can once 
										again unite around common passions, inspire movements, and ignite change.
									</p>
									<footer>Brian Solis</footer>
								</blockquote>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<img class="img-responsive" src="${pageContext.request.contextPath}/resources/img/social.jpg">
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footerComponent.jsp" />
	</body>
</html>