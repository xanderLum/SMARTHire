<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employer Edit Account</title>

<link rel="icon" href="<c:url value="/img/favicon.png" />"
	type="image/png">
<link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon" />


<link class="jsbin"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
	

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
	href="<c:url value="/newscripts/css/font-awesome.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/newscripts/css/responsive.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/newscripts/css/base.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/animate.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/font-awesome.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/responsive.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/style.css" />">
</head>
<body>
	<!-- Titlebar
================================================== -->
	<div id="titlebar" class="single submit-page">
		<div class="container">

			<div class="sixteen columns">
				<h2>
					<i class="fa fa-plus-circle"></i> Profile
				</h2>
			</div>

		</div>
	</div>
	<!-- Content
================================================== -->
	<div class="container">
		<!-- Logo -->
		<div class="form">
			<form name="imageForm" method="post" enctype="multipart/form-data"
				action="employerUpdateLogo/">
				<h5>
					Logo <span>(optional)</span>
				</h5>
				<input type="hidden" name="username" value="${username }" /> <label
					class="upload-btn"> <input type="file" name="image"
					onchange="readURL(this);" size="50" /> <i class="fa fa-upload"></i>
					Browse
				</label> <img id="blah"
					src="<c:url value="/newscripts/images/headshot.jpg"/>" alt="Logo" />
				<input type="submit" value="Upload" />
				<!-- <span class="fake-input">No file selected</span> -->

			</form>
		</div>

		<br>
		<hr>
		<!-- User Employer Company Details -->
		<form name="personalDetailsForm" method="post"
			action="empUpdatePersonalAccount/">
			<!-- Personal Details -->
			<!-- Company Details -->
			<div class="divider">
				<h3>Company Details</h3>
			</div>

			<!-- Company Name -->
			<div class="form">
				<h5>Company Name</h5>
				<input type="text" placeholder="Enter the name of the company">
			</div>

			<!-- Website -->
			<div class="form">
				<h5>
					Website <span>(optional)</span>
				</h5>
				<input type="text" placeholder="http://">
			</div>

			<!-- Teagline -->
			<div class="form">
				<h5>
					Tagline <span>(optional)</span>
				</h5>
				<input type="text" placeholder="Briefly describe your company">
			</div>

			<!-- Email -->
			<div class="form">
				<h5>
					Email <span></span>
				</h5>
				<input type="email" placeholder="employer@company.com" required>
			</div>
			<!-- Name -->
			<div class="form">
				<h5>First Name</h5>
				<input type="hidden" name="username" value="${username }" /> <input
					class="search-field" name="firstname" type="text"
					placeholder="eg. Edmund" value="" />
			</div>
			<div class="form">
				<h5>Last Name</h5>
				<input class="search-field" name="lastname" type="text"
					placeholder="eg. Daylightwalker" value="" />
			</div>

			<!-- Email 
					<div class="form">
						<h5>Your Email</h5>
						<input class="search-field" name="email" type="text"
							placeholder="mail@example.com" required />
					</div>-->

			<!-- Contactnumber -->
			<div class="form">
				<h5>Contact Number</h5>
				<input class="search-field" name="contactnumber" type="text"
					placeholder="e.g. +63912345671" required />
			</div>

			<!-- Title -->
			<div class="form">
				<h5>Designation</h5>
				<input class="search-field" name="designation" type="text"
					placeholder="e.g. Web Developer" required />
			</div>

			<!-- Location -->
			<div class="form">
				<h5>Location</h5>
				<input class="search-field" name="address" type="text"
					placeholder="e.g. London, UK" required />
			</div>


			<div class="modal-footer">
				<div>
					<button type="submit" class="btn btn-primary btn-lg btn-block">Save
						Personal Profile</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</div>
			</div>
		</form>


	</div>

	<script type="text/javascript">
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#blah').attr('src', e.target.result).width(150).height(
							200);
				};
				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>
</body>
<!-- Scripts
================================================== -->
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


</html>