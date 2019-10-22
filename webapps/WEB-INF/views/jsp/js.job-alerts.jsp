<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

</head>
<body>
	<!-- Titlebar
================================================== -->
	<div id="titlebar" class="single">
		<div class="container">

			<div class="sixteen columns">
				<h2>Job Alerts</h2>
				<nav id="breadcrumbs">
				<ul>
					<li>You are here:</li>
					<li><a href="#">Home</a></li>
					<li>Job Alerts</li>
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

			<p class="margin-bottom-25">Your job alerts are shown below.</p>

			<table class="manage-table resumes responsive-table">
				<tr>
					<th><i class="fa fa-file-text"></i> Alert Name</th>
					<th><i class="fa fa-calendar"></i> Date Created</th>
					<th><i class="fa fa-tags"></i> Keywords</th>
					<th><i class="fa fa-map-marker"></i> Location</th>
					<th><i class="fa fa-check-square-o"></i> Status</th>
					<th></th>
				</tr>
				<c:forEach items="${jpAlerts }" var="alerts" varStatus="loop">


					<!-- Item #1 -->
					<tr>
						<td class="alert-name">${jpAlerts[loop.index].alertName }</td>
						<td><fmt:parseDate
								value="${jpAlerts[loop.index].dateCreated }"
								pattern="yyyy-MM-dd" var="date" /> <fmt:formatDate type="date"
								dateStyle="long" value="${date}" /></td>
						<td class="keywords">${jpAlerts[loop.index].keywords }</td>
						<td>${jpAlerts[loop.index].location }</td>
						<td>${jpAlerts[loop.index].status }<br> <!-- 
						<span class="pull-right">
								<a
								href="<%=request.getContextPath()%>/jobseeker/applyJob/${jpAlerts[loop.index].job_post_id}"
								class="btn btn-primary">Apply</a>
						</span></td>
						 --> <td class="action"><!-- <a href="#"><i
								class="fa fa-check-circle-o"></i> Show Results</a>  --><a href="#"><i class="fa fa-envelope"></i> Email</a> <!--  <a href="#"><i
								class="fa fa-pencil"></i> Edit</a> <a href="#"><i
								class="fa  fa-eye-slash"></i> Disable</a> --> <a href="#"
							class="delete"><i class="fa fa-remove"></i> Delete</a>
						</td>
						
					
					</tr>

</c:forEach>
				</table>
			

			<!-- 
			<br> <a href="#small-dialog" class="popup-with-zoom-anim button">Add
				Alert</a>

			<div id="small-dialog" class="zoom-anim-dialog mfp-hide apply-popup">
				<div class="small-dialog-headline">
					<h2>Add Alert</h2>
				</div>

				<div class="small-dialog-content">
					<form action="#" method="get">
						<input type="text" placeholder="Alert Name" value="" /> <input
							type="text" placeholder="Keyword" value="" /> <input type="text"
							placeholder="Location" value="" />
 -->
			<!-- Select 
						<select data-placeholder="Email Frequency"
							class="chosen-select-no-single">
							<option value="">Email Frequency</option>
							<option value="1">Daily</option>
							<option value="2">Weekly</option>
							<option value="3">Fortnightly</option>
						</select>

						<div class="clearfix"></div>
						<div class="margin-top-15"></div>
-->
			<!-- Select 
						<select data-placeholder="Job Type" class="chosen-select" multiple>
							<option value="1">Full-Time</option>
							<option value="2">Part-Time</option>
							<option value="3">Internship</option>
							<option value="4">Freelance</option>
							<option value="5">Temporary</option>
						</select>

						<div class="margin-top-20"></div>
						<div class="divider"></div>

						<button class="send">Save Alert</button>
					</form>
				</div>
			</div>
-->
		</div>

	</div>


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
	<script type="text/javascript"
		src="/newscripts/scripts/headroom.min.js" /></script>

</body>
</html>