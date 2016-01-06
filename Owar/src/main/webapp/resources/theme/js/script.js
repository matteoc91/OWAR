function regitrationRequest(user, next, failure) {
	$.ajax({
		url : $("#contextpath").val()+"/user/registration",
		type : "POST",
		data : {
			user : JSON.stringify(user)
		},
		success : function(res) {
			if(res == 1) {
				displaySuccessMessage("<strong>Success!</strong> User registration OK.");
				$("#genericModal .modal-title").html("Confirmation mail");
				$("#genericModal .modal-body p").html("An email was sent to verify your account. " +
						"Please check it to complete your registration.");
				$("#genericModal").modal("toggle");
				$("#genericModal").on("hidden.bs.modal", function(e) {
					window.location.href = $("#contextpath").val();
				});
				if(next != null) {
					next(res);
				}
			} else {
				displayErrorMessage("<strong>Error!</strong> Unable to register the new user.");
				if(failure != null) {
					failure(res);
				}
			}
		},
		error : function(res) {
			displayErrorMessage("<strong>Error!</strong> Unable to register the new user.");
			if(failure != null) {
				failure(res);
			}
		}
	});
}

function socialLoginRequest(user, next, failure) {
	$.ajax({
		url : $("#contextpath").val()+"/user/socialLogin",
		type : "POST",
		data : {
			user : JSON.stringify(user)
		},
		success : function(res) {
			if(res == 1) {
				displaySuccessMessage("<strong>Success!</strong> User login OK.");
				$("#genericModal .modal-title").html("Login success");
				$("#genericModal .modal-body p").html("Thank you for login in OWAR using LinkdIn.");
				$("#genericModal").modal("toggle");
				$("#genericModal").on("hidden.bs.modal", function(e) {
					window.location.href = $("#contextpath").val();
				});
				if(next != null) {
					next(res);
				}
			} else {
				displayErrorMessage("<strong>Error!</strong> Unable to login user.");
				if(failure != null) {
					failure(res);
				}
			}
		},
		error : function(res) {
			displayErrorMessage("<strong>Error!</strong> Unable to register the new user.");
			if(failure != null) {
				failure(res);
			}
		}
	});
}

function registration() {
	if($("#userMail").val().length > 0 && $("#userPassword").val().length > 0 && $("#userConfirmPassword").val().length > 0) {
		if(Validator.validateEmail($("#userMail").val())) {
			if($("#userPassword").val().length > 7) {
				if($("#userPassword").val() == $("#userConfirmPassword").val()) {
					var user = {};
					user.mail = $("#userMail").val();
					user.password = $("#userPassword").val();
					$("#registrationBtn").html("<span class='glyphicon glyphicon-refresh glyphicon-refresh-animate'></span> Loading...");
					regitrationRequest(user, function(res) {
						$("#registrationBtn").html("Register");
					}, function(res) {
						$("#registrationBtn").html("Register");
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
						window.location.href  = $("#contextpath").val();
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

function logout() {
	$.ajax({
		url : $("#contextpath").val()+"/user/logout",
		type : "POST",
		success : function(res) {
			window.location.href = $("#contextpath").val();
		},
		error : function(res) {
			console.log("[ERROR] - unable to logout!");
		}
	});
}