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
	href="<c:url value="/newscripts/css/bootstrap.css"/>">


<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/animate.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/bootstrap.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/font-awesome.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/responsive.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/style.css" />">

<link
	href='http://fonts.googleapis.com/css?family=PT+Sans:regular,italic,bold,bolditalic&amp;subset=latin,latin-ext,cyrillic'
	rel='stylesheet' type='text/css'>
<script src="/newscripts/scripts/jquery-3.1.1.min.js"></script>

<!-- noty -->
<script type="text/javascript"
	src="/newscripts/noty-2.3.8/js/noty/packaged/jquery.noty.packaged.js"></script>

</head>
<body>
	<div id="wrapper">
		<!-- Title bar
================================================== -->
		<div id="titlebar" class="single">
			<div class="container">

				<div class="sixteen columns">
					<h2>Manage Jobs</h2>
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

				<p class="margin-bottom-25">Your listings are shown in the table
					below. Expired listings will be automatically removed after 30
					days.</p>

				<table class="manage-table responsive-table">
					<tr>
						<th><i class="fa fa-file-text"></i> Title</th>
						<th><i class="fa fa-check-square-o"></i> Filled?</th>
						<th><i class="fa fa-calendar"></i> Date Posted</th>
						<th><i class="fa fa-calendar"></i> Date Expires</th>
						<th><i class="fa fa-user"></i> Applications</th>
						<th></th>
					</tr>
					<c:forEach items="${aJP}" var="jp" varStatus="loop">
						<!-- Item #1 -->
						<tr>
							<td class="title"><a href="#"><c:out
										value="${jp.job_title}" /></a></td>
							<td class="centered">-</td>
							<td><c:choose>
									<c:when test="${empty jp.date_posted}">
							        Not specified.
							    </c:when>
									<c:otherwise>
										<fmt:parseDate value="${jp.date_posted}"
											pattern="yyyy-MM-dd HH:mm:ss" var="date" />
										<fmt:formatDate type="date" dateStyle="long" value="${date}" />
									</c:otherwise>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${empty jp.closing_date}">
							        Not specified.
							    </c:when>
									<c:otherwise>
										<fmt:parseDate value="${jp.closing_date}"
											pattern="yyyy-MM-dd HH:mm:ss" var="date" />
										<fmt:formatDate type="date" dateStyle="long" value="${date}" />
									</c:otherwise>
								</c:choose></td>
							<td class="centered"><a
								href="<%=request.getContextPath()%>/employer/contentViewSpecificJobPost/${jp.job_post_id}"
								class="button"><c:out
										value="Show (${applicantCount[loop.index]})" /></a></td>
							<td class="action"><a
								href="<%=request.getContextPath()%>/employer/editJobPost/${jp.job_post_id}"><i
									class="fa fa-pencil"></i> Edit</a> <a href="#"><i
									class="fa  fa-check "></i> Mark Filled</a> <a
								href="<%=request.getContextPath()%>/employer/deleteJobPost/${jp.job_post_id}"
								name="delete" onclick="return generate('warning','center');"
								class="delete"><i class="fa fa-remove"></i> Delete</a></td>
						</tr>
					</c:forEach>
				</table>
				<br> <a
					href="<%=request.getContextPath()%>/employer/contentCreateJobPosts"
					class="button">Add Job</a>
			</div>
			<script type="text/javascript">
				window.onload = alertName;
			</script>
		</div>

		<!-- 
		<div class="info message">
			<h3>FYI, something just happened!</h3>
			<p>This is just an info notification message.</p>
		</div>

		<div class="error message">
			<h3>Ups, an error occurred!</h3>
			<p>This is just an error notification message.</p>
		</div>

		<div class="warning message">
			<h3>Wait, you wish to delete this record?</h3>
			<p>This is just a warning notification message.</p>
		</div>

		<div class="success message">
			<h3>Successfully deleted record!</h3>
			<p>This is just a success notification message.</p>
		</div>
		 -->
	</div>
</body>

<script>
<script type="text/javascript">
var Msg ='<%=session.getAttribute("getAlert")%>';
	if (Msg == "null") {
		generate('info', "This is where you manage your job postings.",
				'center');
	} else if (Msg == "success") {
		generate('success', "Updated job postings.", 'center')
	} else if (Msg == "failed") {
		generate('error', "Failed to update job postings.  Please retry.",
				'center');
	}
</script>

<script>
	function generate(type, text, layout) {
		var n = noty({
			text : text,
			type : type,
			dismissQueue : true,
			layout : layout,
			theme : 'defaultTheme',
			buttons : [ {
				addClass : 'btn btn-primary',
				text : 'Ok',
				onClick : function($noty) {
					$noty.close();
					noty({
						dismissQueue : true,
						force : true,
						layout : layout,
						theme : 'defaultTheme',
						text : 'You clicked "Ok"',
						type : 'success'
					});
				}
			}, {
				addClass : 'btn btn-danger',
				text : 'Cancel',
				onClick : function($noty) {
					$noty.close();
					noty({
						dismissQueue : true,
						force : true,
						layout : layout,
						theme : 'defaultTheme',
						text : 'You clicked "Cancel"',
						type : 'error'
					});
				}
			} ]
		});
		console.log('html: ' + n.options.id);
	}
</script>
<!-- 
<script>
	var myMessages = [ 'info', 'warning', 'error', 'success' ];

	$(document).ready(function() {
		// Initially, hide them all
		hideAllMessages();

		// Show message
		for (var i = 0; i < myMessages.length; i++) {
			showMessage(myMessages[i]);
		}

		// When message is clicked, hide it
		$('.message').click(function() {
			$(this).animate({
				top : -$(this).outerHeight()
			}, 500);
		});

	});

	function hideAllMessages() {
		var messagesHeights = new Array(); // this array will store height for each

		for (i = 0; i < myMessages.length; i++) {
			messagesHeights[i] = $('.' + myMessages[i]).outerHeight(); // fill array
			$('.' + myMessages[i]).css('top', -messagesHeights[i]); //move element outside viewport     
		}
	}

	function showMessage(type) {
		$('.' + type + '-trigger').click(function() {
			hideAllMessages();
			$('.' + type).animate({
				top : "0"
			}, 500);
		});
	}
</script>
 -->
<style>
.message {
	background-size: 40px 40px;
	background-image: linear-gradient(135deg, rgba(255, 255, 255, .05) 25%,
		transparent 25%, transparent 50%, rgba(255, 255, 255, .05) 50%,
		rgba(255, 255, 255, .05) 75%, transparent 75%, transparent);
	box-shadow: inset 0 -1px 0 rgba(255, 255, 255, .4);
	width: 100%;
	border: 1px solid;
	color: #fff;
	padding: 15px;
	position: fixed;
	_position: absolute;
	text-shadow: 0 1px 0 rgba(0, 0, 0, .5);
	animation: animate-bg 5s linear infinite;
}

.info {
	background-color: #4ea5cd;
	border-color: #3b8eb5;
}

.error {
	background-color: #de4343;
	border-color: #c43d3d;
}

.warning {
	background-color: #eaaf51;
	border-color: #d99a36;
}

.success {
	background-color: #61b832;
	border-color: #55a12c;
}

.message h3 {
	margin: 0 0 5px 0;
}

.message p {
	margin: 0;
}

@
keyframes animate-bg {from { background-position:00;
	
}

to {
	background-position: -80px 0;
}
}
</style>
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
<script type="text/javascript" src="/newscripts/scripts/headroom.min.js" /></script>
</html>