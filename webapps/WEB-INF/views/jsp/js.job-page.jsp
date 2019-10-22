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
<link rel="stylesheet" href="<c:url value="/newscripts/css/style.css"/>">
<link rel="stylesheet"
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
								<strong>Location:</strong> <span>${jp.location }</span>
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


					<!--  no pop up send message for the mean time -->
					<a href="<%=request.getContextPath()%>/jobseeker/applyJob/${jp.job_post_id}" class="btn btn-primary">Apply For This Job</a>

					<div id="small-dialog"
						class="zoom-anim-dialog mfp-hide apply-popup">
						<div class="small-dialog-headline">
							<h2>Apply For This Job</h2>
						</div>

						<div class="small-dialog-content">
							<form method="post" action="sendMessage">
								<input type="text" placeholder="Full Name" name="fullName"
									value="" required /> <input type="text"
									placeholder="Email Address" name="applicantEmail" value=""
									required />
								<textarea
									placeholder="Your message / cover letter sent to the employer"
									name="message"></textarea>
								<input type="hidden" name="employerEmail" value="${emp.email }" />
								<!-- Upload CV -->
								<div class="upload-info">
									<strong>Upload your CV (optional)</strong> <span>Max.
										file size: 5MB</span>
								</div>
								<div class="clearfix"></div>

								<label class="upload-btn"> <input type="file" multiple />
									<i class="fa fa-upload"></i> Browse
								</label> <span class="fake-input">${msg }</span>
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


	<!-- Back To Top Button -->
	<div id="backtotop">
		<a href="#"></a>
	</div>

	</div>
	<!-- Wrapper / End -->


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