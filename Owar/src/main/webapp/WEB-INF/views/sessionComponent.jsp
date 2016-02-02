<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="userSessionBean" value="null" />
<c:if test="${userBean != null && userBean != 'null'}">
	<c:set var="userSessionBean" value="${userBean}" />
</c:if>
<c:set var="adminSessionBean" value="null" />
<c:if test="${administratorBean != null && administratorBean != 'null'}">
	<c:set var="adminSessionBean" value="${administratorBean}" />
</c:if>
<c:set var="tenantSessionBean" value="null" />
<c:if test="${tenantBean != null && tenantBean != 'null'}">
	<c:set var="tenantSessionBean" value="${tenantBean}" />
</c:if>
<c:set var="lessorSessionBean" value="null" />
<c:if test="${lessorBean != null && lessorBean != 'null'}">
	<c:set var="lessorSessionBean" value="${lessorBean}" />
</c:if>

<script>

	var Session = {
		
		UserBean : ${userSessionBean},
		AdministratorBean : ${administratorBean},
		TenantBean : ${tenantSessionBean},
		LessorBean : ${lessorSessionBean},
		OfficeBean : null
		
	};
	
</script>