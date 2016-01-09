/*
 * Set front-end session data
 */
$(document).ready(function() {
	if($("#userSessionHead").length > 0) {
		$("#userSessionHead").html(Session.UserBean.mail);
	}
	if($("#isValidated").length > 0) {
		$("#genericModal .modal-title").html("Account ready");
		$("#genericModal .modal-body p").html("Thank you for verifying your account, " +
				"now you are ready to login.");
		$("#genericModal").modal("toggle");
	}
});