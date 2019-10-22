<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMARTHire Blog</title>


<link rel="icon" href="<c:url value="/img/favicon.png"/>"
	type="image/png" />
<link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon" />

<!-- Mobile Specific Metas
================================================== -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS
================================================== -->
<link rel="stylesheet"
	href="<c:url value="newscripts/css/style.css" /> ">
<link rel="stylesheet"
	href="<c:url value="newscripts/css/colors/green.css" />">
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
						<h1>
							<a href="<%=request.getContextPath()%>/"><img
								src="<c:url value="newscripts/images/smartlogo.png"/>" /></a>
						</h1>
					</div>

					<!-- Menu -->
					<nav id="navigation" class="menu">
						<ul id="responsive">
							<li><a href="<%=request.getContextPath()%>/" id="current">Home</a></li>
							<!-- 
				<li><a href="">Browse</a>
					<ul>
						<li><a href="js.browse-jobs.jsp">Jobs</a></li>
						<li><a href="browse-categories.jsp">Categories</a></li>
					</ul></li>
					 -->
							<li><a href="contact">Contact</a></li>
							<li><a href="blog">Blog</a></li>
						</ul>
						<ul class="responsive float-right">
							<!-- 
				<li><a href="proceedSignUpPage"><i class="fa fa-user"></i>Sign Up</a></li>
				<li><a href="proceedLoginPage"><i class="fa fa-lock"></i> Log In</a></li>
				 -->
							<li><a href="proceedSignUpPage"><i class="fa fa-user"></i>Get
									Started</a></li>
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

		<div class="clearfix"></div>




		<!-- Slider
================================================== -->
		<div class="fullwidthbanner-container">
			<div class="fullwidthbanner">
				<ul>

					<!-- Slide 1 -->
					<li data-fstransition="fade" data-transition="fade"
						data-slotamount="10" data-masterspeed="300"><img
						src="newscripts/images/banner-02.jpg" alt="">

						<div class="caption title sfb" data-x="center" data-y="195"
							data-speed="400" data-start="800" data-easing="easeOutExpo">
							<h2>Hire great hourly employees</h2>
						</div>

						<div class="caption text align-center sfb" data-x="center"
							data-y="270" data-speed="400" data-start="1200"
							data-easing="easeOutExpo">
							<p>
								SMARTHire is most trusted job board, connecting the world's <br>
								brightest minds with resume database loaded with talents.
							</p>
						</div>

						<div class="caption sfb" data-x="center" data-y="400"
							data-speed="400" data-start="1600" data-easing="easeOutExpo">
							<a href="proceedLoginPage" class="slider-button">Hire</a> <a
								href="proceedLoginPage" class="slider-button">Work</a>
						</div></li>

				</ul>
			</div>
		</div>


		<!-- Content
