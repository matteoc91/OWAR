function onLinkedInLoad() {
	IN.Event.onOnce(IN, "auth", function() {
		onLinkedInLogin();
	});
}

function onLinkedInLogin() {
	IN.API.Raw("/people/~:(id,first-name,last-name,email-address)")
		.result(function(res) {
			console.log(res);
			$("#socialLoginSection").html("<span class='glyphicon glyphicon-refresh glyphicon-refresh-animate'></span> Loading...");
			var user = {};
			user.mail = res.emailAddress;
			user.linkedin_id = res.id;
			user.valid = 1;
			socialLoginRequest(user, function(res) {
				onLinkedInLogout();
				$("#socialLoginSection").html("");
			}, function(res) {
				onLinkedInLogout();
				$("#socialLoginSection").html("");
			});
		})
		.error(function(res) {
			console.log(res);
			displayErrorMessage("<strong>Error!</strong> Unable to login to Twitter.");
		});
}

function onLinkedInLogout() {
	IN.User.logout();
}