<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix="form"%>
	
<!DOCTYPE>
<html>
<jsp:include page="dependencies.jsp" ><jsp:paramname="title" value="Feedback" /></jsp:include>
<body>
	<div class='pull-right text-right marg-10'>
		<a href="login">Login</a>
	</div>
	<div class="col-12 col-smm-10 col-sml-7 col-sm-6 col-md-5 col-lg-4 col-xl-3 p-3" data-example-id="basic-forms">
		<h4>Feedback</h4>
		<form:form method = "POST" action="feedbackregister" commandName="fb" >
			<div class="form-group">
				<label for="exampleInputEmail">Name:<fmt:message key="fb.email" /></label>
				<form:input path="email" type="email" class="form-control" id="exampleInputEmail" placeholder="Email" />
			</div>
			<div class="form-group">
				<label for="exampleInputName">Name:<fmt:message key="fb.name" /></label>
				<form:input path="name" class="form-control" id="exampleInputName" placeholder="Name" />
			</div>
			<div class="form-group">
				<label for="exampleInputComments">Comments:<fmt:message key="fb.comments" /></label>
				<form:textarea path="comments" class="form-control" id="exampleInputComments" placeholder="Comments" />
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form:form>
	</div>
</body>
</html>