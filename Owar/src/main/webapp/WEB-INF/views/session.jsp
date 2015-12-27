<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="userSessionBean" value="null" />
<c:if test="${userBean != null}">
	<c:set var="userSessionBean" value="${userBean}" />
</c:if>

<script>

	var Session = {
		
		UserBean : ${userSessionBean}
		
	};
	
</script>