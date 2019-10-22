<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMARTHire</title>

<!-- Mobile Specific Metas
================================================== -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS
================================================== -->
<link rel="stylesheet" href="<c:url value="newscripts/css/style.css"/>">
<link rel="stylesheet" href="<c:url value="newscripts/css/colors/green.css"/>">
</head>
<body>
<!-- Titlebar
================================================== -->
<div id="titlebar" class="single">
	<div class="container">

		<div class="sixteen columns">
			<h2>Manage Resumes</h2>
			<nav id="breadcrumbs">
				<ul>
					<li>You are here:</li>
					<li><a href="#">Home</a></li>
					<li>Candidate Dashboard</li>
				</ul>
			</nav>
		</div>

	</div>
</div>


<!-- Content
================================================== -->
<div class="container">
	
	<!-- Table -->
	<div class="sixteen columns">

		<p class="margin-bottom-25">Your resume can be viewed, edited or removed below.</p>

		<table class="manage-table resumes responsive-table">

			<tr>
				<th><i class="fa fa-user"></i> Name</th>
				<th><i class="fa fa-file-text"></i> Title</th>
				<th><i class="fa fa-map-marker"></i> Location</th>
				<th><i class="fa fa-calendar"></i> Date Posted</th>
				<th></th>
			</tr>

			<!-- Item #1 -->
			<tr>
				<td class="title"><a href="#">John Doe</a></td>
				<td>Front End Web Developer</td>
				<td>New York</td>
				<td>September 30, 2015</td>
				<td class="action">
					<a href="#"><i class="fa fa-pencil"></i> Edit</a>
					<a href="#"><i class="fa  fa-eye-slash"></i> Hide</a>
					<a href="#" class="delete"><i class="fa fa-remove"></i> Delete</a>
				</td>
			</tr>

			<!-- Item #1 -->
			<tr>
				<td class="title"><a href="#">John Doe</a></td>
				<td>Logo Designer</td>
				<td>New York</td>
				<td>August 12, 2015</td>
				<td class="action">
					<a href="#"><i class="fa fa-pencil"></i> Edit</a>
					<a href="#"><i class="fa  fa-eye-slash"></i> Hide</a>
					<a href="#" class="delete"><i class="fa fa-remove"></i> Delete</a>
				</td>
			</tr>	

		</table>

		<br>

		<a href="#" class="button">Add Resume</a>

	</div>

</div>


<!-- Scripts
================================================== -->
<script type="text/javascript" src="/newscripts/scripts/jquery-2.1.3.min.js"/></script>
<script type="text/javascript" src="/newscripts/scripts/custom.js"/></script>
<script type="text/javascript" src="/newscripts/scripts/jquery.superfish.js"/></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.themepunch.tools.min.js"/></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.themepunch.revolution.min.js"/></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.themepunch.showbizpro.min.js"/></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.flexslider-min.js"/></script>
<script src="/newscripts/scripts/chosen.jquery.min.js"/></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.magnific-popup.min.js"/></script>
<script type="text/javascript" src="/newscripts/scripts/waypoints.min.js"/></script>
<script type="text/javascript"
	src="/newscripts/scripts/jquery.counterup.min.js"/></script>
<script type="text/javascript" src="/newscripts/scripts/jquery.jpanelmenu.js"/></script>
<script type="text/javascript" src="/newscripts/scripts/stacktable.js"/></script>
<script type="text/javascript" src="/newscripts/scripts/headroom.min.js"/></script>
</body>
</html>