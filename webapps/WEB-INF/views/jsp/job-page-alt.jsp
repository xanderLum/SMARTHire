<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMARTHire</title>
<link rel="icon" href="<c:url value="/img/favicon.png" />"
	type="image/png">
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
							src="<c:url value="newscripts/images/smartlogo.png"/>" /></a>
					</h1>
				</div>

				<!-- Menu -->
				<nav id="navigation" class="menu">
				<ul id="responsive">

					<li><a href="<%=request.getContextPath()%>/">Home</a></li>

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

		<div id="titlebar" class="photo-bg"
			style="background: url(<c:url value="/myImage/imageDisplay?username=${emp.username}"/>">
			<div class="container">
				<div class="ten columns">
					<span>${emp.company_name }</span>
					<h2>${jp.job_title }
						<c:choose>
							<c:when test="${jp.job_type eq 'Full-Time' }">
								<span class="full-time">Full-Time</span>
							</c:when>
							<c:when test="${jp.job_type eq 'Part-Time' }">
								<span class="part-time">Part-Time</span>
							</c:when>
							<c:when test="${jp.job_type eq 'INTERNSHIP' }">
								<span class="internship">INTERNSHIP</span>
							</c:when>
							<c:when test="${jp.job_type eq 'Temporary' }">
								<span class="temporary">Temporary</span>
							</c:when>
							<c:when test="${jp.job_type eq 'Freelance' }">
								<span class="freelance">Freelance</span>
							</c:when>
						</c:choose>
					</h2>
				</div>
				<!-- 
				<div class="six columns">
					<a href="#" class="button white"><i class="fa fa-star"></i>
						Bookmark This Job</a>
				</div>
				-->
			</div>
		</div>


		<!-- Content
================================================== -->
		<div class="container">

			<!-- Recent Jobs -->
			<div class="eleven columns">
				<div class="padding-right">

					<!-- Company Info -->
					<div class="company-info">
						<img
							src="<c:url value="/myImage/imageDisplay?username=${jp.username}"/>" />
						<div class="content">
							<h4>${emp.company_name }</h4>
							<span><a href="#"><i class="fa fa-link"></i>${emp.website }</a></span>
							<span><a href="#"><i class="fa fa-twitter"></i>
									@${emp.username }</a></span>
						</div>
						<div class="clearfix"></div>
					</div>

					<p class="margin-reset">The ${jp.job_title } ensures
						outstanding customer service is provided to all customers and
						that all service offerings meet the required stock levels and
						presentation standards. Beginning your career as a ${jp.job_title }
						will give you a strong foundation in ${emp.company_name } that
						can make you a vital member of the front line team!</p>

					<br>
					<p>
						The <strong>${jp.job_title }</strong> will have responsibilities
						that include:
					</p>

					<ul class="list-1">
						<li>${jp.job_description }</li>
					</ul>

					<br>

					<h4 class="margin-bottom-10">Job Skills Requirement</h4>

					<ul class="list-1">
						<c:forEach items="${skillsReq }" varStatus="loop">
							<li>${skillsReq[loop.index].skill_name }</li>
						</c:forEach>
					</ul>

				</div>
			</div>


			<!-- Widgets -->
			<div class="five columns">

				<!-- Sort by -->
				<div class="widget">
					<h4>Overview</h4>

					<div class="job-overview">

						<ul>
							<li><i class="fa fa-map-marker"></i>
								<div>
									<strong>Location:</strong> <span>
									${jp.location }</span>
								</div></li>
							<li><i class="fa fa-user"></i>
								<div>
									<strong>Job Title:</strong> <span>${jp.job_title }</span>
								</div></li>
							<li><i class="fa fa-clock-o"></i>
								<div>
									<strong>Hours:</strong> <span>40h / week</span>
								</div></li>
							<li><i class="fa fa-money"></i>
								<div>
									<strong>Rate:</strong> <span>$9.50 - $12.50 / hour</span>
								</div></li>
						</ul>


						<a href="<%=request.getContextPath()%>/proceedLoginPage" class="btn btn-primary">Apply
							For This Job</a>

						<div id="small-dialog"
							class="zoom-anim-dialog mfp-hide apply-popup">
							<div class="small-dialog-headline">
								<h2>Apply For This Job</h2>
							</div>

							<div class="small-dialog-content">
								<form action="proceedLoginPage" method="get">
									<input type="text" placeholder="Full Name" value="" /> <input
										type="text" placeholder="Email Address" value="" />
									<textarea
										placeholder="Your message / cover letter sent to the employer"></textarea>

									<!-- Upload CV -->
									<div class="upload-info">
										<strong>Upload your CV (optional)</strong> <span>Max.
											file size: 5MB</span>
									</div>
									<div class="clearfix"></div>

									<label class="upload-btn"> <input type="file" multiple />
										<i class="fa fa-upload"></i> Browse
									</label> <span class="fake-input">No file selected</span>

									<div class="divider"></div>

									<button class="send">Send Application</button>
								</form>
							</div>

						</div>

					</div>

				</div>

			</div>
			<!-- Widgets / End -->


		</div>


		<!-- Footer
================================================== -->
		<div class="margin-top-50"></div>

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
</body>
</html>