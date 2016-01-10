function regitrationRequest(user, next, failure) {
	$.ajax({
		url : $("#contextpath").val()+"/user/registration",
		type : "POST",
		data : {
			user : JSON.stringify(user)
		},
		success : function(res) {
			if(res.responseCode == 1) {
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
				displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
				if(failure != null) {
					failure(res);
				}
			}
		},
		error : function(res) {
			displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
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
			if(res.responseCode == 1) {
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
				displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
				if(failure != null) {
					failure(res);
				}
			}
		},
		error : function(res) {
			displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
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
					if($("#userName").val().length > 0) {
						user.name = $("#userName").val();
					}
					if($("#userSurname").val().length > 0) {
						user.surname = $("#userSurname").val();
					}
					if($("#userBirthDate").val().length > 0) {
						user.birth_date = $("#userBirthDate").val();
					}
					if($("#userDistrict").val().length > 0) {
						user.comune_id = $("#userDistrict").find(":selected").attr("name");
					}
					if($("#userAddress").val().length > 0) {
						user.address = $("#userAddress").val();
					}
					if($("#userHouseNumber").val().length > 0) {
						user.house_number = $("#userHouseNumber").val();
					}
					if($("#userTaxCode").val().length > 0) {
						user.tax_code = $("#userTaxCode").val();
					}
					if($("#userPhoneNumber").val().length > 0) {
						user.phone_number = $("#userPhoneNumber").val();
					}
					
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
			$("#loginBtn").html("<span class='glyphicon glyphicon-refresh glyphicon-refresh-animate'></span> Loading...");
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
					if(res.responseCode == 1) {
						displaySuccessMessage("<strong>Success!</strong> User login OK.");
						$("#loginBtn").html("Login");
						window.location.href  = $("#contextpath").val();
					} else {
						$("#loginBtn").html("Login");
						displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
					}
				},
				error : function(res) {
					$("#loginBtn").html("Login");
					displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
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
			console.log("[ERROR] - ResponseCode: " + res.responseCode);
			console.log("[ERROR] - ResponseStatus: " + res.responseStatus);
		}
	});
}

function toggleMoreInfo() {
	var btnClass = $($("#toggleMoreInfo span[class*='glyphicon-menu-']")[0]).attr("class").split(" ");
	$(btnClass).each(function(index, value) {
		if(!(value.indexOf("glyphicon-menu-") < 0)) {
			switch(value) {
				case "glyphicon-menu-up":
					$("#toggleMoreInfo").html("<span class='glyphicon glyphicon-menu-down'></span> More info");
					break;
				case "glyphicon-menu-down":
					$("#toggleMoreInfo").html("<span class='glyphicon glyphicon-menu-up'></span> Hide info");
					break;
			}
		}
	});
	if($(".moreInfo").length > 0) {
		$(".moreInfo").toggle(300);
	}
}

function getAllDistrict(districtId) {
	$.ajax({
		url : $("#contextpath").val()+"/district/all",
		type : "POST",
		success : function(res) {
			if(res.responseCode == 1) {
				var districtList = res.responseObject;
				$.each(districtList, function(index, item) {
					$("#" + districtId).append($("<option>", {
						text : item.nome_comune,
						name : item.id
					}));
				});
			}
		},
		error : function(res) {
			
		}
	});
}