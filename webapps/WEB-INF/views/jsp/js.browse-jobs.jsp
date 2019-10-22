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
	
	<%session.setAttribute("username", session.getAttribute("username")); %>

</head>
<body>

	<!-- Content
================================================== -->
	<div class="container">
		<!-- Recent Jobs -->
		<div class="eleven columns">
			<div class="padding-right">
			
				<ul class="job-list full">
					<c:forEach items="${aJP}" var="jp">
						<!--
						<form method="get" action="applyJob/${jp.job_post_id}">
							<span class="pull-right"> <input type="submit" 
								class="btn btn-primary" value="Apply!" /></span>
						</form>
						-->

						<span class="pull-right"> <a
							href="<%=request.getContextPath()%>/jobseeker/applyJob/${jp.job_post_id}"
							class="btn btn-primary">Apply!</a>
						</span>

						<br>
						
						<li>
							<div class="job-list-content">
							<img src="<c:url value="/myImage/imageDisplay?username=${jp.username}"/>"
							alt="Company Logo">
								<h4>${jp.job_title }
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
								</h4>
								<div class="job-icons">
									<span><i class="fa fa-briefcase"></i> ${jp.username }</span> <span><i
										class="fa fa-map-marker"></i> ${jp.location }</span> <span><i
										class="fa fa-money"></i> $100 / hour</span>
								</div>
								<p>${jp.job_description }</p>

							</div> <span class="pull-right"><a
								href="<%=request.getContextPath()%>/jobseeker/viewJob/${jp.job_post_id}"></a></span>
							<div class="clearfix"></div></li>
						<br>
						<br>
					</c:forEach>

				</ul>
				<div class="clearfix"></div>
			</div>
		</div>

		<!-- Comment extras for now -->
		<!-- Widgets -->
		<!-- Sort by -->
		<!-- 
		<div class="five columns">

			<div class="widget">
				<h4>Sort by</h4>

				<!-- Select -->
		<!-- 
				<select data-placeholder="Choose Category"
					class="chosen-select-no-single">
					<option selected="selected" value="recent">Newest</option>
					<option value="oldest">Oldest</option>
					<option value="expiry">Expiring Soon</option>
					<option value="ratehigh">Hourly Rate - Highest First</option>
					<option value="ratelow">Hourly Rate - Lowest First</option>
				</select>

			</div>

			<!-- Location -->
		<!-- 
			<div class="widget">
				<h4>Location</h4>
				<form action="#" method="get">
					<input type="text" placeholder="State / Province" value="" /> <input
						type="text" placeholder="City" value="" /> <input type="text"
						class="miles" placeholder="Miles" value="" /> <label
						for="zip-code" class="from">from</label> <input type="text"
						id="zip-code" class="zip-code" placeholder="Zip-Code" value="" /><br>

					<button class="button">Filter</button>
				</form>
			</div>

			<!-- Job Type -->
		<!-- 
			<div class="widget">
				<h4>Job Type</h4>

				<ul class="checkboxes">
					<li><input id="check-1" type="checkbox" name="check"
						value="check-1" checked> <label for="check-1">Any
							Type</label></li>
					<li><input id="check-2" type="checkbox" name="check"
						value="check-2"> <label for="check-2">Full-Time <span>(312)</span></label>
					</li>
					<li><input id="check-3" type="checkbox" name="check"
						value="check-3"> <label for="check-3">Part-Time <span>(269)</span></label>
					</li>
					<li><input id="check-4" type="checkbox" name="check"
						value="check-4"> <label for="check-4">Internship <span>(46)</span>
					</label></li>
					<li><input id="check-5" type="checkbox" name="check"
						value="check-5"> <label for="check-5">Freelance <span>(119)</span></label>
					</li>
				</ul>

			</div>

			<!-- Rate/Hr -->
		<!-- 
			<div class="widget">
				<h4>Rate / Hr</h4>

				<ul class="checkboxes">
					<li><input id="check-6" type="checkbox" name="check"
						value="check-6" checked> <label for="check-6">Any
							Rate</label></li>
					<li><input id="check-7" type="checkbox" name="check"
						value="check-7"> <label for="check-7">$0 - $25 <span>(231)</span></label>
					</li>
					<li><input id="check-8" type="checkbox" name="check"
						value="check-8"> <label for="check-8">$25 - $50 <span>(297)</span></label>
					</li>
					<li><input id="check-9" type="checkbox" name="check"
						value="check-9"> <label for="check-9">$50 - $100 <span>(78)</span>
					</label></li>
					<li><input id="check-10" type="checkbox" name="check"
						value="check-10"> <label for="check-10">$100 -
							$200 <span>(98)</span>
					</label></li>
					<li><input id="check-11" type="checkbox" name="check"
						value="check-11"> <label for="check-11">$200+ <span>(21)</span></label>
					</li>
				</ul>

			</div>

		</div>
	 -->
		<!-- Widgets / End -->


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