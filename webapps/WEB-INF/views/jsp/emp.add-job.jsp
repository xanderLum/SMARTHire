<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Job Vacancy</title>


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

		<!-- Titlebar
================================================== -->
		<div id="titlebar" class="single submit-page">
			<div class="container">

				<div class="sixteen columns">
					<h2>
						<i class="fa fa-plus-circle"></i> Add Job
					</h2>
				</div>

			</div>
		</div>


		<!-- Content
================================================== -->
		<div class="container">

			<!-- Submit Page -->
			<div class="sixteen columns">
				<div class="">
					<form method="post" action="empCreateJobPost/">
						<!-- Title -->
						<div class="form">
							<h5>Job Title</h5>
							<input type="hidden" name="job_post_id" value="${jp.job_post_id }"/>
							<input class="search-field" name="job_title" type="text"
								placeholder="" value="${jp.job_title }" required />
						</div>

						<!-- Location -->
						<div class="form">
							<h5>
								Location <span>(optional)</span>
							</h5>
							<input class="search-field" name="location" type="text"
								placeholder="e.g. London" value="${jp.location }" />
							<p class="note">Leave this blank if the location is not
								important</p>
						</div>

						<!-- Job Type -->
						<div class="form">
							<h5>Job Type</h5>
							<select data-placeholder="Full-Time" name="job_type"
								class="chosen-select-no-single">
								<option value="Full-Time" <c:if test="${jp.job_type eq 'Full-Time' }">selected</c:if>>Full-Time</option>
								<option value="Part-Time" <c:if test="${jp.job_type eq 'Part-Time' }">selected</c:if>>Part-Time</option>
								<option value="Internship" <c:if test="${jp.job_type eq 'Internship' }">selected</c:if>>Internship</option>
								<option value="Freelance" <c:if test="${jp.job_type eq 'Freelance' }">selected</c:if>>Freelance</option>
							</select>
						</div>


						<!-- Choose Category -->
						<div class="form">
							<div class="select">
								<h5>Category</h5>
								<select data-placeholder="Choose Categories" name="job_category"
									class="chosen-select" multiple>
									<option value="Web Developers" <c:if test="${jp.job_type eq 'Web Developers' }">selected</c:if>>Web Developers</option>
									<option value="Mobile Developers" <c:if test="${jp.job_type eq 'Mobile Developers' }">selected</c:if>>Mobile Developers</option>
									<option value="Designers & Creatives" <c:if test="${jp.job_type eq 'Designers & Creatives' }">selected</c:if>>Designers &
										Creatives</option>
									<option value="Writers" <c:if test="${jp.job_type eq 'Writers' }">selected</c:if>>Writers</option>
									<option value="Virtual Assistants" <c:if test="${jp.job_type eq 'Virtual Assistants' }">selected</c:if>>Virtual Assistants</option>
									<option value="Customer Service Agents" <c:if test="${jp.job_type eq 'Customer Service Agents' }">selected</c:if>>Customer
										Service Agents</option>
									<option value="Sales & Marketing Experts" <c:if test="${jp.job_type eq 'Sales & Marketing Experts' }">selected</c:if>>Sales &
										Marketing Experts</option>
									<option value="Accountants & Consultants" <c:if test="${jp.job_type eq 'Accountants & Consultants' }">selected</c:if>>Accountants
										& Consultants</option>
									<option value="Administrative Support Specialist" <c:if test="${jp.job_type eq 'Administrative Support Specialist' }">selected</c:if>>Administrative
										Support Specialist</option>
								</select>
							</div>
						</div>

						<!-- Tags -->
						<div class="form">
							<h5>Job Tags</h5>
							<input class="search-field" type="text" name="skills"
								placeholder="e.g. PHP, Social Media, Management" value="${jpSkills }"
								required />
							<p class="note">Comma separate tags, such as required skills
								or technologies, for this job.</p>
						</div>


						<!-- Description -->
						<div class="form">
							<h5>Description</h5>
							<textarea class="WYSIWYG" name="job_description" cols="40"
								rows="3" id="summary" spellcheck="true" required>${jp.job_description}</textarea>
						</div>


						<!-- TClosing Date -->
						<div class="form">
							<h5>
								Closing Date <span>(optional)</span>
							</h5>
							<input data-role="date" name="closing_date" type="text"
								placeholder="yyyy-mm-dd" value="${jp.closing_date }">
							<p class="note">Deadline for new applicants.</p>
						</div>

						<div class="divider margin-top-0"></div>
						<input type="submit" class="button big margin-top-5" value="Post Job
						Vacancy"/> <i class="fa fa-arrow-circle-right"></i>
					</form>
				</div>
			</div>

		</div>


		<div class="margin-top-60"></div>
		<!-- Back To Top Button -->
		<div id="backtotop">
			<a href="#"></a>
		</div>

	</div>
	<!-- Wrapper / End -->


	<!-- Scripts
================================================== -->
	<script src="/newscripts/scripts/jquery-2.1.3.min.js"></script>
	<script src="/newscripts/scripts/custom.js"></script>
	<script src="/newscripts/scripts/jquery.superfish.js"></script>
	<script src="/newscripts/scripts/jquery.themepunch.tools.min.js"></script>
	<script src="/newscripts/scripts/jquery.themepunch.revolution.min.js"></script>
	<script src="/newscripts/scripts/jquery.themepunch.showbizpro.min.js"></script>
	<script src="/newscripts/scripts/jquery.flexslider-min.js"></script>
	<script src="/newscripts/scripts/chosen.jquery.min.js"></script>
	<script src="/newscripts/scripts/jquery.magnific-popup.min.js"></script>
	<script src="/newscripts/scripts/waypoints.min.js"></script>
	<script src="/newscripts/scripts/jquery.counterup.min.js"></script>
	<script src="/newscripts/scripts/jquery.jpanelmenu.js"></script>
	<script src="/newscripts/scripts/stacktable.js"></script>
	<script src="/newscripts/scripts/headroom.min.js"></script>


	<!-- WYSIWYG Editor -->
	<script type="text/javascript"
		src="/newscripts/scripts/jquery.sceditor.bbcode.min.js"></script>
	<script type="text/javascript"
		src="/newscripts/scripts/jquery.sceditor.js"></script>
</body>
</html>