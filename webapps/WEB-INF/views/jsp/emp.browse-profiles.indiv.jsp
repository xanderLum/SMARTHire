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
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS
================================================== -->
<link rel="stylesheet" href="<c:url value="newscripts/css/style.css"/>">
<link rel="stylesheet" href="<c:url value="newscripts/css/colors/green.css"/>">
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
			<h1><a href="index.jsp"><img src="<c:url value="newscripts/images/smartlogo.png" alt="SMARTHire" />"/></a></h1>
		</div>

		<!-- Menu -->
		<nav id="navigation" class="menu">
			<ul id="responsive">

				<li><a href="index.html">Home</a></li>
				<li><a href="">Browse</a>
					<ul>
						<li><a href="js.browse-jobs.jsp">Jobs</a></li>
						<li><a href="browse-categories.jsp">Categories</a></li>
					</ul></li>
				<li><a href="contact.jsp">Contact</a></li>
			<li><a href="blog.jsp">Blog</a></li>
			</ul>

			<ul class="responsive float-right">
				<li><a href="my-account.html#tab2"><i class="fa fa-user"></i> Sign Up</a></li>
				<li><a href="my-account.html"><i class="fa fa-lock"></i> Log In</a></li>
			</ul>
		</nav>

		<!-- Navigation -->
		<div id="mobile-navigation">
			<a href="#menu" class="menu-trigger"><i class="fa fa-reorder"></i> Menu</a>
		</div>

	</div>
