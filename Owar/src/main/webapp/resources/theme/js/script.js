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
			displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
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
			displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
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
	$("."+fields).val("");
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
					displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
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
		url : $("#contextpath").val()+"/district/allDistrict",
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

function getAllTown(townId) {
	$.ajax({
		url : $("#contextpath").val()+"/district/allTown",
		type : "POST",
		success : function(res) {
			if(res.responseCode == 1) {
				var townList = res.responseObject;
				$.each(townList, function(index, item) {
					$("#" + townId).append($("<option>", {
						text : item.nome_provincia,
						name : item.sigla_provincia
					}));
				});
			}
		},
		error : function(res) {
			
		}
	});
}

function getAllRegion(regionId) {
	$.ajax({
		url : $("#contextpath").val()+"/district/allRegion",
		type : "POST",
		success : function(res) {
			if(res.responseCode == 1) {
				var regionList = res.responseObject;
				$.each(regionList, function(index, item) {
					$("#" + regionId).append($("<option>", {
						text : item.nome_regione,
						name : item.codiceistat_regione
					}));
				});
			}
		},
		error : function(res) {
			
		}
	});
}

function setOfficePagination() {
	var objPerPage = 10;
	$.ajax({
		url : $("#contextpath").val()+"/office/count",
		type : "POST",
		success : function(res) {
			switch(res.responseCode) {
				case 1:		// SUCCESS
					var numOfPage = Math.ceil(res.responseObject/objPerPage);
					$("#numOfPage").val(numOfPage);
					if(numOfPage > 1) {
						$("#officePagination").html("1 <span class='glyphicon glyphicon-menu-right' onclick='getOffices(2,"+objPerPage+")'></span>");
					}
					getOffices(1, objPerPage);
					break;
				case -8:	// EMPTY
					displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
					break;
				case -1:	// DB ACCESS ERROR
					displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
					break;
			}
		},
		error : function(res) {
			displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
		}
	});
}

function getOffices(page, objPerPage) {
	var requestParam = locationFilterIstance.createJSON();
	requestParam.services = JSON.stringify(servicesFilterIstance.createJSON());
	requestParam.page = page;
	requestParam.objPerPage = objPerPage;
	$.ajax({
		url : $("#contextpath").val()+"/office/officesInPage",
		type : "POST",
		data : requestParam,
		success : function(res) {
			switch(res.responseCode) {
				case 1:		// SUCCESS
					$("#numOfPage").val(Math.ceil(res.responseObject["numOfOffices"]/objPerPage));
					var param = "";
					if(page > 1) {
						param += "<span class='glyphicon glyphicon-menu-left' onclick='getOffices("+(page-1)+","+objPerPage+")'></span>";
					}
					param += page;
					if(page < $("#numOfPage").val()) {
						param += "<span class='glyphicon glyphicon-menu-right' onclick='getOffices("+(page+1)+","+objPerPage+")'></span>";
					}
					$("#officePagination").html("");
					$("#officePagination").html(param);
					setOfficesInPage(res.responseObject["offices"]);
					break;
				case -8:	// EMPTY
					displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
					break;
				case -1:	//DB ACCESS ERROR
					displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
					break;
			}
		},
		error : function(res) {
			displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
		}
	});
}

function setOfficesInPage(officesList) {
	var param = "";
	$.each(officesList, function(index, element) {
		var location = getLocationString(element.tbComuni_id);
		
		param += "<div class='row singleOffice' onclick='getOfficeDetail(" + element.id + ")'>";
		
		//param += "<div class='col-md-6'>";
		/*
		 * TODO - add image
		 * */
		//param += "</div>";
		
		param += "<div class='col-md-12'>";
		
		param += "<div class='row'>";
		param += "<div class='col-md-12 officeDescription'>";
		param += element.description;
		param += "</div>";
		param += "</div>";
		
		param += "<div class='row'>";
		param += "<div class='col-md-12 officeAddress'>";
		param += element.address + " " + element.home_number + ", " + location;
		param += "</div>";
		param += "</div>";
		
		param += "<div class='row'>";
		param += "<div class='col-md-12 officePrice'>";
		param += element.daily_price + " EURO";
		param += "</div>";
		param += "</div>";
		
		param += "</div>";
		
		param += "</div>";
	});
	$("#officesList").html(param);
}