================================================== -->
		<div class="container">

			<!-- Blog Posts -->
			<div class="eleven columns">
				<div class="padding-right">

					<!-- Post -->
					<div class="post-container">
						<div class="post-img">
							<a href="blog-single-post.jsp"><img
								src="newscripts/images/blog-post-01.jpg" alt=""></a>
							<div class="hover-icon"></div>
						</div>
						<div class="post-content">
							<h3>
								<a href="#">Hey Job Seeker, It's Time To Get Up And Get
									Hired</a>
							</h3>
							<div class="meta-tags">
								<span>October 10, 2015</span> <span><a href="#">0
										Comments</a></span>
							</div>
							<p>Nam nisl lacus, dignissim ac tristique ut, scelerisque eu
								massa. Vestibulum ligula nunc, rutrum in malesuada vitae, tempus
								sed augue. Curabitur quis lectus quis augue dapibus facilisis.
								Vivamus tincidunt orci est, in vehicula nisi eleifend ut.
								Vestibulum sagittis varius orci vitae.</p>
							<a class="button" href="blog-single-post.html">Read More</a>
						</div>
					</div>

					<!-- Post -->
					<div class="post-container">
						<div class="post-img">
							<a href="blog-single-post.jsp"><img
								src="newscripts/images/blog-post-02.jpg" alt=""></a>
							<div class="hover-icon"></div>
						</div>
						<div class="post-content">
							<h3>
								<a href="#">How to "Woo" a Recruiter and Land Your Dream Job</a>
							</h3>
							<div class="meta-tags">
								<span>September 12, 2015</span> <span><a href="#">0
										Comments</a></span>
							</div>
							<p>Nam nisl lacus, dignissim ac tristique ut, scelerisque eu
								massa. Vestibulum ligula nunc, rutrum in malesuada vitae, tempus
								sed augue. Curabitur quis lectus quis augue dapibus facilisis.
								Vivamus tincidunt orci est, in vehicula nisi eleifend ut.
								Vestibulum sagittis varius orci vitae.</p>
							<a class="button" href="blog-single-post.html">Read More</a>
						</div>
					</div>

					<!-- Post -->
					<div class="post-container">
						<div class="post-img">
							<a href="blog-single-post.jsp"><img
								src="newscripts/images/blog-post-03.jpg" alt=""></a>
							<div class="hover-icon"></div>
						</div>
						<div class="post-content">
							<h3>
								<a href="#">11 Tips to Help You Get New Clients Through Cold
									Calling</a>
							</h3>
							<div class="meta-tags">
								<span>August 27, 2015</span> <span><a href="#">0
										Comments</a></span>
							</div>
							<p>Nam nisl lacus, dignissim ac tristique ut, scelerisque eu
								massa. Vestibulum ligula nunc, rutrum in malesuada vitae, tempus
								sed augue. Curabitur quis lectus quis augue dapibus facilisis.
								Vivamus tincidunt orci est, in vehicula nisi eleifend ut.
								Vestibulum sagittis varius orci vitae.</p>
							<a class="button" href="blog-single-post.html">Read More</a>
						</div>
					</div>

					<!-- Pagination -->
					<div class="pagination-container">
						<nav class="pagination">
							<ul>
								<li><a href="#" class="current-page">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
							</ul>
						</nav>

						<nav class="pagination-next-prev">
							<ul>
								<li><a href="#" class="prev">Previous</a></li>
								<li><a href="#" class="next">Next</a></li>
							</ul>
						</nav>
					</div>

				</div>
			</div>
			<!-- Blog Posts / End -->


			<!-- Widgets -->
			<div class="five columns blog">

				<!-- Search -->
				<div class="widget">
					<h4>Search</h4>
					<div class="widget-box search">
						<div class="input">
							<input class="search-field" type="text"
								placeholder="To search type and hit enter" value="" />
						</div>
					</div>
				</div>

				<div class="widget">
					<h4>Got any questions?</h4>
					<div class="widget-box">
						<p>If you are having any questions, please feel free to ask.</p>
						<a href="contact.html" class="button widget-btn"><i
							class="fa fa-envelope"></i> Drop Us a Line</a>
					</div>
				</div>

				<!-- Tabs -->
				<div class="widget">

					<ul class="tabs-nav blog">
						<li class="active"><a href="#tab1">Popular</a></li>
						<li><a href="#tab2">Featured</a></li>
						<li><a href="#tab3">Recent</a></li>
					</ul>

					<!-- Tabs Content -->
					<div class="tabs-container">

						<div class="tab-content" id="tab1">

							<!-- Recent Posts -->
							<ul class="widget-tabs">

								<!-- Post #1 -->
								<li>
									<div class="widget-thumb">
										<a href="blog-single-post.html"><img
											src="newscripts/images/blog-widget-01.png" alt="" /></a>
									</div>

									<div class="widget-text">
										<h5>
											<a href="blog-single-post.html">How to "Woo" a Recruiter
												and Land Your Dream Job</a>
										</h5>
										<span>September 12, 2015</span>
									</div>
									<div class="clearfix"></div>
								</li>

								<!-- Post #2 -->
								<li>
									<div class="widget-thumb">
										<a href="blog-single-post.html"><img
											src="newscripts/images/blog-widget-02.png" alt="" /></a>
									</div>

									<div class="widget-text">
										<h5>
											<a href="blog-single-post.html">Hey Job Seeker, It's Time
												To Get Up And Get Hired</a>
										</h5>
										<span>October 10, 2015</span>
									</div>
									<div class="clearfix"></div>

								</li>

								<!-- Post #3 -->
								<li>
									<div class="widget-thumb">
										<a href="blog-single-post.html"><img
											src="newscripts/images/blog-widget-03.png" alt="" /></a>
									</div>

									<div class="widget-text">
										<h5>
											<a href="blog-single-post.html">11 Tips to Help You Get
												New Clients Through Cold Calling</a>
										</h5>
										<span>August 27, 2015</span>
									</div>
									<div class="clearfix"></div>
								</li>
							</ul>

						</div>

						<div class="tab-content" id="tab2">

							<!-- Featured Posts -->
							<ul class="widget-tabs">

								<!-- Post #1 -->
								<li>
									<div class="widget-thumb">
										<a href="blog-single-post.html"><img
											src="newscripts/images/blog-widget-02.png" alt="" /></a>
									</div>

									<div class="widget-text">
										<h5>
											<a href="blog-single-post.html">Hey Job Seeker, It's Time
												To Get Up And Get Hired</a>
										</h5>
										<span>October 10, 2015</span>
									</div>
									<div class="clearfix"></div>

								</li>

								<!-- Post #2 -->
								<li>
									<div class="widget-thumb">
										<a href="blog-single-post.html"><img
											src="newscripts/images/blog-widget-01.png" alt="" /></a>
									</div>

									<div class="widget-text">
										<h5>
											<a href="blog-single-post.html">How to "Woo" a Recruiter
												and Land Your Dream Job</a>
										</h5>
										<span>September 12, 2015</span>
									</div>
									<div class="clearfix"></div>
								</li>

								<!-- Post #3 -->
								<li>
									<div class="widget-thumb">
										<a href="blog-single-post.html"><img
											src="newscripts/images/blog-widget-03.png" alt="" /></a>
									</div>

									<div class="widget-text">
										<h5>
											<a href="blog-single-post.html">11 Tips to Help You Get
												New Clients Through Cold Calling</a>
										</h5>
										<span>August 27, 2015</span>
									</div>
									<div class="clearfix"></div>
								</li>
							</ul>
						</div>

						<div class="tab-content" id="tab3">

							<!-- Recent Posts -->
							<ul class="widget-tabs">

								<!-- Post #1 -->
								<li>
									<div class="widget-thumb">
										<a href="blog-single-post.jsp"><img
											src="newscripts/images/blog-widget-03.png" alt="" /></a>
									</div>

									<div class="widget-text">
										<h5>
											<a href="blog-single-post.jsp">11 Tips to Help You Get
												New Clients Through Cold Calling</a>
										</h5>
										<span>August 27, 2015</span>
									</div>
									<div class="clearfix"></div>
								</li>

								<!-- Post #2 -->
								<li>
									<div class="widget-thumb">
										<a href="blog-single-post.html"><img
											src="newscripts/images/blog-widget-02.png" alt="" /></a>
									</div>

									<div class="widget-text">
										<h5>
											<a href="blog-single-post.html">Hey Job Seeker, It's Time
												To Get Up And Get Hired</a>
										</h5>
										<span>October 10, 2015</span>
									</div>
									<div class="clearfix"></div>

								</li>

								<!-- Post #3 -->
								<li>
									<div class="widget-thumb">
										<a href="blog-single-post.html"><img
											src="newscripts/images/blog-widget-01.png" alt="" /></a>
									</div>

									<div class="widget-text">
										<h5>
											<a href="blog-single-post.html">How to "Woo" a Recruiter
												and Land Your Dream Job</a>
										</h5>
										<span>September 12, 2015</span>
									</div>
									<div class="clearfix"></div>
								</li>
							</ul>
						</div>

					</div>
				</div>


				<div class="widget">
					<h4>Social</h4>
					<ul class="social-icons">
						<li><a class="facebook" href="#"><i class="icon-facebook"></i></a></li>
						<li><a class="twitter" href="#"><i class="icon-twitter"></i></a></li>
						<li><a class="gplus" href="#"><i class="icon-gplus"></i></a></li>
						<li><a class="linkedin" href="#"><i class="icon-linkedin"></i></a></li>
					</ul>

				</div>

				<div class="clearfix"></div>
				<div class="margin-bottom-40"></div>

			</div>
			<!-- Widgets / End -->


		</div>


		<!-- Footer
