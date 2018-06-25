<%@ page isELIgnored="false"%>
<html>
<jsp:include page="dependenciesHeader.jsp"><jsp:param name="title" value="HomePage" /></jsp:include>
<body>
	<div>
		<div class="p-3">
			<div class='col-6 pull-right text-right'>
				<a href="logout">Logout</a>
			</div>
			<h1>MVC Web</h1>
			<h2>Authentication Successful</h2>
		</div>

		<div class="container" >
			<table class="col-12" >
				<tr>
					<th class="pull-right" >User Id: </th>
					<td style="width:10px" ></td>
					<td>${userId}</td>
				</tr>
				<tr>
					<th class="pull-right" >User Name: </th>
					<td style="width:10px" ></td>
					<td>${userName}</td>
				</tr>
			</table>
		</div>

	</div>

</body>
</html>