function getAdminPrivilege() {
	$.ajax({
		url : $("#contextpath").val()+"/user/getAdminPrivilege",
		type : "POST",
		success : function(res) {
			if(res.responseCode == 1) {
				displaySuccessMessage("<strong>Success!</strong> Admin status OK.");
				window.location.href = $("#contextpath").val();
			}
			else {
				displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
			}
		},
		error : function(res) {
			displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
		}
	});
}

function displayNewOfficeForm() {
	$("#newOfficeForm").removeClass("defaultHide");
}

function displayNewService(serviceContainerId) {
	var param = "";
	param += "<div class='row singleService'>";
	
	param += "<div class='col-md-6'>";
	
	param += "<div class='form-group'>";
	param += "<label class='text-info'>Service (*)</label>";
	param += "<select class='userInput form-control serviceId'>";
	param += "<option></option>";
	param += "</select>";
	param += "</div>";
	
	param += "</div>";
	
	param += "<div class='col-md-6'>";
	
	param += "<div class='form-group'>";
	param += "<label class='text-info'>Number (*)</label>";
	param += "<input type='number' min='1' class='userInput form-control serviceNumber'>";
	param += "</div>";
	
	param += "</div>";
	
	param += "</div>";
	
	param += "</div>";
	
	$("#"+serviceContainerId).append(param);
	
	populateServices(serviceContainerId);
}

function createNewOffice() {
	var office = {};
	office.address = $("#officeAddress").val();
	office.home_number = $("#officeHomeNumber").val();
	office.tbComuni_id = $("#officeDistrict").find(":selected").attr("name");
	office.description = $("#officeDescription").val();
	office.daily_price = $("#officeDailyPrice").val();
	office.date_begin = $("#officeBeginDate").val();
	office.date_end = $("#officeEndDate").val();
	
	var services = new ServicesFilter();
	services.init("newServiceInList");
	
	$.ajax({
		url : $("#contextpath").val()+"/office/add",
		type : "POST",
		data : {
			office : JSON.stringify(office),
			services : JSON.stringify(services.createJSON())
		},
		success : function(res) {
			if(res.responseCode == 1) {
				displaySuccessMessage("<strong>Success!</strong> Office creation OK.");
				$(".officeImg").each(function(index, item) {
					$(item).attr("name", "image"+index);
					$("#newOfficeImgListForm").attr("action", $("#contextpath").val()+"/office/setImage")
					$("#newOfficeImgListForm").submit();
				});
			} else {
				displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
			}
		},
		error : function(res) {
			displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
		}
	});
}

function getLocationString(tbComuni_id) {
	var location = "location";
	$.ajax({
		url : $("#contextpath").val()+"/district/location",
		type : "POST",
		async : false,
		data : {
			id : tbComuni_id
		},
		success : function(res) {
			if(res.responseCode == 1) {
				location = res.responseObject;
			}
		},
		error : function(res) {
			
		}
	});
	return location;
}

function displayNewServiceForm() {
	$("#newServiceForm").removeClass("defaultHide");
}

function createNewService() {
	var service = {};
	service.type = $("#serviceType").val();
	service.description = $("#serviceDescription").val();
	
	$.ajax({
		url : $("#contextpath").val()+"/office/createService",
		type : "POST",
		data : {
			service : JSON.stringify(service)
		},
		success : function(res) {
			if(res.responseCode == 1) {
				displaySuccessMessage("<strong>Success!</strong> Service creation OK.");
			} else {
				displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
			}
		},
		error : function(res) {
			displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
		}
	});
}

function setServicePagination() {
	$.ajax({
		url : $("#contextpath").val()+"/office/serviceInPage",
		type : "POST",
		success : function(res) {
			
		},
		error : function(res) {
			
		}
	});
}

function populateServices(containerId) {
	$.ajax({
		url : $("#contextpath").val()+"/office/serviceInPage",
		type : "POST",
		success : function(res) {
			if(res.responseCode == 1) {
				var serviceList = res.responseObject;
				$.each(serviceList, function(index, item) {
					$("#"+containerId+" .singleService:last-child .serviceId").append($("<option>", {
						text : item.type + " - " + item.description,
						name : item.id
					}));
				});
			}
		},
		error : function(res) {
			
		}
	});
}

function getOfficeDetail(officeId) {
	window.location.href = $("#contextpath").val() + "/officeDetailPage/" + officeId;
}

