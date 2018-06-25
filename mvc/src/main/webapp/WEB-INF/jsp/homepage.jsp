<%@ page isELIgnored="false"%>
<html>
<jsp:include page="dependencies.jsp"><jsp:param name="title" value="Welcome MVC" /></jsp:include>
<body>
	<div class="p-5">
		<div class='col-6 pull-right text-right'>
			<a href="logout">Logout</a>
		</div>
		<h1>MVC Web</h1>
		<h2>Authentication Successful</h2>

		<div>
			<table>
				<tr>
					<td>UserId</td>
					<td>${userId}</td>
				</tr>
				<tr>
					<td>UserName</td>
					<td>${userName}</td>
				</tr>
			</table>
		</div>

	</div>

</body>
</html>