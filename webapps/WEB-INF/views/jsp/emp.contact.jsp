<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact Us</title>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCH7QRCSEm8bt6U0ZNYF47gYyVaWccdtcM&callback=initMap"
  type="text/javascript"></script>
<script src="<c:url value="http://maps.google.com/maps/api/js?sensor=true"/>"></script>
<script src="<c:url value="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"/>"></script>

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
<link rel="stylesheet" type="text/css" href="<c:url value="/css/animate.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/responsive.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />">	
</head>
<body>
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
						<form method="post" action="sendMessageEmp">
							<div>
								<label>Name:</label> <input name="name" type="text" id="name" />
								<label>Email: <span>*</span></label> 
									<input name="email"	type="email" id="email"
									pattern="^[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})$" />
								<label>Message: <span>*</span></label>
								<textarea name="comment" cols="40" rows="3" id="comment"
									spellcheck="true"></textarea>
							</div>
							<input type="hidden" name="userFeedBack" value=""/>
							<input type="submit" class="submit" value="Send Message" />
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
					<p>SMARTHire is most trusted job board, connecting the world's <br>
							brightest minds with resume database loaded with talents.</p>

					<ul class="contact-informations">
						<li>45 Park Avenue, Apt. 303</li>
						<li>New York, NY 10016</li>
					</ul>

					<ul class="contact-informations second">
						<li><i class="fa fa-phone"></i>
							<p>+48 880 440 110</p></li>
						<li><i class="fa fa-envelope"></i>
							<p>
								<a class="__cf_email__" href="/cdn-cgi/l/email-protection"
									data-cfemail="2548444c4965405d44485549400b464a48">[email&#160;protected]</a>
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

</body>
</html>