function applyFilter(servicesContainerId) {
	locationFilterIstance.init();
	servicesFilterIstance.init(servicesContainerId);
	getOffices(1, 10);
}

function populatePage() {
	if($("#officeId").length > 0) {
		$.ajax({
			url : $("#contextpath").val()+"/office/getOffice",
			type : "POST",
			data : {
				officeId : $("#officeId").val()
			},
			success : function(res) {
				if(res.responseCode == 1) {
					Session.OfficeBean = res.responseObject;
					$("#officeDescription").html(Session.OfficeBean.description);
					$("#officeAddress").html(Session.OfficeBean.address);
					$("#officeHomeNumber").html(Session.OfficeBean.home_number);
					$("#officeLocation").html(getLocationString(Session.OfficeBean.tbComuni_id));
					$("#officeBeginDate").html(getDateFromTimeStamp(Session.OfficeBean.date_begin));
					$("#officeEndDate").html(getDateFromTimeStamp(Session.OfficeBean.date_end));
					$("#officeDailyPrice").html(Session.OfficeBean.daily_price);
					var imageList = getOfficeImg(Session.OfficeBean.id);
					var itemList = $(".imgList img");
					if(imageList != null) {
						$.each(imageList, function(index, item) {
							$(itemList[index]).attr("src", $("#contextpath").val()+"/office/displayImage/"+item);
							$(itemList[index]).closest(".imgDivContainer").removeClass("defaultHide");
						});
					}
				} else {
					displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
				}
			},
			error : function(res) {
				displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
			}
		});
	}
}

function getOfficeImg(id) {
	var officesImg = null;
	$.ajax({
		url : $("#contextpath").val()+"/office/officeImages",
		type : "POST",
		async : false,
		data : {
			officeId : id
		},
		success : function(res) {
			if(res.responseCode == 1) {
				officesImg = res.responseObject;
			} else {
				officesImg = null;
			}
		},
		error : function(res) {
			officesImg = null;
		}
	});
	return officesImg;
}

function getDateFromTimeStamp(timestamp) {
	var date = new Date(timestamp);
	var dayNumber = date.getDate();
	var monthNumber = date.getMonth()+1;
	var fullYear = date.getFullYear();
	if(dayNumber < 10) {
		dayNumber = "0" + dayNumber;
	}
	if(monthNumber < 10) {
		monthNumber = "0" + monthNumber;
	}
	return dayNumber + "/" + monthNumber + "/" + fullYear;
}

function displayFeedback() {
	$.ajax({
		url : $("#contextpath").val()+"/office/getFeedback",
		type : "POST",
		data : {
			officeId : Session.OfficeBean.id
		},
		success : function(res) {
			switch(res.responseCode) {
				case 1:
					var feedbackList = res.responseObject;
					var param = "<div class='col-md-12'>";
					$.each(feedbackList, function(index, item) {
						param += "<div class='row singleFeedback'>";
						param += "<div class='col-md-12'>";
						
						param += "<div class='row singleFeedbackValue'>";
						param += "<div class='col-md-6'>";
						param += "<span class='text-info'>Value: </span>";
						param += "</div>";
						param += "<div class='col-md-6'>";
						param += "<span class='text-info'>"+item.office_valuation+"</span>";
						param += "</div>";
						param += "</div>";
						
						param += "<div class='row singleFeedbackComment'>";
						param += "<div class='col-md-6'>";
						param += "<span class='text-info'>Comment: </span>";
						param += "</div>";
						param += "<div class='col-md-6'>";
						param += "<span class='text-info'>"+item.comment+"</span>";
						param += "</div>";
						param += "</div>";
						
						param += "</div>";
						param += "</div>"
					});
					param += "</div>";
					$(".feedBackList").html(param);
					break;
				case -8:
					$(".feedBackList").html("<div class='col-md-12 text-warning'>No feedback found.</div>")
					break;
			}
		},
		error : function(res) {
			
		}
	});
}

function sendFeedback() {
	var feedbackItem = {};
	feedbackItem.office_valuation = $("input[name=feedbackValue]:checked").val();
	feedbackItem.comment = $("#feedbackComment").val();
	//feedbackItem.office_id = Session.OfficeBean.id;
	feedbackItem.office_id = $("#officeRefFeedback").val();
	$.ajax({
		url : $("#contextpath").val()+"/office/addFeedback",
		type : "POST",
		data : {
			feedback : JSON.stringify(feedbackItem)
		},
		success : function(res) {
			if(res.responseCode == 1) {
				displaySuccessMessage("<strong>Success!</strong> Feedback creation OK.");
			} else {
				displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
			}
		},
		error : function(res) {
			displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
		}
	});
}

