<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE>
<html>
<head>
	<jsp:include page="dependencies.jsp" ><jsp:paramname="title" value="Login" /></jsp:include>
	<script type="text/javascript" src="/<%=core.SystemParams.CONTEXT%>/js/login.js"></script>
	<meta Http-Equiv="Cache-Control" Content="no-cache">
	<meta Http-Equiv="Pragma" Content="no-cache">
	<meta Http-Equiv="Expires" Content="0">
	<script>
		history.pushState(null, null, location.href);
		window.onpopstate = function() {
			history.go(1);
		};
	</script>
</head>
<body>
	<div class='pull-right text-right marg-10'>
		<a href="feedback">Feedback</a>
	</div>
	<div class="col-12 col-smm-10 col-sml-7 col-sm-6 col-md-5 col-lg-4 col-xl-3 p-3 container" data-form-id="basic-forms">
		<h4>Login</h4>
		<form:form method = "POST" action="authenticate" onsubmit="return validateLogin();" >
			<div class="form-group">
				<form:label path="userName" for="formInputEmail">Email Address:</form:label>
				<div class="input-group">
					<form:input path="userName" type="email" class="form-control" id="formInputEmail" placeholder="Email" />
					<div class="input-group-append red marg-l-10" >*</div>
				</div>
			</div>
			<div class="form-group">
				<form:label path="password" for="formInputPassword">Password:</form:label>
				<div class="input-group">
					<form:password path="password" class="form-control" id="formInputPassword" placeholder="Password" />
					<div class="input-group-append red marg-l-10" > *</div>
				</div>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form:form>
		<div id="formError" class="red" ></div>
	</div>
</body>
</html>