function registration() {
	if($("#userMail").val().length > 0 && $("#userPassword").val().length > 7 && $("#userConfirmPassword").val().length > 7) {
		if(Validator.validateEmail($("#userMail").val())) {
			if($("#userPassword").val().length > 7) {
				if($("#userPassword").val() == $("#userConfirmPassword").val()) {
					var user = {};
					user.mail = $("#userMail").val();
					user.password = $("#userPassword").val();
					$.ajax({
						url : $("#contextpath").val()+"/user/registration",
						type : "POST",
						data : {
							user : JSON.stringify(user)
						},
						success : function(res) {
							if(res == 1) {
								displaySuccessMessage("<strong>Success!</strong> User registration OK.");
							} else {
								displayErrorMessage("<strong>Error!</strong> Unable to register the new user.");
							}
						},
						error : function(res) {
							displayErrorMessage("<strong>Error!</strong> Unable to register the new user.");
						}
					});
				} else {
					displayErrorMessage("<strong>Error!</strong> Passwords mismatch.");
				}
			} else {
				displayErrorMessage("<strong>Error!</strong> The password must be at least 8 character long.");
			}
		} else {
			displayErrorMessage("<strong>Error!</strong> Email not valid.");
		}
	} else {
		displayErrorMessage("<strong>Error!</strong> Compile all mandatory fields.");
	}
}

function displayErrorMessage(msg) {
	$("#successMsg").hide();
	$("#errorMsg").html(msg)
	$("#errorMsg").show();
}

function displaySuccessMessage(msg) {
	$("#errorMsg").hide();
	$("#successMsg").html(msg)
	$("#successMsg").show();
}

function resetFields(fields) {
	$("#errorMsg").hide();
	$("#successMsg").hide();
	$("input[class="+fields+"]").val("");
}

function login() {
	if($("#userMail").val().length > 0 && $("#userPassword").val().length > 0) {
		if(Validator.validateEmail($("#userMail").val())) {
			var user = {};
			user.mail = $("#userMail").val();
			user.password = $("#userPassword").val();
			$.ajax({
				url : $("#contextpath").val()+"/user/login",
				type : "POST",
				data : {
					user : JSON.stringify(user)
				},
				success : function(res) {
					if(res == 1) {
						displaySuccessMessage("<strong>Success!</strong> User login OK.");
					} else {
						displayErrorMessage("<strong>Error!</strong> Wrong email or password.");
					}
				},
				error : function(res) {
					displayErrorMessage("<strong>Error!</strong> Unable to login user.");
				}
			});
		} else {
			displayErrorMessage("<strong>Error!</strong> Email not valid.");
		}
	} else {
		displayErrorMessage("<strong>Error!</strong> Compile all fields.");
	}
}