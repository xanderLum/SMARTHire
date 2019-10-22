<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMARTHire Contact</title>

<script defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCNt8kl2xEcFMhE620rzqQ1x6n2QbwoXPY"
	type="text/javascript" /></script>


<link rel="icon" href="<c:url value="/img/favicon.png"/>"
	type="image/png" />
<link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon" />

<!-- Mobile Specific Metas
================================================== -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- CSS
================================================== -->
<link rel="stylesheet" href="<c:url value="newscripts/css/style.css" />">
<link rel="stylesheet"
	href="<c:url value="newscripts/css/colors/green.css"/>">
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
								src="<c:url value="/newscripts/images/smartlogo.png"/>" /></a>
						</h1>
					</div>

					<!-- Menu -->
					<nav id="navigation" class="menu">
						<ul id="responsive">
							<li><a href="<%=request.getContextPath()%>/" id="current">Home</a></li>
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
							<!-- 
				<li><a href="proceedSignUpPage"><i class="fa fa-user"></i>Sign Up</a></li>
				<li><a href="proceedLoginPage"><i class="fa fa-lock"></i> Log In</a></li>
				 -->
							<li><a href="proceedSignUpPage"><i class="fa fa-user"></i>Get
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
					<h2>Contact</h2>
					<nav id="breadcrumbs">
						<ul>
							<li>You are here:</li>
							<li><a href="#">Home</a></li>
							<li>Contact</li>
						</ul>
					</nav>
				</div>

			</div>
		</div>




		<!-- Content
================================================== -->
		<!-- Container -->
		<div class="container">
			<div class="sixteen columns">
				<h3 class="margin-bottom-20">Our Office</h3>
				<!-- Google Maps -->
				<section class="google-map-container">
					<div id="googlemaps" class="google-map google-map-full"></div>
				</section>
				<!-- Google Maps / End -->

			</div>
		</div>
		<!-- Container / End -->


		<!-- Container -->
		<div class="container">

			<div class="eleven columns">

				<h3 class="margin-bottom-15">Contact Form</h3>

				<!-- Contact Form -->
				<section id="contact" class="padding-right">
					<!-- Success Message -->
					<mark id="message">${message }</mark>

					<!-- Form -->
					<fieldset>
						<form method="post" action="sendMessage">
							<div>
								<label>Name:</label> <input name="name" type="text" id="name" />
								<label>Email: <span>*</span></label> <input name="email"
									type="email" id="email"
									pattern="^[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})$" />
								<label>Message: <span>*</span></label>
								<textarea name="comment" cols="40" rows="3" id="comment"
									spellcheck="true"></textarea>
							</div>
							<input type="hidden" name="userFeedBack" value="" /> <input
								type="submit" class="submit" value="Send Message" />
						</form>
					</fieldset>
					<div id="result"></div>
					<div class="clearfix"></div>
					<div class="margin-bottom-40"></div>

				</section>
				<!-- Contact Form / End -->

			</div>
			<!-- Container / End -->


			<!-- Sidebar
================================================== -->
			<div class="five columns">

				<!-- Information -->
				<h3 class="margin-bottom-10">Information</h3>
				<div class="widget-box">
					<p>
						SMARTHire is most trusted job board, connecting the world's <br>
						brightest minds with resume database loaded with talents.
					</p>

					<ul class="contact-informations">
						<li>Countryside Village, Apt. 303</li>
						<li>Cebu, PH 6046</li>
					</ul>

					<ul class="contact-informations second">
						<li><i class="fa fa-phone"></i>
							<p>+032 490 0168</p></li>
						<li><i class="fa fa-envelope"></i>
							<p>
								<a class="__cf_email__" href="/cdn-cgi/l/email-protection"
									data-cfemail="2548444c4965405d44485549400b464a48">ai.smarthire@gmail.com</a>
								<script data-cfhash='f9e31' type="text/javascript">
									/* <![CDATA[ */!function(t, e, r, n, c, a,
											p) {
										try {
											t = document.currentScript
													|| function() {
														for (
																t = document
																		.getElementsByTagName('script'),
																e = t.length; e--;)
															if (t[e]
																	.getAttribute('data-cfhash'))
																return t[e]
													}();
											if (t && (c = t.previousSibling)) {
												p = t.parentNode;
												if (a = c
														.getAttribute('data-cfemail')) {
													for (e = '', r = '0x'
															+ a.substr(0, 2)
															| 0, n = 2; a.length
															- n; n += 2)
														e += '%'
																+ ('0' + ('0x'
																		+ a
																				.substr(
																						n,
																						2) ^ r)
																		.toString(16))
																		.slice(-2);
													p
															.replaceChild(
																	document
																			.createTextNode(decodeURIComponent(e)),
																	c)
												}
												p.removeChild(t)
											}
										} catch (u) {
										}
									}
											()
											/* ]]> */
								</script>
							</p></li>
						<li><i class="fa fa-globe"></i>
							<p>www.smarthire.com</p></li>
					</ul>

				</div>

				<!-- Social -->
				<div class="widget margin-top-30">
					<h3 class="margin-bottom-5">Social Media</h3>
					<ul class="social-icons">
						<li><a class="facebook" href="#"><i class="icon-facebook"></i></a></li>
						<li><a class="twitter" href="#"><i class="icon-twitter"></i></a></li>
						<li><a class="gplus" href="#"><i class="icon-gplus"></i></a></li>
						<li><a class="linkedin" href="#"><i class="icon-linkedin"></i></a></li>
					</ul>
					<div class="clearfix"></div>
					<div class="margin-bottom-50"></div>
				</div>

			</div>
		</div>
		<!-- Container / End -->

		<!-- Footer
================================================== -->
		<div class="margin-top-20"></div>

		<div id="footer">
			<!-- Main -->
			<div class="container">

				<div class="seven columns">
					<h4>About</h4>
					<p>
						SMARTHire is most trusted job board, connecting the world's <br>
						brightest minds with resume database loaded with talents.
					</p>
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
							<li><a class="facebook"
								href="https://www.facebook.com/SMARTHire-269685486764592/"><i
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


<script src="<c:url value="newscripts/scripts/jquery.gmaps.min.js"/>"></script>
	<script type="text/javascript">
		(
						function($) {
							$(document)
									.ready(
											function() {
												$('#googlemaps')
														.gMap(
																{
																	maptype : 'ROADMAP',
																	scrollwheel : false,
																	zoom : 13,
																	markers : [ {
																		//address : 'New York, 45 Park Avenue', // Your Adress Here
																		address : ' Cebu, Philippines, Countryside Village, Minglanilla',
																		//html : '<strong>Our Office</strong><br>45 Park Avenue, Apt. 303 </br>New York, NY 10016 ',
																		html : '<strong>Our Office</strong><br>Countryside Village, Minglanilla</br>Cebu, Ph 6046',
																		popup : true,
																	} ],
																});
											});
						})(this.jQuery);
	</script>
	
	

</body>
</html>