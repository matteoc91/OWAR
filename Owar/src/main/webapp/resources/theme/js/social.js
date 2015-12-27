function onLinkedInLoad() {
	IN.Event.on(IN, "auth", function() {onLinkedInLogin();});
	IN.Event.on(IN, "logout", function() {onLinkedInLogout();});
}

function onLinkedInLogin() {
	IN.API.Raw("/people/~:(id,first-name,last-name,email-address)")
		.result(function(res) {
			console.log(res);
		})
		.error(function(res) {
			console.log(res);
		});
}