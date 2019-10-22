<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMARTHire</title>

<!-- Mobile Specific Metas
================================================== -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS
================================================== -->
<link rel="stylesheet" href="<c:url value="newscripts/css/style.css"/> ">
<link rel="stylesheet"
	href="<c:url value="newscripts/css/colors/green.css" /> ">
	
<link rel="stylesheet" type="text/css"
	href="<c:url value="/newscripts/css/font-awesome.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/newscripts/css/responsive.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/newscripts/css/base.css"/>">


<link class="jsbin"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
	
</head>
<body>
	<div id="wrapper">

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
			<!-- Submit Page -->
			<!-- <form method="post" action="profile/">  -->
				<div class="sixteen columns">
					<div class="submit-page">

						<!-- Linked In -->
						<div class="form">
							<h5>LinkedIn</h5>
							<a href="#" class="button linkedin-btn" data-toggle="modal"
								data-target="modalUrlLinkedIn">Import from LinkedIn</a>
						</div>

						<!-- Adding URL(s) -->
						<div class="form boxed box-to-clone url-box" id="modalUrlLinkedIn"
							tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
							aria-hidden="true" style="display: none;">
							<a href="#" class="close-form remove-box button"><i
								class="fa fa-close"></i></a> <input class="search-field"
								name="linkedInURL" type="text"
								placeholder="LinkedIn Public Profile URL" value="" />
						</div>

						<!-- Upwork -->
						<div class="form">
							<h5>Upwork</h5>
							<a href="#" class="button upwork-btn" data-toggle="modal"
								data-target="modalUrlUpwork">Import from Upwork</a>
						</div>
						<!-- Adding URL(s) -->
						<div class="form boxed box-to-clone url-box" id="modalUrlUpwork"
							tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
							aria-hidden="true" style="display: none;">
							<a href="#" class="close-form remove-box button"><i
								class="fa fa-close"></i></a> <input class="search-field"
								name="upworkURL" type="text"
								placeholder="Upwork Public Profile URL" value="" />
						</div>


						<!-- Facebook -->
						<div class="form">
							<h5>Facebook</h5>
							<a href="#" class="button facebook-btn" data-toggle="modal"
								data-target="modalFB">Import from Facebook</a>

						</div>
						<!-- MODAL FB -->
						<div class="form boxed box-to-clone url-box" id="modalFB"
							tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
							aria-hidden="true" style="display: none;">
							<a href="#" class="close-form remove-box button"><i
								class="fa fa-close"></i></a>
							<fb:login-button scope="user_posts" onlogin="checkLoginState();"></fb:login-button>
						</div>

						<!-- Twitter -->
						<div class="form">
							<h5>Twitter</h5>
							<a href="#" class="button twitter-btn">Import from Twitter</a>
						</div>
						<!-- Adding URL(s) -->
						<div class="form boxed box-to-clone url-box" id="modalUrlTwitter"
							tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
							aria-hidden="true" style="display: none;">
							<a href="#" class="close-form remove-box button"><i
								class="fa fa-close"></i></a> <input class="search-field"
								name="twitterUsername" type="text"
								placeholder="twitter username" value="" />
						</div>

						<!-- Full Name -->
						<div class="form">
							<h5>Your Name</h5>
							<input class="search-field" type="text"
								placeholder="Your full name" value="" />
						</div>

						<!-- Email -->
						<div class="form">
							<h5>Your Email</h5>
							<input class="search-field" type="text"
								placeholder="mail@example.com" value="" />
						</div>

						<!-- Title -->
						<div class="form">
							<h5>Professional Title</h5>
							<input class="search-field" type="text"
								placeholder="e.g. Web Developer" value="" />
						</div>

						<!-- Location -->
						<div class="form">
							<h5>Location</h5>
							<input class="search-field" type="text"
								placeholder="e.g. London, UK" value="" />
						</div>

						<!-- Logo -->
						<div class="form">
							<h5>
								Photo <span>(optional)</span>
							</h5>
							<label class="upload-btn"> <input type="file"
								name="image" size="50" enctype="multipart/form-data" /> <i
								class="fa fa-upload"></i> Browse
							</label> <!-- <span class="fake-input">No file selected</span> -->
						</div>

						<!-- Email -->
						<div class="form">
							<h5>
								Video <span>(optional)</span>
							</h5>
							<input class="search-field" type="text"
								placeholder="A link to a video about you" value="" />
						</div>

						<!-- Description -->
						<div class="form">
							<h5>Profile Content</h5>
							<textarea class="WYSIWYG" name="summary" cols="40" rows="3"
								id="summary" spellcheck="true"></textarea>
						</div>

						<!-- Education -->
						<div class="form with-line">
							<h5>
								Education <span>(optional)</span>
							</h5>
							<div class="form-inside">

								<!-- Add Education -->
								<div class="form boxed box-to-clone education-box">
									<a href="#" class="close-form remove-box button"><i
										class="fa fa-close"></i></a> <input class="search-field"
										type="text" placeholder="School Name" value="" /> <input
										class="search-field" type="text"
										placeholder="Qualification(s)" value="" /> <input
										class="search-field" type="text"
										placeholder="Start / end date" value="" />
									<textarea name="desc" id="desc" cols="30" rows="10"
										placeholder="Notes (optional)"></textarea>
								</div>

								<a href="#" class="button gray add-education add-box"><i
									class="fa fa-plus-circle"></i> Add Education</a>
							</div>
						</div>


						<!-- Experience  -->
						<div class="form with-line">
							<h5>
								Experience <span>(optional)</span>
							</h5>
							<div class="form-inside">

								<!-- Add Experience -->
								<div class="form boxed box-to-clone experience-box">
									<a href="#" class="close-form remove-box button"><i
										class="fa fa-close"></i></a> <input class="search-field"
										type="text" placeholder="Employer" value="" /> <input
										class="search-field" type="text" placeholder="Job Title"
										value="" /> <input class="search-field" type="text"
										placeholder="Start / end date" value="" />
									<textarea name="desc1" id="desc1" cols="30" rows="10"
										placeholder="Notes (optional)"></textarea>
								</div>

								<a href="#" class="button gray add-experience add-box"><i
									class="fa fa-plus-circle"></i> Add Experience</a>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>


		<!-- Footer