function rentOffice() {
	var json = {};
	json.office_id = Session.OfficeBean.id;
	json.date_begin = $("#rentBeginDate").val();
	json.date_end = $("#rentEndDate").val();
	json.tenant_id = Session.UserBean.id;
	$.ajax({
		url : $("#contextpath").val()+"/office/rentOffice",
		type : "POST",
		data : {
			officeHasTenant : JSON.stringify(json)
		},
		success : function(res) {
			if(res.responseCode == 1) {
				displaySuccessMessage("<strong>Success!</strong> Office rented.");
			} else {
				displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
			}
		},
		error : function(res) {
			displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
		}
	});
}

function displayUpdateProfileInput() {
	var container = $(".profileDetailContainer");
	if(Session.UserBean.name == null) {
		container.removeClass("defaultHide");
		$("#userName").closest(".row").removeClass("defaultHide");
	}
	if(Session.UserBean.surname == null) {
		container.removeClass("defaultHide");
		$("#userSurname").closest(".row").removeClass("defaultHide");
	}
	if(Session.UserBean.birth_date == null) {
		container.removeClass("defaultHide");
		$("#userBirthDate").closest(".row").removeClass("defaultHide");
	}
	if(Session.UserBean.comune_id == null || Session.UserBean.comune_id < 1) {
		container.removeClass("defaultHide");
		$("#userDistrict").closest(".row").removeClass("defaultHide");
	}
	if(Session.UserBean.address == null) {
		container.removeClass("defaultHide");
		$("#userAddress").closest(".row").removeClass("defaultHide");
	}
	if(Session.UserBean.house_number == null || Session.UserBean.house_number < 1) {
		container.removeClass("defaultHide");
		$("#userHouseNumber").closest(".row").removeClass("defaultHide");
	}
	if(Session.UserBean.tax_code == null) {
		container.removeClass("defaultHide");
		$("#userTaxCode").closest(".row").removeClass("defaultHide");
	}
	if(Session.UserBean.phone_number == null) {
		container.removeClass("defaultHide");
		$("#userPhoneNumber").closest(".row").removeClass("defaultHide");
	}
}

function updateProfile() {
	var json = Session.UserBean;
	
	if(Session.UserBean.name != null) {
		json.name = Session.UserBean.name;
	} else if($("#userName").val() != "") {
		json.name = $("#userName").val();
	}
	
	if(Session.UserBean.surname != null) {
		json.surname = Session.UserBean.surname;
	} else if($("#userSurname").val() != "") {
		json.surname = $("#userSurname").val();
	}
	
	if(Session.UserBean.birth_date != null) {
		json.birth_date = Session.UserBean.birth_date;
	} else if($("#userBirthDate").val() != "") {
		json.birth_date = $("#userBirthDate").val();
	}
	
	if(Session.UserBean.comune_id != null && Session.UserBean.comune_id > 0) {
		json.comune_id = Session.UserBean.comune_id;
	} else if($("#userDistrict").val() != "") {
		json.comune_id = $("#userDistrict").find(":selected").attr("name");
	} else {
		json.comune_id = null;
	}
	
	if(Session.UserBean.address != null) {
		json.address = Session.UserBean.address;
	} else if($("#userAddress").val() != "") {
		json.address = $("#userAddress").val();
	}
	
	if(Session.UserBean.house_number != null && Session.UserBean.house_number > 0) {
		json.house_number = Session.UserBean.house_number;
	} else if($("#userHouseNumber").val() != "") {
		json.house_number = $("#userHouseNumber").val()
	} else {
		json.house_number = null;
	}
	
	if(Session.UserBean.tax_code != null) {
		json.tax_code = Session.UserBean.tax_code;
	} else if($("#userTaxCode").val() != "") {
		json.tax_code = $("#userTaxCode").val()
	}
	
	if(Session.UserBean.phone_number != null) {
		json.phone_number = Session.UserBean.phone_number;
	} else if($("#userPhoneNumber").val() != "") {
		json.phone_number = $("#userPhoneNumber").val()
	}
	
	$.ajax({
		url : $("#contextpath").val()+"/user/update",
		type : "POST",
		data : {
			user : JSON.stringify(json)
		},
		success : function(res) {
			if(res.responseCode == 1) {
				displaySuccessMessage("<strong>Success!</strong> Update profile OK.");
			} else {
				displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
			}
		},
		error : function(res) {
			displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
		}
	});
}

