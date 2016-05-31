<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>车易拍Portal</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="/css/portal/font-awesome.min.css">
	<link rel="stylesheet" href="/css/portal/portal.min.css">
	<link rel="stylesheet" href="/css/portal/_all-skins.min.css">
	<link rel="stylesheet" href="/css/portal/blue.css">
	<link rel="stylesheet" href="/css/portal/morris.css">
	<link rel="icon" type="image/png" href="/images/favicon.ico" sizes="32x32" />
	<!--[if lt IE 9]>
	<script src="/js/ie/html5shiv.min.js"></script>
	<script src="/js/ie/respond.min.js"></script>
	<![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
	<jsp:directive.include file="../header.jsp"/>
	<jsp:directive.include file="../left.jsp"/>
	<div class="content-wrapper">
		<section class="content-header">
			<h1>
				500 Error Page
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">500 Error</li>
			</ol>
		</section>
		<section class="content">
			<div class="error-page">
				<h2 class="headline text-red">500</h2>
				<div class="error-content">
					<h3><i class="fa fa-warning text-red"></i> Oops! Something went wrong.</h3>
					<p>
						We will work on fixing that right away.
						Meanwhile, you may <a href="../../index.html">return to dashboard</a> or try using the search
						form.
					</p>
					<form class="search-form">
						<div class="input-group">
							<input type="text" name="search" class="form-control" placeholder="Search">
							<div class="input-group-btn">
								<button type="submit" name="submit" class="btn btn-danger btn-flat"><i
										class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- /.error-page -->
		</section>
	</div>
	<jsp:directive.include file="../footer.jsp"/>
	<jsp:directive.include file="../right.jsp"/>
	<div class="control-sidebar-bg"></div>
</div>
<script src="/js/jQuery/jquery.min.js"></script>
<script src="/js/bootstrap/bootstrap.min.js"></script>
<script src="/js/jQuery/jquery.slimscroll.min.js"></script>
<script src="/js/portal/app.min.js"></script>
<script src="/js/tools/dashboard.js"></script>
<script src="/js/portal/portal.js"></script>
</body>
</html>