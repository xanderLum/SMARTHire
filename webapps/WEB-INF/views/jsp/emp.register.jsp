<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employer Register</title>

<link rel="icon" href="<c:url value="/img/favicon.png"/>" type="image/png"/>
<link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon" />

<!-- Mobile Specific Metas
================================================== -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS
================================================== -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/newscripts/css/style.css" /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/newscripts/css/colors/green.css"/>">


<link rel="stylesheet" type="text/css"
	href="<c:url value="/newscripts/css/bootstrap.css"/>">
	
<link rel="stylesheet" type="text/css" href="<c:url value="/css/animate.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/responsive.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />">	

<script type="text/javascript"
	src="/newscripts/scripts/jquery-2.1.3.min.js" /></script>
<script type="text/javascript" src="/newscripts/scripts/custom.js" /></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.superfish.js" /></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.themepunch.tools.min.js" /></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.themepunch.revolution.min.js" /></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.themepunch.showbizpro.min.js" /></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.flexslider-min.js" /></script>
<script src="/newscripts/scripts/chosen.jquery.min.js" /></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.magnific-popup.min.js" /></script>
<script type="text/javascript"
	src="/newscripts/scripts/waypoints.min.js" /></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.counterup.min.js" /></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.jpanelmenu.js" /></script>
<script type="text/javascript" src="/newscripts/scripts/stacktable.js" /></script>
<script type="text/javascript" src="/newscripts/scripts/headroom.min.js" /></script>

</head>
<body>
	<form role="form" method="post" action="../empCreateAccount/">
		<!-- User Personal Details -->
		<div class="divider">
			<h3>User Details</h3>
		</div>
		<div class="form">
			<h5>First Name</h5>
			<input type="text" name="firstName" placeholder="eg. Michael"
				required>
		</div>
		<div class="form">
			<h5>Last Name</h5>
			<input type="text" name="lastName" placeholder="eg. Johnson" required>
		</div>

		<div class="form">
			<h5>Contact Number</h5>
			<input type="text" name="contactNumber"
				placeholder="Enter the name of the company" required>
		</div>


		<div class="form">
			<h5>Email Address</h5>
			<input type="email" name="email" placeholder="company@example.com"
				required>
		</div>

		<div class="form">
			<h5>Address</h5>
			<input type="text" name="address" placeholder="Cebu, Philippines"
				required>
		</div>


		<!-- Company Details -->
		<div class="divider">
			<h3>Company Details</h3>
		</div>

		<!-- Company Name -->
		<div class="form">
			<h5>Company Name</h5>
			<input type="text" placeholder="Enter the name of the company"
				required>
		</div>

		<!-- Website -->
		<div class="form">
			<h5>
				Website <span>(optional)</span>
			</h5>
			<input type="text" name="website" placeholder="http://">
		</div>

		<!-- Teagline -->
		<div class="form">
			<h5>
				Tagline <span>(optional)</span>
			</h5>
			<input type="text" placeholder="Briefly describe your company">
		</div>

		<!-- Logo -->
		<div class="form">
			<form action="imageDisplay">
				<h5>
					Logo <span>(optional)</span>
				</h5>
				<label class="upload-btn"> <input type="file" multiple /> <i
					class="fa fa-upload"></i> Browse
				</label>
				<!-- <span class="fake-input">No file selected</span> -->
			</form>
		</div>
		<input class="input-btn" type="submit" value="Create Account" style="width: 100%"> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>