================================================== -->
		<div class="margin-top-60"></div>

		<div id="footer">
			<!-- Main -->
			<div class="container">

				<div class="seven columns">
					<h4>About</h4>
					<p>Morbi convallis bibendum urna ut viverra. Maecenas quis
						consequat libero, a feugiat eros. Nunc ut lacinia tortor morbi
						ultricies laoreet ullamcorper phasellus semper.</p>
					<a href="#" class="button">Get Started</a>
				</div>

				<div class="three columns">
					<h4>Company</h4>
					<ul class="footer-links">
						<li><a href="#">About Us</a></li>
						<li><a href="#">Careers</a></li>
						<li><a href="#">Our Blog</a></li>
						<li><a href="#">Terms of Service</a></li>
						<li><a href="#">Privacy Policy</a></li>
						<li><a href="#">Hiring Hub</a></li>
					</ul>
				</div>

				<div class="three columns">
					<h4>Press</h4>
					<ul class="footer-links">
						<li><a href="#">In the News</a></li>
						<li><a href="#">Press Releases</a></li>
						<li><a href="#">Awards</a></li>
						<li><a href="#">Testimonials</a></li>
						<li><a href="#">Timeline</a></li>
					</ul>
				</div>

				<div class="three columns">
					<h4>Browse</h4>
					<ul class="footer-links">
						<li><a href="#">Freelancers by Category</a></li>
						<li><a href="#">Freelancers in USA</a></li>
						<li><a href="#">Freelancers in UK</a></li>
						<li><a href="#">Freelancers in Canada</a></li>
						<li><a href="#">Freelancers in Australia</a></li>
						<li><a href="#">Find Jobs</a></li>

					</ul>
				</div>

			</div>

			<!-- Bottom -->
			<div class="container">
				<div class="footer-bottom">
					<div class="sixteen columns">
						<h4>Follow Us</h4>
						<ul class="social-icons">
							<li><a class="facebook" href="#"><i
									class="icon-facebook"></i></a></li>
							<li><a class="twitter" href="#"><i class="icon-twitter"></i></a></li>
							<li><a class="gplus" href="#"><i class="icon-gplus"></i></a></li>
							<li><a class="linkedin" href="#"><i
									class="icon-linkedin"></i></a></li>
						</ul>
						<div class="copyrights">
							© Copyright 2016 by <a href="#">SMARTHire Global Inc.</a>. All Rights
							Reserved.
						</div>
					</div>
				</div>
			</div>

		</div>

		<!-- Back To Top Button -->
		<div id="backtotop">
			<a href="#"></a>
		</div>

	</div>
	<!-- Wrapper / End -->


	<!-- Access Tokens or Authentication follows -->

	<!-- Facebook Get AccessToken -->
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
	<!-- Scripts
================================================== -->
	<script src="<c:url value="newscripts/scripts/jquery-2.1.3.min.js" /> "></script>
	<script src="<c:url value="newscripts/scripts/custom.js" /> "></script>
	<script src="<c:url value="newscripts/scripts/jquery.superfish.js" /> "></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.themepunch.tools.min.js" /> "></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.themepunch.revolution.min.js" /> "></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.themepunch.showbizpro.min.js" /> "></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.flexslider-min.js" /> "></script>
	<script
		src="<c:url value="newscripts/scripts/chosen.jquery.min.js" /> "></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.magnific-popup.min.js" /> "></script>
	<script src="<c:url value="newscripts/scripts/waypoints.min.js" /> "></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.counterup.min.js" /> "></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.jpanelmenu.js" /> "></script>
	<script src="<c:url value="newscripts/scripts/stacktable.js" /> "></script>
	<script src="<c:url value="newscripts/scripts/headroom.min.js" /> "></script>


	<!-- WYSIWYG Editor -->
	<script type="text/javascript"
		src="<c:url value="newscripts/scripts/jquery.sceditor.bbcode.min.js" /> "></script>
	<script type="text/javascript"
		src="<c:url value="newscripts/scripts/jquery.sceditor.js" /> "></script>




</body>
</html>