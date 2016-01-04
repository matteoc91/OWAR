function onLinkedInLoad() {
	IN.Event.onOnce(IN, "auth", function() {
		onLinkedInLogin();
	});
}

function onLinkedInLogin() {
	IN.API.Raw("/people/~:(id,first-name,last-name,email-address)")
		.result(function(res) {
			console.log(res);
			//onLinkedInLogout();
		})
		.error(function(res) {
			console.log(res);
		});
}

function onLinkedInLogout() {
	IN.User.logout();
}