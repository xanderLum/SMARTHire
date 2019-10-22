<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jobseeker Register</title>

<link rel="icon" href="<c:url value="/img/favicon.png"/>"
	type="image/png" />
<link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon" />

<!-- Mobile Specific Metas
================================================== -->
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

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<!--==========Title======================================== -->
	<div id="titlebar" class="single submit-page">
		<div class="container">
			<div class="sixteen columns">
				<h2>
					<i class="fa fa-plus-circle"></i> Complete your profile before
					proceeding.
				</h2>
			</div>
		</div>
	</div>

	<!-- Content
================================================== -->
	<div class="container">
		<!-- Submit Page -->
		<!-- <form method="post" action="profile/">  -->
		<!-- SOCIAL MEDIA IMPORTS -->
		<div class="sixteen columns">
			<div class="submit-page">
				<!-- Linked In -->
				<div class="form">
					<h5>LinkedIn</h5>
					<a class="button linkedin-btn" data-toggle="modal"
						data-target="#linkedIn-modal">Import from LinkedIn</a>
				</div>

				<!-- Upwork -->
				<div class="form">
					<h5>Upwork</h5>
					<a class="button upwork-btn" data-toggle="modal"
						data-target="#upwork-modal">Import from Upwork</a>
				</div>

				<!-- Facebook -->
				<div class="form">
					<h5>Facebook</h5>
					<a href="#" class="button facebook-btn"> <fb:login-button
							scope="user_posts" onlogin="checkLoginState();">Import from Facebook</fb:login-button></a>
				</div>

				<!-- Twitter -->
				<div class="form">
					<h5>Twitter</h5>
					<a class="button twitter-btn" data-toggle="modal"
						data-target="#twitter-modal">Import from Twitter</a>
				</div>

				<!-- Logo -->
				<div class="form">
					<form name="imageForm" method="post" enctype="multipart/form-data"
						action="jsUpdateImage/">
						<h5>
							Photo <span>(optional)</span> <input type="hidden"
								name="username" value="${username }" />
						</h5>
						<label class="upload-btn"> <input type="file" name="image"
							onchange="readURL(this);" size="50" /> <i class="fa fa-upload"></i>
							Browse
						</label> <img id="blah"
							src="<c:url value="/newscripts/images/headshot.jpg"/>"
							alt="your image" /> <input type="submit" value="Upload" />
					</form>
				</div>

				<br>
				<hr>
				<form name="personalDetailsForm" method="post"
					action="jsUpdatePersonalAccount/">
					<!-- Personal Details -->
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

					<!-- Email -->
					<div class="form">
						<h5>Your Email</h5>
						<input class="search-field" name="email" type="text"
							placeholder="mail@example.com" required />
					</div>

					<!-- Contactnumber -->
					<div class="form">
						<h5>Contact Number</h5>
						<input class="search-field" name="contactnumber" type="text"
							placeholder="e.g. +63912345671" required />
					</div>

					<!-- Title -->
					<div class="form">
						<h5>Professional Title</h5>
						<input class="search-field" name="professionalTitle" type="text"
							placeholder="e.g. Web Developer" required />
					</div>

					<!-- Location -->
					<div class="form">
						<h5>Location</h5>
						<input class="search-field" name="address" type="text"
							placeholder="e.g. London, UK" required />
					</div>


					<!-- Description -->
					<div class="form">
						<h5>Profile Content</h5>
						<textarea class="WYSIWYG" name="description" cols="40" rows="3"
							id="summary" spellcheck="true"></textarea>
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

				<!-- </div>	<div class="submit-page">  -->
				<!-- Credentials -->
				<!-- Education -->
				<div class="form with-line">
					<h5>
						Education <span>(optional)</span> 
					</h5>
					<div class="form-inside">
						<form method="post" name="educationForm" action="addEducation/"
							onsubmit="return checkEduDate();">
							<!-- Add Education -->
							<input type="hidden"
							name="username" value="${username }" />
							<div class="form boxed box-to-clone education-box">
								<a href="#" class="close-form remove-box button"><i
									class="fa fa-close"></i></a> <input class="search-field"
									type="text" name="schoolName" placeholder="School Name" />
								<input class="search-field" type="text" name="qualifications"
									placeholder="Qualification(s)" /> <input class="search-field"
									type="text" name="startDate"
									placeholder="Start date: yyyy/mm/dd" /> <input
									class="search-field" type="text" name="endDate"
									placeholder="End date: yyyy/mm/dd" />
								<textarea name="notes" id="desc" cols="30" rows="10"
									placeholder="Notes (optional)"></textarea>
							</div>

							<!-- <a href="#" class="button gray add-education add-box"><i
								class="fa fa-plus-circle"></i> Add Education</a>
 							-->
							<div class="modal-footer">
								<div>
									<button type="submit" class="btn btn-primary btn-lg btn-block">Add
										Education</button>
								</div>
							</div>
						</form>
					</div>
				</div>

				<!-- Experience  -->
				<div class="form with-line">
					<h5>
						Experience <span>(optional)</span> 
					</h5>
					<div class="form-inside">
						<form method="post" name="expForm" action="addExperience/"
							onsubmit="return checkExpDate();">
							<!-- Add Experience -->
							<input type="hidden"
							name="username" value="${username }" />
							<div class="form boxed box-to-clone experience-box">
								<a href="#" class="close-form remove-box button"> <i
									class="fa fa-close"></i></a> <input class="search-field"
									name="employer" type="text" placeholder="Employer" required />
								<input class="search-field" name="jobTitle" type="text"
									placeholder="Job Title" required /> <input
									class="search-field" name="startDate" type="text"
									placeholder="Start Date: yyyy/mm/dd" required /> <input
									class="search-field" name="endDate" type="text"
									placeholder="End Date: yyyy/mm/dd" required />
								<textarea name="notes" id="desc1" cols="30" rows="10"
									placeholder="Notes (optional)"></textarea>
							</div>
							<!-- <a href="#" class="button gray add-experience add-box"><i
								class="fa fa-plus-circle"></i> Add Experience</a>
							-->

							<div class="modal-footer">
								<div>
									<button type="submit" class="btn btn-primary btn-lg btn-block">Add
										Experience</button>
								</div>
							</div>
						</form>
					</div>
				</div>

				<!-- Certificate  -->
				<div class="form with-line">
					<h5>
						Certificate <span>(optional)</span> <input type="hidden"
							name="username" value="${username }" />
					</h5>
					<div class="form-inside">
						<form method="post" action="addCertificate/">
							<!-- Add Experience -->
							<div class="form boxed box-to-clone experience-box">
								<a href="#" class="close-form remove-box button"><i
									class="fa fa-close"></i></a> <input class="search-field"
									type="text" name="certName" placeholder="Certificate Name" />
								<textarea name="certificateDescription" id="desc1" cols="30"
									rows="10" placeholder="Notes (optional)"></textarea>
							</div>
							<!--  
							<a href="#" class="button gray add-experience add-box"><i
								class="fa fa-plus-circle"></i> Add Experience</a>
								-->
							<div class="modal-footer">
								<div>
									<button type="submit" class="btn btn-primary btn-lg btn-block">Add
										Certificate</button>
								</div>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>

	</div>

	<!-- MODALS -->
	<!-- BEGIN # MODAL LinkedIn Update -->
	<!-- Modal -->
	<div class="modal fade" id="linkedIn-modal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Please input:</h4>
				</div>
				<form id="update-linkedIn-form" method="post"
					action="linkedInUpdateURL/">

					<div class="modal-body">
						<input class="search-field" id="l_publicUrl" name="l_publicUrl" type="text"
							placeholder="LinkedIn Public Profile URL" value="" required/>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary btn-lg btn-block">Update</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- END # MODAL LinkedIn Update -->

	<!-- BEGIN # MODAL Upwork Update -->
	<div class="modal fade" id="upwork-modal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Please input:</h4>
				</div>
				<form id="update-upwork-form" method="post"	action="upworkUpdateURL/">
					<div class="modal-body">
						<input class="search-field" id="u_publicUrl" name="u_publicUrl" type="text"
							placeholder="Upwork Public Profile URL" value="" required/>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary btn-lg btn-block">Update</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- END # MODAL Upwork Update -->

	<!-- BEGIN # MODAL Twitter Update -->
	<div class="modal fade" id="twitter-modal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Please input:</h4>
				</div>
				<form id="update-upwork-form" method="post"
					action="twitterUpdateUsername/">
					<div class="modal-body">
						<input class="search-field" id="t_username" name="t_username" type="text"
							placeholder="Twitter Username" value="" required/>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary btn-lg btn-block">Update</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</div>
				</form>
			</div>
		</div>
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
	<script type="text/javascript">
		function checkEduDate() {
			var eduStartDate = document.forms["educationForm"]["eduStartDate"].value;
			var eduEndDate = document.forms["educationForm"]["eduEndDate"].value;
			var valid = false;

			if (!moment(eduStartDate, 'YYYY/MM/DD', true).isValid()) {
				console.log('Invalid Start Date of Education');
				alert('Invalid START DATE of EDUCATION');
				valid = false;
			} else {
				console.log('Valid StartDate Education');
				if (!moment(eduEndDate, 'YYYY/MM/DD', true).isValid()) {
					console.log('Invalid End Date of Education');
					valid = false;
				} else {
					valid = true;
				}
			}
			return valid;
		}

		function checkExpDate() {
			var expStartDate = document.forms["expForm"]["expStartDate"].value;
			var expEndDate = document.forms["expForm"]["expEndDate"].value;

			var valid = false;

			if (!moment(expStartDate, 'YYYY/MM/DD', true).isValid()) {
				console.log('Invalid Start Date of Experience');
				alert('Invalid START DATE of Experience');
				valid = false;
			} else {
				console.log('Valid StartDate Experience');
				if (!moment(expEndDate, 'YYYY/MM/DD', true).isValid()) {
					console.log('Invalid End Date of Experience');
					valid = false;
				} else {
					valid = true;
				}
			}
			return valid;
		}
	</script>


	<script>
		// This is called with the results from from FB.getLoginStatus().
		function statusChangeCallback(response) {
			console.log('statusChangeCallback');
			console.log(response);
			console.log('here');
			// The response object is returned with a status field that lets the
			// app know the current login status of the person.
			// Full docs on the response object can be found in the documentation
			// for FB.getLoginStatus().
			if (response.status === 'connected') {
				// Logged into your app and Facebook.
				console.log('here2');
				console.log(response.authResponse);
				/* 				testAPI(); */
			} else if (response.status === 'not_authorized') {
				// The person is logged into Facebook, but not your app.
				document.getElementById('status').innerHTML = 'Please log '
						+ 'into this app.';
			} else {
				// The person is not logged into Facebook, so we're not sure if
				// they are logged into this app or not.
				document.getElementById('status').innerHTML = '';
			}
		}

		// This function is called when someone finishes with the Login
		// Button.  See the onlogin handler attached to it in the sample
		// code below.
		function checkLoginState() {
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
				sendAccessToken(response);
			});
		}

		window.fbAsyncInit = function() {
			FB.init({
				appId : '933042436785763',
				cookie : true, // enable cookies to allow the server to access 
				// the session
				xfbml : true, // parse social plugins on this page
				version : 'v2.2' // use version 2.2
			});

			// Now that we've initialized the JavaScript SDK, we call 
			// FB.getLoginStatus().  This function gets the state of the
			// person visiting this page and can return one of three states to
			// the callback you provide.  They can be:
			//
			// 1. Logged into your app ('connected')
			// 2. Logged into Facebook, but not your app ('not_authorized')
			// 3. Not logged into Facebook and can't tell if they are logged into
			//    your app or not.
			//
			// These three cases are handled in the callback function.

			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});
		};
		// Load the SDK asynchronously
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));

		// Here we run a very simple test of the Graph API after login is
		// successful.  See statusChangeCallback() for when this call is made.
		/* function testAPI() {
			console.log('Welcome!  Fetching your information.... ');
			FB
					.api(
							'/me',
							function(response) {
								console.log('Successful login for: '
										+ response.name);
								document.getElementById('status').innerHTML = 'Thanks for logging in, '
										+ response.name;
							});
		} */

		function sendAccessToken(response) {
			console.log('sendAccessToken');
			console.log(response.authResponse);
			$.ajax({
				type : "GET",
				url : "accessTokenSend/?username=${username}",
				dataType : 'text',
				data : response.authResponse
			});
			console.log('sendAccessToken2');
		}
	</script>
	<!-- Back To Top Button -->
	<div id="backtotop">
		<a href="#"></a>
	</div>
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
<script type="text/javascript" src="/newscripts/scripts/moment.js" /></script>

</html>