function getTenantOffices() {
	var containerClass = "profileOfficeContainer";
	$.ajax({
		url : $("#contextpath").val()+"/office/getTenantOffices",
		type : "POST",
		data : {
			userId : Session.UserBean.id
		},
		success : function(res) {
			switch(res.responseCode) {
				case 1:
					setTenantOffices(res.responseObject, containerClass);
					break;
				case -8:
					$("."+containerClass).html("<span class='text-warning'>No office rented.</span>");
					break;
			}
		},
		error : function(res) {
			
		}
	});
}

function setTenantOffices(officeList, containerClass) {
	param = "";
	$.each(officeList, function(index, item) {
		param += "<div class='row singleOffice'>";
		
		param += "<div class='col-md-6' onclick='getOfficeDetail(" + item.office_id + ")'>";
		
		param += "<div class='row'>";
		param += "<div class='col-md-12'>Office ID: <span class='officeId text-info'>";
		param += item.office_id;
		param += "</span></div>";
		param += "</div>";
		
		param += "<div class='row'>";
		param += "<div class='col-md-12'>Begin rent date: <span class='officeBeginDate text-info'>";
		param += getDateFromTimeStamp(item.date_begin);
		param += "</span></div>";
		param += "</div>";
		
		param += "<div class='row'>";
		param += "<div class='col-md-12'>End rent date: <span class='officeEndDate text-info'>";
		param += getDateFromTimeStamp(item.date_end);
		param += "</span></div>";
		param += "</div>";
		
		param += "</div>";
		
		param += "<div class='col-md-6'>";
		
		var link = $("#contextpath").val()+"/office/QRCode/"+item.office_id+"/"+item.tenant_id+"/"+item.date_begin+"/"+item.date_end;
		
		param += "<div class='row'>";
		param += "<div class='col-md-12'>";
		param += "<a href='"+link+"' target='_blank' class='btn btn-info'>Generate QRCode</a>"
		param += "</div>";
		param += "</div>";
		
		param += "<div class='row'>";
		param += "<div class='col-md-12'>";
		param += "<button type='button' class='btn btn-primary' name='"+item.office_id+"' onclick='displayFeedbackForm(event)'>Leave a feedback</button>";
		param += "</div>";
		param += "</div>";
		
		param += "</div>";
		
		param += "</div>";
	});
	$("."+containerClass).html(param);
}

function displayTenant() {
	/*$.ajax({
		url : $("#contextpath").val()+"/office/getTenantFromOfficeRenting",
		type : "POST",
		success : function(res) {
			
		},
		error : function(res) {
			
		}
	});*/
}

function displayLessor() {
	/*$.ajax({
		url : $("#contextpath").val()+"/office/getLessorFromOfficeRenting",
		type : "POST",
		success : function(res) {
			
		},
		error : function(res) {
			
		}
	});*/
}

function completeProfile() {
	if($("#userPiva").val() != "") {
		var json = {};
		json.user_id = Session.UserBean.id;
		json.piva = $("#userPiva").val();
		$("#rentBtn").html("<span class='glyphicon glyphicon-refresh glyphicon-refresh-animate'></span> Loading...");
		$.ajax({
			url : $("#contextpath").val()+"/user/completeProfile",
			type : "POST",
			data : {
				lessor : JSON.stringify(json)
			},
			success : function(res) {
				if(res.responseCode == 1) {
					displaySuccessMessage("<strong>Success!</strong> Profile Complete.");
				} else {
					displayErrorMessage("<strong>Error!</strong> " + res.responseStatus + ".");
				}
				$("#rentBtn").html("Rent");
			},
			error : function(res) {
				displayErrorMessage("<strong>Error!</strong> Failed to contact WS.");
				$("#rentBtn").html("Rent");
			}
		});
	}
}

function displayFeedbackForm(event) {
	$("#officeRefFeedback").val($(event.target).attr("name"));
	$(".feedbackForm").removeClass("defaultHide");
}