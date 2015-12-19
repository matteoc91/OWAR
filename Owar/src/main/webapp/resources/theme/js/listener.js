$(document).ready(function() {
	
	var currentPage = window.location.pathname;
	$(".navbarToActive a[href='"+currentPage+"']").parent().addClass("active");
	
	if($("#registrationBtn").length > 0) {
		$("#registrationBtn").click(function() {
			registration();
		});
	}
	
	if(("#resetBtn").length > 0) {
		$("#resetBtn").click(function() {
			resetFields("userInput");
		});
	}
	
});