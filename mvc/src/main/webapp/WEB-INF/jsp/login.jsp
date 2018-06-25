<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix="form"%>
	
<!DOCTYPE>
<html>
<jsp:include page="dependencies.jsp" ><jsp:paramname="title" value="Login" /></jsp:include>
<body>
	<div class="col-12 col-smm-10 col-sml-7 col-sm-6 col-md-5 col-lg-4 col-xl-3" data-example-id="basic-forms">
		<h4>Login</h4>
		<form:form method = "POST" action="authenticate">
			<div class="form-group">
				<form:label path="userName" for="exampleInputEmail">Email Address</form:label>
				<form:input path="userName" type="email" class="form-control" id="exampleInputEmail" placeholder="Email" />
			</div>
			<div class="form-group">
				<form:label path="password" for="exampleInputPassword">Password</form:label>
				<form:input path="password" type="password" class="form-control" id="exampleInputPassword" placeholder="Password" />
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form:form>
	</div>
</body>
</html>