</div>
</header>
<div class="clearfix"></div>
<!-- Titlebar
================================================== -->
<div id="titlebar" class="resume">
	<div class="container">
		<div class="ten columns">
			<div class="resume-titlebar">
				<img src="images/resumes-list-avatar-01.png" alt="">
				<div class="resumes-list-content">
					<h4>John Doe <span>UX/UI Graphic Designer</span></h4>
					<span class="icons"><i class="fa fa-map-marker"></i> Mountain View, CA</span>
					<span class="icons"><i class="fa fa-money"></i> $100 / hour</span>
					<span class="icons"><a href="#"><i class="fa fa-link"></i> Website</a></span>
					<span class="icons"><a href="/cdn-cgi/l/email-protection#543e3b3c3a7a303b3114312c35392438317a373b39"><i class="fa fa-envelope"></i> <span class="__cf_email__" data-cfemail="9af0f5f2f4b4fef5ffdaffe2fbf7eaf6ffb4f9f5f7">[email&#160;protected]</span><script data-cfhash='f9e31' type="text/javascript">/* <![CDATA[ */!function(t,e,r,n,c,a,p){try{t=document.currentScript||function(){for(t=document.getElementsByTagName('script'),e=t.length;e--;)if(t[e].getAttribute('data-cfhash'))return t[e]}();if(t&&(c=t.previousSibling)){p=t.parentNode;if(a=c.getAttribute('data-cfemail')){for(e='',r='0x'+a.substr(0,2)|0,n=2;a.length-n;n+=2)e+='%'+('0'+('0x'+a.substr(n,2)^r).toString(16)).slice(-2);p.replaceChild(document.createTextNode(decodeURIComponent(e)),c)}p.removeChild(t)}}catch(u){}}()/* ]]> */</script></a></span>
					<div class="skills">
						<span>JavaScript</span>
						<span>PHP</span>
						<span>WordPress</span>
					</div>
					<div class="clearfix"></div>

				</div>
			</div>
		</div>

		<div class="six columns">
			<div class="two-buttons">

				<a href="#small-dialog" class="popup-with-zoom-anim button"><i class="fa fa-envelope"></i> Send Message</a>
				<div id="small-dialog" class="zoom-anim-dialog mfp-hide apply-popup">
					<div class="small-dialog-headline">
						<h2>Send Message to John Doe</h2>
					</div>

					<div class="small-dialog-content">
						<form action="#" method="get" >
							<input type="text" placeholder="Full Name" value=""/>
							<input type="text" placeholder="Email Address" value=""/>
							<textarea placeholder="Message"></textarea>

							<button class="send">Send Application</button>
						</form>
					</div>
					
				</div>
				<a href="#" class="button dark"><i class="fa fa-star"></i> Bookmark This Resume</a>


			</div>
		</div>

	</div>
</div>


<!-- Content
================================================== -->
<div class="container">
	<!-- Recent Jobs -->
	<div class="eight columns">
	<div class="padding-right">

		<h3 class="margin-bottom-15">About Me</h3>

		<p class="margin-reset">
			The Food Service Specialist ensures outstanding customer service is provided to food customers and that all food offerings meet the required stock levels and presentation standards. Beginning your career as a Food Steward will give you a strong foundation in Speedway's food segment that can make you a vital member of the front line team!
		</p>

		<br>

		<p>The <strong>Food Service Specialist</strong> will have responsibilities that include:</p>

		<ul class="list-1">
			<li>Excellent customer service skills, communication skills, and a happy, smiling attitude are essential.</li>
			<li>Must be available to work required shifts including weekends, evenings and holidays.</li>
			<li>Must be able to perform repeated bending, standing and reaching.</li>
			<li>Must be able to occasionally lift up to 50 pounds</li>
		</ul>

	</div>
	</div>


	<!-- Widgets -->
	<div class="eight columns">

		<h3 class="margin-bottom-20">Education</h3>

		<!-- Resume Table -->
		<dl class="resume-table">
			<dt>
				<small class="date">2012 - 2015</small>
				<strong>Bsc Computing at College of West Anglia</strong>
			</dt>
			<dd>
				<p>Captain, why are we out here chasing comets? Maybe we better talk out here; the observation lounge has turned into a swamp. Ensign Babyface!</p>
			</dd>

		
			<dt>
				<small class="date">2006 - 2010</small>
				<strong>GCSE something at King Edward 7th</strong>
			</dt>
			<dd>
				<p>Captain, why are we out here chasing comets? Maybe we better talk out here; the observation lounge has turned into a swamp. Ensign Babyface!</p>
			</dd>


			<dt>
				<small class="date">2004 - 2006</small>
				<strong>Test 2 at Test</strong>
			</dt>
			<dd>
				<p>Phasellus vestibulum metus orci, ut facilisis dolor interdum eget. Pellentesque magna sem, hendrerit nec elit sit amet, ornare efficitur est.</p>
			</dd>

		</dl>

	</div>

</div>


<!-- Footer
================================================== -->
<div class="margin-top-45"></div>

<div id="footer">
	<!-- Main -->
	<div class="container">

		<div class="seven columns">
			<h4>About</h4>
			<p>Morbi convallis bibendum urna ut viverra. Maecenas quis consequat libero, a feugiat eros. Nunc ut lacinia tortor morbi ultricies laoreet ullamcorper phasellus semper.</p>
			<a href="#" class="button">Get Started</a>
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
					<li><a class="facebook" href="#"><i class="icon-facebook"></i></a></li>
					<li><a class="twitter" href="#"><i class="icon-twitter"></i></a></li>
					<li><a class="gplus" href="#"><i class="icon-gplus"></i></a></li>
					<li><a class="linkedin" href="#"><i class="icon-linkedin"></i></a></li>
				</ul>
				<div class="copyrights">©  Copyright 2015 by <a href="#">Work Scout</a>. All Rights Reserved.</div>
			</div>
		</div>
	</div>

</div>

<!-- Back To Top Button -->
<div id="backtotop"><a href="#"></a></div>

</div>
<!-- Wrapper / End -->


<!-- Scripts
================================================== -->
<script src="scripts/jquery-2.1.3.min.js"></script>
<script src="scripts/custom.js"></script>
<script src="scripts/jquery.superfish.js"></script>
<script src="scripts/jquery.themepunch.tools.min.js"></script>
<script src="scripts/jquery.themepunch.revolution.min.js"></script>
<script src="scripts/jquery.themepunch.showbizpro.min.js"></script>
<script src="scripts/jquery.flexslider-min.js"></script>
<script src="scripts/chosen.jquery.min.js"></script>
<script src="scripts/jquery.magnific-popup.min.js"></script>
<script src="scripts/waypoints.min.js"></script>
<script src="scripts/jquery.counterup.min.js"></script>
<script src="scripts/jquery.jpanelmenu.js"></script>
<script src="scripts/stacktable.js"></script>
<script src="scripts/headroom.min.js"></script>


</body>
</html>