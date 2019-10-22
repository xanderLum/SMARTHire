<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMARTHire</title>

<link rel="icon" href="<c:url value="/img/favicon.png"/>"
	type="image/png" />
<link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon" />

<!-- Mobile Specific Metas
================================================== -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS
================================================== -->
<link rel="stylesheet" href="<c:url value="newscripts/css/style.css"/>">
<link rel="stylesheet"
	href="<c:url value="newscripts/css/colors/green.css" />">

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
	<div id="wrapper">


		<!-- Header
================================================== -->
		<header class="sticky-header">
		<div class="container">
			<div class="sixteen columns">

				<!-- Logo -->
				<div id="logo">
					<h1>
						<a href="<%=request.getContextPath()%>/"><img
							src="<c:url value="/newscripts/images/smartlogo.png" />" /></a>
					</h1>
				</div>

				<!-- Menu -->
				<nav id="navigation" class="menu">
				<ul id="responsive">
					<li><a href="">Home</a></li>
					<!-- 
					<li><a href="">Browse</a>
						<ul>
							<li><a href="js.browse-jobs.jsp">Jobs</a></li>
							<li><a href="browse-categories.jsp">Categories</a></li>
						</ul></li>
					-->
					<li><a href="contact">Contact</a></li>
					<li><a href="blog">Blog</a></li>
				</ul>

				<ul class="responsive float-right">
					<li><a href="proceedLoginPage"><i class="fa fa-user"></i>Get
							Started</a></li>
				</ul>
				</nav>

				<!-- Navigation -->
				<div id="mobile-navigation">
					<a href="#menu" class="menu-trigger"><i class="fa fa-reorder"></i>
						Menu</a>
				</div>

			</div>
		</div>
		</header>
		<div class="clearfix"></div>


		<!-- Titlebar
================================================== -->
		<div id="titlebar" class="single">
			<div class="container">

				<div class="sixteen columns">
					<h2>My Account</h2>
					<nav id="breadcrumbs">
					<ul>
						<li>You are here:</li>
						<li><a href="<%=request.getContextPath()%>/">Home</a></li>
						<li>My Account</li>
					</ul>
					</nav>
				</div>

			</div>
		</div>


		<!-- Content
================================================== -->

		<!-- Container -->
		<div class="container">
			<div class="my-account">
				<ul class="tabs-nav">
					<li><a href="#tab1"><i class="fa fa-lock"></i> Login</a></li>
					<li><a href="#tab2"><i class="fa fa-user"></i> Register</a></li>
				</ul>

				<div class="tabs-container">
					<!-- Login -->
					<div class="tab-content" id="tab1" style="display: none;">
						<form method="post" class="login" action="mainLogin">

							<p class="form-row form-row-wide">
								<label for="username">Username: <i
									class="ln ln-icon-Male"></i> <input type="text"
									class="input-text" name="username" id="username" required />
								</label>
							</p>

							<p class="form-row form-row-wide">
								<label for="loginAs">You are logging in to... <i
									class="ln ln-icon-Male"></i> <select id="loginAs"
									name="loginAs" class="chosen-select">
										<option value="to Hire"><i class="ln ln-icon-Male"></i>
											to Hire
										</option>
										<option value="to Work"><i class="ln ln-icon-Male"></i>
											to Work
										</option>
								</select>
								</label>
							</p>

							<p class="form-row form-row-wide">
								<label for="password">Password: <i
									class="ln ln-icon-Lock-2"></i> <input class="input-text"
									type="password" name="password" id="password" required />
								</label>
							</p>

							<p class="form-row">
								<input type="submit" class="button border fw margin-top-10"
									name="login" value="Login" /> <label for="rememberme"
									class="rememberme"> <input name="rememberme"
									type="checkbox" id="rememberme" value="forever" /> Remember Me
								</label>
							</p>

							<p class="lost_password">
								<a href="#">Lost Your Password?</a>
							</p>

						</form>
					</div>

					<!-- Register -->
					<div class="tab-content" id="tab2" style="display: none;">
						<form method="post" onsubmit="return confirmPasswordFxn();"
							class="register" action="mainSignUp">
							<p class="form-row form-row-wide">
								<label for="username2">Username: <i
									class="ln ln-icon-Male"></i> <input type="text"
									class="input-text" name="username2" id="username2" required />
								</label>
							</p>

							<p class="form-row form-row-wide">
								<label for="email2">Email Address: <i
									class="ln ln-icon-Mail"></i> <input type="text"
									class="input-text" name="email2" id="email2" required />
								</label>
							</p>

							<p class="form-row form-row-wide">
								<label for="signUpAs">You are looking to...<i
									class="ln ln-icon-Male"></i> <select id="signUpAs"
									name="signUpAs" class="chosen-select">
										<option value="to Hire"><i class="ln ln-icon-Male"></i>
											to Hire
										</option>
										<option value="to Work"><i class="ln ln-icon-Male"></i>
											to Work
										</option>
								</select>
								</label>
							</p>

							<p class="form-row form-row-wide">
								<label for="password1">Password: <i
									class="ln ln-icon-Lock-2"></i> <input class="input-text"
									type="password" name="password1" id="password1" required />
								</label>
							</p>

							<p class="form-row form-row-wide">
								<label for="password2">Repeat Password: <i
									class="ln ln-icon-Lock-2"></i> <input class="input-text"
									type="password" name="password2" id="password2" required />
								</label>
							</p>

							<p class="form-row">
								<input type="submit" class="button border fw margin-top-10"
									name="register" value="Register" />
							</p>

						</form>
					</div>
				</div>
			</div>

			<div id="message">
				<h4>
					<c:out value="${msg}" />
				</h4>
			</div>
		</div>



		<!-- Footer
================================================== -->
		<div class="margin-top-30"></div>

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
							© Copyright 2016 by <a href="#">SMARTHire Global Inc.</a>. All
							Rights Reserved.
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


</body>
<!-- Scripts
================================================== -->
<script src="<c:url value="newscripts/scripts/jquery-2.1.3.min.js"/>"></script>
<script src="<c:url value="newscripts/scripts/custom.js"/>"></script>
<script src="<c:url value="newscripts/scripts/jquery.superfish.js"/>"></script>
<script
	src="<c:url value="newscripts/scripts/jquery.themepunch.tools.min.js"/>"></script>
<script
	src="<c:url value="newscripts/scripts/jquery.themepunch.revolution.min.js"/>"></script>
<script
	src="<c:url value="newscripts/scripts/jquery.themepunch.showbizpro.min.js"/>"></script>
<script
	src="<c:url value="newscripts/scripts/jquery.flexslider-min.js"/>"></script>
<script src="<c:url value="newscripts/scripts/chosen.jquery.min.js"/>"></script>
<script
	src="<c:url value="newscripts/scripts/jquery.magnific-popup.min.js"/>"></script>
<script src="<c:url value="newscripts/scripts/waypoints.min.js"/>"></script>
<script
	src="<c:url value="newscripts/scripts/jquery.counterup.min.js"/>"></script>
<script src="<c:url value="newscripts/scripts/jquery.jpanelmenu.js"/>"></script>
<script src="<c:url value="newscripts/scripts/stacktable.js"/>"></script>
<script src="<c:url value="newscripts/scripts/headroom.min.js"/>"></script>


<script>
	function confirmPasswordFxn() {
		var pass1 = document.getElementById('password1').value;
		var pass2 = document.getElementById('password2').value;
		var match = true;
		if (pass1 != pass2) {
			//alert("Passwords Do not match");
			match = false;
			alert("Passwords NOT MATCH!!!");
		} else {
			return match;
		}
		return match;
	}
</script>

</html>