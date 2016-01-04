/*
 * Set front-end session data
 */
$(document).ready(function() {
	if($("#userSessionHead").length > 0) {
		$("#userSessionHead").html(Session.UserBean.mail);
	}
});