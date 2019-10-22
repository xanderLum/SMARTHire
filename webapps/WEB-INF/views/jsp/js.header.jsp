<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<link rel="stylesheet"
	href="<c:url value="newscripts/css/style.css" /> ">
<link rel="stylesheet"
	href="<c:url value="newscripts/css/colors/green.css"/>">
</head>
<body>
<!-- Header
================================================== -->
	<header class="sticky-header">
	<div class="container">
		<div class="sixteen columns">
			<!-- Logo -->
			<div id="logo">
				<h1>
					<a href="index.jsp"><img
						src="<c:url value="newscripts/images/smartlogo.png"/>" /></a>
				</h1>
			</div>

			<!-- Menu -->
			<nav id="navigation" class="menu">
			<ul id="responsive">
				<li><a href="" id="current">Home</a></li>
				<li><a href="#" id="current">Menu</a>
					<ul>
						<li><a href="js.browse-jobs.jsp">Browse Jobs</a></li>
						<li><a href="browse-categories.jsp">Browse Categories</a></li>
						<li><a href="js.profile.jsp">Profile</a></li>
						<li><a href="js.job-alerts.jsp">Job Alerts</a></li>
					</ul>
				</li>
				
			</ul>
			<ul class="responsive float-right">
				<li><a href="contact.jsp">Contact</a></li>
				<li><a href="blog.jsp">Blog</a></li>
				<li><a href="logout/">Logout</a></li>
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

	<!-- header modals -->

	<div class="clearfix"></div>


</body>
</html>