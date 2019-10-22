<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employer Dashboard</title>
<link rel="icon" href="<c:url value="/img/favicon.png"/>"
	type="image/png" />
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

<!-- Scripts
================================================== -->
<!-- 
<script type="text/javascript" src="<c:url value="/newscripts/scripts/jquery-2.1.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/newscripts/scripts/custom.js"/>"></script>
<script type="text/javascript" src="<c:url value="/newscripts/scripts/jquery.superfish.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/newscripts/scripts/jquery.themepunch.tools.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/newscripts/scripts/jquery.themepunch.revolution.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/newscripts/scripts/jquery.themepunch.showbizpro.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/newscripts/scripts/jquery.flexslider-min.js"/>"></script>
<script src="<c:url value="/newscripts/scripts/chosen.jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/newscripts/scripts/jquery.magnific-popup.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/newscripts/scripts/waypoints.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/newscripts/scripts/jquery.counterup.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/newscripts/scripts/jquery.jpanelmenu.js"/>"></script>
<script type="text/javascript" src="<c:url value="/newscripts/scripts/stacktable.js"/>"></script>
<script type="text/javascript" src="<c:url value="/newscripts/scripts/headroom.min.js"/>"></script>
 -->

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
	<div id="wrapper">
		<!-- Header
================================================== -->
		<header class="sticky-header">
		<div class="container">
			<div class="sixteen columns">
				<!-- Logo -->
				<div id="logo">
					<h1>
						<a href="<%=request.getContextPath()%>/employer/home"><img
							src="<c:url value="/newscripts/images/smartlogo.png"/>" /></a>
					</h1>
				</div>
				<!-- Menu -->
				<nav id="navigation" class="menu">
				<ul id="responsive">
					<li><a href="<%=request.getContextPath()%>/employer/home"
						id="current">Home</a></li>
					<li><a href="#" id="current">Menu</a>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/employer/contentCreateJobPosts">Post
									Job</a></li>
							<li><a
								href="<%=request.getContextPath()%>/employer/contentViewJobPosts">Manage
									Jobs</a></li>
							<!-- <li><a href="<%=request.getContextPath()%>/employer/browse-profiles">Browse Profiles</a></li> -->
						</ul></li>
				</ul>
				<ul class="responsive float-right">
					<!-- <li><a href="contact">Contact</a></li>
					<li><a href="blog">Blog</a></li> -->
					<li><a
						href="<%=request.getContextPath()%>/employer/editAccountPage">Account<br>(${username})</a></li>
					<li><a href="<%=request.getContextPath()%>/employer/logout">Logout</a></li>
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
		<!-- Content
================================================== -->
		<c:choose>
			<c:when test="${empty state || state eq 'contentViewJobPosts'}">
				<jsp:include page="emp.manage-jobs.jsp" />
			</c:when>
			<c:when test="${state eq 'viewJob'}">
				<jsp:include page="emp.manage-applications.jsp" />
			</c:when>
			<c:when test="${state eq 'contentCreateJobPosts'}">
				<jsp:include page="emp.add-job.jsp" />
			</c:when>

			<c:when test="${state eq 'categories'}">
				<jsp:include page="browse-categories.jsp" />
			</c:when>
			<c:when test="${state eq 'editAccount'}">
				<jsp:include page="emp.editAccount.jsp" />
			</c:when>
			<c:when test="${state eq 'ranking'}">
				<jsp:include page="emp.manage-applications.jsp" />
			</c:when>
			<c:when test="${state eq 'viewJSprofile' }">
				<jsp:include page="emp.viewJSprofile.jsp" />
			</c:when>

		</c:choose>
		
		<!-- Footer
================================================== -->
		<div class="margin-top-60"></div>

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


</html>