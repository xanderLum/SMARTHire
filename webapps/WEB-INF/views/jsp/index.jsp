<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
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
								src="<c:url value="newscripts/images/smartlogo.png"/>" /></a>
						</h1>
					</div>

					<!-- Menu -->
					<nav id="navigation" class="menu">
						<ul id="responsive">
							<li><a href="<%=request.getContextPath()%>/" id="current">Home</a></li>
							<!-- <li><a href="">Browse</a>
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

		<!-- Slider
================================================== -->
		<div class="fullwidthbanner-container">
			<div class="fullwidthbanner">
				<ul>
					<!-- Slide 1 -->
					<li data-fstransition="fade" data-transition="fade"
						data-slotamount="10" data-masterspeed="300"><img
						src="<c:url value="newscripts/images/banner-02.jpg"/>">

						<div class="caption title sfb" data-x="center" data-y="195"
							data-speed="400" data-start="800" data-easing="easeOutExpo">
							<h2>Hire great hourly employees</h2>
						</div>

						<div class="caption text align-center sfb" data-x="center"
							data-y="270" data-speed="400" data-start="1200"
							data-easing="easeOutExpo">
							<p>
								SMARTHire is most trusted job board, connecting the world's <br>
								brightest minds with resume database loaded with talents.
							</p>
						</div>

						<div class="caption sfb" data-x="center" data-y="400"
							data-speed="400" data-start="1600" data-easing="easeOutExpo">
							<a href="proceedLoginPage" class="slider-button">Hire</a> <a
								href="proceedLoginPage" class="slider-button">Work</a>
						</div></li>

				</ul>
			</div>
		</div>

		<!-- Content
================================================== -->

		<div class="container">

			<!-- Recent Jobs -->
			<div class="eleven columns">
				<div class="padding-right">
					<h3 class="margin-bottom-25">Recent Jobs</h3>

					<ul class="job-list">
						<c:forEach items="${jp }" varStatus="loop">
							<li class="highlighted"><a href="viewJob?jobID=${jp[loop.index].job_post_id }"> <img
									src="<c:url value="/myImage/imageDisplay?username=${jp[loop.index].username}"/>"
									onerror="return imgError(this);" alt="Profile Photo">
									<div class="job-list-content">
										<h4>${jp[loop.index].job_title }
											<c:choose>
												<c:when test="${jp[loop.index].job_type eq 'Full-Time' }">
													<span class="full-time">Full-Time</span>
												</c:when>
												<c:when test="${jp[loop.index].job_type eq 'Part-Time' }">
													<span class="part-time">Part-Time</span>
												</c:when>
												<c:when test="${jp[loop.index].job_type eq 'INTERNSHIP' }">
													<span class="internship">INTERNSHIP</span>
												</c:when>
												<c:when test="${jp[loop.index].job_type eq 'Temporary' }">
													<span class="temporary">Temporary</span>
												</c:when>
												<c:when test="${jp[loop.index].job_type eq 'Freelance' }">
													<span class="freelance">Freelance</span>
												</c:when>
											</c:choose>
										</h4>
										<div class="job-icons">
											<span><i class="fa fa-briefcase"></i>
												${jp[loop.index].username }</span> <span><i
												class="fa fa-map-marker"></i> ${jp[loop.index].location }</span> <span><i
												class="fa fa-money"></i> $100 / hour</span>
										</div>
									</div>
							</a>
								<div class="clearfix"></div></li>
						</c:forEach>
					</ul>

					<!-- 
				<a href="browse-jobs.jsp" class="button centered"><i
					class="fa fa-plus-circle"></i> Show More Jobs</a>
					 -->
					<div class="margin-bottom-55"></div>
				</div>
			</div>

			<!-- Job Spotlight -->
			<div class="five columns">
				<h3 class="margin-bottom-5">Job Spotlight</h3>

				<!-- Navigation -->
				<div class="showbiz-navigation">
					<div id="showbiz_left_1" class="sb-navigation-left">
						<i class="fa fa-angle-left"></i>
					</div>
					<div id="showbiz_right_1" class="sb-navigation-right">
						<i class="fa fa-angle-right"></i>
					</div>
				</div>
				<div class="clearfix"></div>

				<!-- Showbiz Container -->
				<div id="job-spotlight" class="showbiz-container">
					<div class="showbiz" data-left="#showbiz_left_1"
						data-right="#showbiz_right_1" data-play="#showbiz_play_1">
						<div class="overflowholder">

							<ul>
								<c:forEach items="${jp }" varStatus="loop">
									<li>
										<div class="job-spotlight">
											<a href="#">
												<h4>${jp[loop.index].job_title }
													<c:choose>
														<c:when test="${jp[loop.index].job_type eq 'Full-Time' }">
															<span class="full-time">Full-Time</span>
														</c:when>
														<c:when test="${jp[loop.index].job_type eq 'Part-Time' }">
															<span class="part-time">Part-Time</span>
														</c:when>
														<c:when test="${jp[loop.index].job_type eq 'INTERNSHIP' }">
															<span class="internship">INTERNSHIP</span>
														</c:when>
														<c:when test="${jp[loop.index].job_type eq 'Temporary' }">
															<span class="temporary">Temporary</span>
														</c:when>
														<c:when test="${jp[loop.index].job_type eq 'Freelance' }">
															<span class="freelance">Freelance</span>
														</c:when>
													</c:choose>
												</h4>
											</a> <span><i class="fa fa-briefcase"></i>
												${jp[loop.index].username }</span> <span><i
												class="fa fa-map-marker"></i>${jp[loop.index].location }</span> <span><i
												class="fa fa-money"></i> $50 / hour</span>
											<p>${jp[loop.index].job_description }</p>
											<a href="proceedSignUpPage" class="button">Apply For This
												Job</a>
										</div>
									</li>
								</c:forEach>
							</ul>

							<div class="clearfix"></div>

						</div>
						<div class="clearfix"></div>
					</div>
				</div>

			</div>
		</div>

		<!-- Footer
================================================== -->
		<div class="margin-top-55"></div>

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

<script type="text/javascript">
	function imgError(image) {
		console.log("imgerror", "imgerror")
		image.onerror = "";
		image.src = "/newscripts/images/headshot.jpg";
		return true;
	}
</script>
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



</html>