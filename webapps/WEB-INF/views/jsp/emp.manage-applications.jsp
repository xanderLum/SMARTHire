<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
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
<link rel="stylesheet" href="/newscripts/css/style.css">
<link rel="stylesheet" href="/newscripts/css/colors/green.css"
	id="colors">

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.2.js"></script>
</head>
<body>
	<!-- Titlebar
================================================== -->
	<div id="titlebar" class="single">
		<div class="container">

			<div class="sixteen columns">
				<h2>Manage Applications</h2>
				<nav id="breadcrumbs">
					<ul>
						<li>You are here:</li>
						<li><a href="#">Home</a></li>
						<li>Job Dashboard</li>
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
			<p class="margin-bottom-25" style="float: left;">
				The job applications for <strong><a href="#">${jp.job_title }</a></strong>
				are listed below.
			</p>
			<!-- <strong><a href="#" class="download-csv">Download CSV</a></strong> -->

		</div>


		<div class="eight columns">
			<!-- Select 
			<select data-placeholder="Filter by status"
				class="chosen-select-no-single">
				<option value="">Filter by status</option>
				<option value="new">New</option>
				<option value="interviewed">Interviewed</option>
				<option value="offer">Offer extended</option>
				<option value="hired">Hired</option>
				<option value="archived">Archived</option>
			</select>
			<div class="margin-bottom-15"></div>-->
		</div>

		<!-- Select -->
		<!--
		<div class="eight columns">
			
			<select data-placeholder="Newest first"
				class="chosen-select-no-single">
				<option value="">Newest first</option>
				<option value="name">Sort by name</option>
				<option value="rating">Sort by rating</option>
			</select>
			<div class="margin-bottom-35"></div>
		</div>
 		-->


		<!-- Applications -->
		<div class="sixteen columns">
			<c:forEach items="${rankList }" varStatus="loop">

				<!-- Application #1 -->
				<div class="application">
					<div class="app-content">

						<!-- Name / Avatar -->
						<div class="info">
							<img
								src="<c:url value="/myImage/imageDisplay?username=${rankList[loop.index].js.username }"/>"
								onerror="imgError(this);" alt="avatar"> <span>${rankList[loop.index].js.firstname }
								${rankList[loop.index].js.lastname }</span>
							<ul>
								<li><a href="#"><i class="fa fa-envelope"></i> Contact</a></li>
							</ul>
							<ul>
								<!-- 
							<li><a href="#"><i class="fa fa-file-text"></i> Download
									CV</a></li> -->

								<li><a><i class="fa fa-pencil-square-o"></i> Character:
										${rankList[loop.index].charScore} %</a></li>
								<li><a><i class="fa fa-pencil-square-o"></i> Skills:
										${rankList[loop.index].skillScore} %</a></li>
								<li><a><i class="fa fa-user"></i> Type:
										${rankList[loop.index].type}</a></li>
							</ul>
							<ul>
								<c:forEach items="${rankList[loop.index].matchedSkills}"
									var="keyword">
									<li><i class="fa fa-tag">${keyword}</i></li>
								</c:forEach>
							</ul>
						</div>
						<!-- Buttons -->
						<div class="buttons" id="nav">
						<!-- 
							<a href="#one-1" onclick="functionOne()"
								class="button gray app-link" style=""><i
								class="fa fa-pencil"></i> Edit</a>
							<a href="#two-1"
								onclick="functionTwo()" class="button gray app-link"><i
								class="fa fa-sticky-note"></i> Add Note</a> 
								 -->
							<a href="<%=request.getContextPath()%>/employer/viewJSprofile/${rankList[loop.index].js.username}"
								class="button gray app-link"><i
								class="fa fa-plus-circle"></i> Show Details</a>
						</div>
						<div class="clearfix"></div>
					</div>

				</div>
			</c:forEach>
		</div>
		<!--  Hidden Tabs
					<div class="app-tabs" id="nav">

						<a href="#" class="close-tab button gray"><i
							class="fa fa-close"></i></a>
 -->
		<!-- First Tab 
						<div class="app-tab-content" id="one-1">
							<div class="select-grid">
								<select data-placeholder="Application Status"
									class="chosen-select-no-single">
									<option value="">Application Status</option>
									<option value="new">New</option>
									<option value="interviewed">Interviewed</option>
									<option value="offer">Offer extended</option>
									<option value="hired">Hired</option>
									<option value="archived">Archived</option>
								</select>
							</div>

						<div class="select-grid">
								<input type="number" min="1" max="5"
									placeholder="Rating (out of 5)">
							</div>

							<div class="clearfix"></div>
							<a href="#" class="button margin-top-15">Save</a> <a href="#"
								class="button gray margin-top-15 delete-application">Delete
								this application</a>
	-->
	</div>

	<!-- Second Tab 
						<div class="app-tab-content" id="two-1">
							<textarea placeholder="Private note regarding this application"></textarea>
							<a href="#" class="button margin-top-15">Add Note</a>
						</div>
