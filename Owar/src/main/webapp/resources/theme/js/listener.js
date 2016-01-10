$(document).ready(function() {
	
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
	
	if($("#toggleMoreInfo").length > 0) {
		$("#toggleMoreInfo").click(function() {
			toggleMoreInfo();
		});
	}
	
	if($("#userDistrict").length > 0) {
		getAllDistrict("userDistrict");
	}
	
});