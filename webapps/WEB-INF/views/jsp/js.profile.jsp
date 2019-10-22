<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	pageContext.setAttribute("newLineChar", "\n");
%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>

<link
	href='http://fonts.googleapis.com/css?family=Rokkitt:400,700|Lato:400,300'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/newscripts/css/stylecv.css" /> ">
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


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<%session.setAttribute("username", session.getAttribute("username")); %>
</head>
<body>
<body id="top">
	<div id="cv" class="instaFade">
		<div class="mainDetails">
			<div id="headshot" class="quickFade">
				<img
					src="<c:url value="/myImage/imageDisplay?username=${username}"/>"
					onerror="return imgError(this);" alt="Profile Photo">
			</div>

			<div id="name">
				<h1 class="quickFade delayTwo">${js.firstname}${js.lastname}</h1>
				<h2 class="quickFade delayThree">${js.professionalTitle}</h2>
			</div>

			<div id="contactDetails" class="quickFade delayFour">
				<ul>
					<li>email: <a href="mailto:${js.email}" target="_blank">${js.email }</a></li>
					<li>contactNo: ${js.contactnumber}</li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>

		<div id="mainArea" class="quickFade delayFive">
			<section>
				<article>
					<div class="sectionTitle">
						<h1>Personal Profile</h1>
					</div>

					<div class="sectionContent">
						<p>${fn:replace(js.description, '\\n', '<br> ')}</p>
					</div>
				</article>
				<div class="clear"></div>
			</section>


			<section>
				<div class="sectionTitle">
					<h1>Work Experience</h1>
				</div>

				<div class="sectionContent">
					<c:forEach items="${exp}" var="ex">
						<article>
							<h2>${ex.jobTitle}  at   ${ex.employer}</h2>
							<p class="subDetails">
								<fmt:parseDate value="${ex.startDate}" pattern="yyyy-MM-dd"
									var="date" />
								<fmt:formatDate type="date" dateStyle="long" value="${date}" />
								to
								<fmt:parseDate value="${ex.endDate }" pattern="yyyy-MM-dd"
									var="date" />
								<fmt:formatDate type="date" dateStyle="long" value="${date}" />
							</p>
							<p>${ex.notes }</p>
						</article>
					</c:forEach>
				</div>
				<div class="clear"></div>
			</section>


			<section>
				<div class="sectionTitle">
					<h1>Key Skills</h1>
				</div>

				<div class="sectionContent">
					<ul class="keySkills">
						<c:forEach items="${skills}" var="skill">
							<li>${skill.skill_name}</li>
						</c:forEach>
					</ul>
				</div>
				<div class="clear"></div>
			</section>


			<section>
				<div class="sectionTitle">
					<h1>Education</h1>
				</div>

				<div class="sectionContent">
					<c:forEach items="${educ}" var="edu">
						<article>
							<h2>${edu.schoolName }</h2>
							<p>${edu.qualifications}</p>
							<h4 class="subDetails">
								<fmt:parseDate value="${edu.startDate }" pattern="yyyy-MM-dd"
									var="date" />
								<fmt:formatDate type="date" dateStyle="long" value="${date}" />
								to
								<fmt:parseDate value="${edu.endDate }" pattern="yyyy-MM-dd"
									var="date" />
								<fmt:formatDate type="date" dateStyle="long" value="${date}" />
							</h4>

							<p>${edu.notes}</p>
						</article>
					</c:forEach>
				</div>
				<div class="clear"></div>
			</section>

			<section>
				<div class="sectionTitle">
					<h1>Certificates</h1>
				</div>

				<div class="sectionContent">
					<c:forEach items="${cert}" var="cert">
						<article>
							<h2>${cert.name}</h2>
							<p class="subDetails">${cert.uid}</p>
							<p>${cert.description}</p>
						</article>
					</c:forEach>
				</div>
				<div class="clear"></div>
			</section>

			<section>
				<div class="sectionTitle">
					<h1>Tests</h1>
				</div>

				<div class="sectionContent">
					<c:forEach items="${test}" var="test">
						<article>
							<h3>${test.name}</h3>
							<p>Score : ${test.score}%</p>
							<p>
								Date:
								<fmt:parseDate value="${test.dateTaken}" pattern="yyyy-MM-dd"
									var="date" />
								<fmt:formatDate type="date" dateStyle="long" value="${date}" />
							</p>
							<hr>
						</article>
					</c:forEach>
				</div>
				<div class="clear"></div>
			</section>

		</div>
	</div>

	<script type="text/javascript">
		var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl."
				: "http://www.");
		document
				.write(unescape("%3Cscript src='"
						+ gaJsHost
						+ "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
	</script>

	<script type="text/javascript">
		function imgError(image) {
			console.log("", "")
			image.onerror = "";
			image.src = "/newscripts/images/headshot.jpg";
			return true;
		}
	</script>

	<script type="text/javascript">
		var pageTracker = _gat._getTracker("UA-3753241-1");
		pageTracker._initData();
		pageTracker._trackPageview();
	</script>

</body>


</html>