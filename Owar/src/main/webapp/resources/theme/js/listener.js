var locationFilterIstance = null;
var servicesFilterIstance = null;

$(document).ready(function() {
	
	locationFilterIstance = new LocationFilter();
	servicesFilterIstance = new ServicesFilter();
	
	$("a[href='#']").click(function(event) {
		event.preventDefault();
	});
	
	var currentPage = window.location.pathname;
	$(".navbarToActive a[href='"+currentPage+"']").parent().addClass("active");
	
	if($("#registrationBtn").length > 0) {
		$("#registrationBtn").click(function() {
			registration();
		});
	}
	
	if($("#resetBtn").length > 0) {
		$("#resetBtn").click(function() {
			resetFields("userInput");
		});
	}
	
	if($("#loginBtn").length > 0) {
		$("#loginBtn").click(function() {
			login();
		});
	}
	
	if($("#logoutBtn").length > 0) {
		$("#logoutBtn").click(function() {
			logout();
		});
	}
	
	if($("#addOffice").length > 0) {
		$("#addOffice").click(function() {
			displayNewOfficeForm();
		});
	}
	
	if($("#toggleMoreInfo").length > 0) {
		$("#toggleMoreInfo").click(function() {
			toggleMoreInfo();
		});
	}
	
	if($("#userDistrict").length > 0) {
		getAllDistrict("userDistrict");
	}
	
	if($("#officePagination").length > 0) {
		getOffices(1, 10);
	}
	
	if($("#getAdminPrivilege").length > 0) {
		$("#getAdminPrivilege").click(function() {
			getAdminPrivilege();
		});
	}
	
	if($("#officeDistrict").length > 0) {
		getAllDistrict("officeDistrict");
	}
	
	if($("#newOfficeBtn").length > 0) {
		$("#newOfficeBtn").click(function() {
			createNewOffice();
		});
	}
	
	if($("#addService").length > 0) {
		$("#addService").click(function() {
			displayNewServiceForm();
		});
	}
	
	if($("#newServiceBtn").length > 0) {
		$("#newServiceBtn").click(function() {
			createNewService();
		});
	}
	
	if($("#servicesList").length > 0) {
		setServicePagination("servicesList");
	}
	
	if($("#addServiceBtn").length > 0) {
		$("#addServiceBtn").click(function() {
			displayNewService("newServiceInList");
		});
	}
	
	if($("#returnMessage").length > 0) {
		displaySuccessMessage("<strong>Success!</strong> Office creation OK.");
	}
	
	/* FILTER MANAGEMENT */
	$(".locationFilterInner").change(function(event) {
		if($(event.target).val() == "") {
			$(".locationFilter").removeClass("defaultHide");
		} else {
			$(".locationFilter").addClass("defaultHide");
			$(event.target).closest(".locationFilter").toggleClass("defaultHide");
		}
	});
	/* ------------------------ */
	
	if($("#officeFilterDistrict").length > 0) {
		getAllDistrict("officeFilterDistrict");
	}
	
	if($("#officeFilterTown").length > 0) {
		getAllTown("officeFilterTown");
	}
	
	if($("#officeFilterRegion").length > 0) {
		getAllRegion("officeFilterRegion");
	}
	
	if($("#addServiceToFilterBtn").length > 0) {
		$("#addServiceToFilterBtn").click(function() {
			displayNewService("serviceFilterContainer");
		});
	}
	
	if($("#applyFilterBtn").length > 0) {
		$("#applyFilterBtn").click(function() {
			applyFilter("serviceFilterContainer");
		});
	}
	
	if($("#detailPage").length > 0) {
		populatePage();
	}
	
	if($("#leaveFeedbackBtn").length > 0) {
		$("#leaveFeedbackBtn").click(function(event) {
			$("#officeRefFeedback").val($(event.target).attr("name"));
			$(".feedbackForm").removeClass("defaultHide");
		});
	}
	
	if($("#displayFeedbackBtn").length > 0) {
		$("#displayFeedbackBtn").click(function(event) {
			displayFeedback();
		});
	}
	
	if($("#sendFeedbackBtn").length > 0) {
		$("#sendFeedbackBtn").click(function() {
			sendFeedback();
		});
	}
	
	if($("#rentBtn").length > 0) {
		$("#rentBtn").click(function() {
			rentOffice();
		});
	}
	
	if($("#updateBtn").length > 0) {
		displayUpdateProfileInput();
		$("#updateBtn").click(function() {
			updateProfile();
		});
	}
	
	if($(".profileOfficeContainer").length > 0) {
		getTenantOffices();
	}
	
	if($(".tenantList").length > 0) {
		displayTenant();
	}
	
	if($(".lessorList").length > 0) {
		displayLessor();
	}
	
	if($("#completeProfile").length > 0) {
		$("#completeProfile").click(function() {
			completeProfile();
		});
	}
	
	/*if($("#servicesList").length > 0) {
		getOfficeServices("servicesList");
	}*/
	
});