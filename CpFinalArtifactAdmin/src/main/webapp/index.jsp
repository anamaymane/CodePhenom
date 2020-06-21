<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.mongodbConnection,org.bson.Document,com.mongodb.BasicDBObject,cpFinal.DAO"%>
<%@include file="/components/Header_navbar.html"%>
<%@include file="/components/footer.jsp"%>
<!DOCTYPE html>
<html>
<!-- /.navbar -->
<!-- Content Wrapper. Contains page content -->

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Main content -->
	<!-- Content Header (Page header) -->
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0 text-dark">Dashboard</h1>
				</div>
				<!-- /.col -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">Dashboard</li>
					</ol>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /.content-header -->
	<section class="content">
		<div class="container-fluid">
			<!-- Small boxes (Stat box) -->
			<div class="row">
				<div class="col-lg-4 col-6">
					<!-- small box -->
					<div class="small-box bg-info">
						<div class="inner">
							<h3><%=DAO.TodaySubmission("all")%></h3>

							<p>Last 15 Days Submissions</p>
						</div>
						<div class="icon">
							<i class="ion ion-stats-bars"></i>
						</div>
					</div>
				</div>
				<!-- ./col -->
				<div class="col-lg-4 col-6">
					<!-- small box -->
					<div class="small-box bg-success">
						<div class="inner">
							<h3>
								<%=DAO.TodaySubmission("Accepted")%>
							</h3>

							<p>Accepted submissions in the Last 15 Days</p>
						</div>
						<div class="icon">
							<i class="ion ion-stats-bars"></i>
						</div>
					</div>
				</div>
				<!-- ./col -->
				<div class="col-lg-3 col-6">
					<!-- small box -->
					<div class="small-box bg-warning">
						<div class="inner">
							<h3><%=DAO.NumberOfUserRegistredLastMonth()%></h3>

							<p>Last 15 Days User Registrations</p>
						</div>
						<div class="icon">
							<i class="ion ion-person-add"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section>
		<div>
			<br>
			<div class="row  ">
				<div class=" col-6 my-auto">
					<h4>Number of submissions per verdict in the last 15 days</h4>

					<canvas id="myChart1" width="600" height="300"></canvas>
				</div>
				<div class="col-md-5 my-auto col-sm-4  ">
					<div class="info-box bg-info">
						<span class="info-box-icon"><i class="far fa-bookmark"></i></span>
						<div class="info-box-content">
							<span class="info-box-text">Problems</span> <span
								class="info-box-number"><%=DAO.getNumberOfProblems("all")%></span>
							<div class="progress">
								<div class="progress-bar" style="width: 70%"></div>
							</div>
							<span class="progress-description"><%=DAO.getNumberOfProblems("last15Day")%>
								added in 15 Days </span>
						</div>

					</div>
				</div>
			</div>

		</div>
	</section>
</div>

</html>

<script>
	var ctx = document.getElementById('myChart1').getContext('2d');
	var myChart = new Chart(ctx, {
		type : 'horizontalBar',
		data : {
			labels : [ "Accepted", "Wrong answer", "Time limit exceeded",
					"Compilation error", "Runtime error",
					"Memory limit exceeded" ],
			datasets : [ {
				label : "Number of submission",
				data : [
<%=DAO.TodaySubmission("Accepted")%>
	,
<%=DAO.TodaySubmission("Wrong answer")%>
	,
<%=DAO.TodaySubmission("Time limit exceeded")%>
	,
<%=DAO.TodaySubmission("Compilation error")%>
	,
<%=DAO.TodaySubmission("Runtime error")%>
	,
<%=DAO.TodaySubmission("Memory limit exceeded")%>
	],
				backgroundColor : [ 'rgba(0,128,0,0.2)',
						'rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)',
						'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
						'rgba(153, 33, 33, 0.2)' ],
				borderColor : [ 'rgba(0,128,0,0.2)', 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)', 'rgba(75, 192, 192, 1)',
						'rgba(153, 102, 255, 1)', 'rgba(153, 33, 33, 0.2)' ],
				borderWidth : 1
			} ]
		}
	});
</script>