================================================== -->
		<div class="margin-top-55"></div>

		<div id="footer">
			<!-- Main -->
			<div class="container">

				<div class="seven columns">
					<h4>About</h4>
					<p>Morbi convallis bibendum urna ut viverra. Maecenas quis
						consequat libero, a feugiat eros. Nunc ut lacinia tortor morbi
						ultricies laoreet ullamcorper phasellus semper.</p>
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
							<li><a class="facebook" href="#"><i
									class="icon-facebook"></i></a></li>
							<li><a class="twitter" href="#"><i class="icon-twitter"></i></a></li>
							<li><a class="gplus" href="#"><i class="icon-gplus"></i></a></li>
							<li><a class="linkedin" href="#"><i
									class="icon-linkedin"></i></a></li>
						</ul>
						<div class="copyrights">
							© Copyright 2016 by <a href="#">SMARTHire Global Inc.</a>. All
							Rights Reserved.
						</div>
					</div>
				</div>
			</div>

		</div>

		<!-- Back To Top Button -->
		<div id="backtotop">
			<a href="#"></a>
		</div>

	</div>
	<!-- Wrapper / End -->


	<!-- Scripts
================================================== -->
	<script src="<c:url value="newscripts/scripts/jquery-2.1.3.min.js"/>"></script>
	<script src="<c:url value="newscripts/scripts/custom.js"/>"></script>
	<script src="<c:url value="newscripts/scripts/jquery.superfish.js"/>"></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.themepunch.tools.min.js"/>"></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.themepunch.revolution.min.js"/>"></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.themepunch.showbizpro.min.js"/>"></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.flexslider-min.js"/>"></script>
	<script src="<c:url value="newscripts/scripts/chosen.jquery.min.js"/>"></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.magnific-popup.min.js"/>"></script>
	<script src="<c:url value="newscripts/scripts/waypoints.min.js"/>"></script>
	<script
		src="<c:url value="newscripts/scripts/jquery.counterup.min.js"/>"></script>
	<script src="<c:url value="newscripts/scripts/jquery.jpanelmenu.js"/>"></script>
	<script src="<c:url value="newscripts/scripts/stacktable.js"/>"></script>
	<script src="<c:url value="newscripts/scripts/headroom.min.js"/>"></script>

</body>
</html>