-->
	<!-- Third Tab 
						<div class="app-tab-content" id="three-1">
							<i>Full Name:</i> <span>${ rankList[loop.index].js.firstname}
								${rankList[loop.index].js.lastname } :
								${rankList[loop.index].js.professionalTitle}</span> <i>Email:</i> <span><a
								href="/cdn-cgi/l/email-protection#fb91949395d59f949ebb9e839a968b979ed5989496"><span
									class="__cf_email__"
									data-cfemail="c4aeabacaaeaa0aba184a1bca5a9b4a8a1eaa7aba9">${rankList[loop.index].js.email }</span>
									<script data-cfhash='f9e31' type="text/javascript">
										/* <![CDATA[ */!function(t, e, r, n,
												c, a, p) {
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
												if (t
														&& (c = t.previousSibling)) {
													p = t.parentNode;
													if (a = c
															.getAttribute('data-cfemail')) {
														for (
																e = '',
																r = '0x'
																		+ a
																				.substr(
																						0,
																						2)
																		| 0,
																n = 2; a.length
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
										}()/* ]]> */
									</script></a></span> <i>Overview:</i> <span>${rankList[loop.index].js.description }
							</span>
						</div>

					</div>
-->
	<!-- Footer 
				<div class="app-footer">
					<div class="rating no-stars">
						<div class="star-rating"></div>
						<div class="star-bg"></div>
					</div>
					<ul>
						<li><i class="fa fa-file-text-o"></i> New</li>
						<li><i class="fa fa-calendar"></i> <fmt:parseDate
								value="${jp.date_posted}" pattern="yyyy-MM-dd HH:mm:ss"
								var="date" /> <fmt:formatDate type="date" dateStyle="long"
								value="${date}" /></li>
					</ul>
					<div class="clearfix"></div>
-->


	<script>
		$(document).ready(function() {
			$('.app-tabs div').hide();
			$('.app-tabs div:first').show();
			$('.app-tabs :first').addClass('active');
			$('.app-tabs a').click(function() {
				var currentTab = $(this).attr('href');
				var vis = $(currentTab).is(':visible');
				$('.app-tabs div').hide();
				$('.app-tabs ul li').removeClass('active');
				$(this).parent().addClass('active');
				if (vis) {
					$(currentTab).hide();
				} else {
					$(currentTab).show();
				}
			});
		});
	</script>

	<script type="text/javascript">
		function imgError(image) {
			console.log("asdf", "asdf")
			image.onerror = "";
			image.src = "/newscripts/images/headshot.jpg";
			return true;
		}
	</script>
	<!-- Scripts
================================================== 
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
-->
</body>
<!-- 	<script type="text/javascript">/* <![CDATA[ */(function(d,s,a,i,j,r,l,m,t){try{l=d.getElementsByTagName('a');t=d.createElement('textarea');for(i=0;l.length-i;i++){try{a=l[i].href;s=a.indexOf('/cdn-cgi/l/email-protection');m=a.length;if(a&&s>-1&&m>28){j=28+s;s='';if(j<m){r='0x'+a.substr(j,2)|0;for(j+=2;j<m&&a.charAt(j)!='X';j+=2)s+='%'+('0'+('0x'+a.substr(j,2)^r).toString(16)).slice(-2);j++;s=decodeURIComponent(s)+a.substr(j,m-j)}t.innerHTML=s.replace(/</g,'&lt;').replace(/>/g,'&gt;');l[i].href='mailto:'+t.value}}catch(e){}}}catch(e){}})(document);/* ]]> */</script>
		 -->
</html>