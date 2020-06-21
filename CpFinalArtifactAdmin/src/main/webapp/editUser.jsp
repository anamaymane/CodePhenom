<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.DAO,org.bson.Document,cpFinal.mongodbConnection"%>
<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.DAO,org.bson.Document"%>
<%@include file="/components/Header_navbar.html"%>
<%@include file="/components/footer.jsp"%>

<!DOCTYPE html>
<html>

<!-- Main content -->
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style="margin-bottom: 54px">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>Profile</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item"><a href="./Users">Users</a></li>
						<li class="breadcrumb-item active">edit <%=(String) request.getAttribute("fullName")%></li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->

	</section>

	<!-- Main content -->
	<section class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6">

					<!-- Profile Image -->
					<div class="card card-primary card-outline">
						<div class="card-body box-profile">
							<div class="text-center">
								<img style="width: 70px; height: 70px;"
									class="profile-user-img img-fluid img-circle"
									src="<%=request.getContextPath()%>/img/users/<%=(String) request.getAttribute("username")%>">
							</div>
							<div>
								<a class="btn btn-danger btn-sm" data-toggle="modal"
									data-target="#modal-lg<%=(String) request.getAttribute("username")%>">
									<i class="fas fa-trash"></i> delete user
								</a>
							</div>
							<div class="modal fade"
								id="modal-lg<%=(String) request.getAttribute("username")%>">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header text-center">
											<h4 class="modal-title">
												<span class="badge badge-warning"> irreversible
													operation !!</span>
											</h4>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<p>
												are you sure you want delete user :
												<%=(String) request.getAttribute("username")%>
											</p>
											<a class="btn btn-danger btn-sm"
												href="./deleteUser?username=	<%=(String) request.getAttribute("username")%>">
												<i class="fas fa-trash"></i> Yes,I'm sure
											</a>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">No</button>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->

							</div>
							<h3 class="profile-username text-center"><%=(String) request.getAttribute("username")%></h3>
							<ul class="list-group list-group-unbordered mb-3">
								<li class="list-group-item"><b>Full Name</b> <a
									class="float-right"><%=(String) request.getAttribute("fullName")%>
								</a></li>
								<li class="list-group-item"><b>Score</b> <a
									class="float-right"><%=(String) request.getAttribute("score")%>
								</a></li>
								<li class="list-group-item"><b>Date Registration</b> <a
									class="float-right"><%=(String) request.getAttribute("dateRegistration")%>
								</a></li>
								<li class="list-group-item"><b>Last Login</b> <a
									class="float-right"><%=(String) request.getAttribute("lastLogin")%></a>
								</li>
								<li class="list-group-item text-center"><b></b> <a
									class="btn btn-default btn-sm"
									href="./userSubmission?username=<%=(String) request.getAttribute("username")%> ">
										<i class="fas fa-file"></i> Show submissions
								</a></li>
							</ul>
						</div>
						<!-- /.card-body -->
					</div>

					<!-- /.card -->
					<!-- About Me Box -->
					<div class="card card-primary">
						<div class="card-header">
							<h3 class="card-title">About Me</h3>
						</div>
						<!-- /.card-header -->
						<div class="card-body">
							<strong><i class="fas fa-book mr-1"></i> Brief
								description</strong>

							<p class="text-muted"><%=(String) request.getAttribute("description")%></p>

							<hr>

							<strong><i class="fas fa-pencil-alt mr-1"></i>
								Programming languages used</strong>

							<p class="text-muted">
								<%
									ArrayList<Document> listDoc3 = DAO
																	.programmingLanguageUsedByUser((String) request.getAttribute("username"));

															if (listDoc3.size() > 0) {
																for (Document doc : listDoc3) {
								%>
								<span class="tag tag-danger"><%=doc.get("_id")%></span>
								<%
									}
															} else {
								%>
								<span class="tag tag-danger">No Submission have been sent</span>
								<%
									}
								%>
							</p>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
				<div class="offset-md-2 col-md-2">
					<div >
						<%
							if (DAO.userSolvedCategoryNumber((String) request.getAttribute("username")).size() > 0) {
						%>
						<div class="row mb-2 justify-content-center">
							<div>Number of problems solved per category</div>
							<canvas id="myChart" width="200" height="260"></canvas>
						</div>
						<%
							} else {
						%>
						<div class="row justify-content-center">
							<div class="callout callout-info">
								<h5>No problem has been solved by this user</h5>
							</div>
						</div>
						<%
							}
						%>
					</div>
					<%
						if (DAO.submissionStatistics((String) request.getAttribute("username")).size() > 0) {
					%>
					<div class="row mb-2 justify-content-center"
						style="margin-top: 40px">
						<div>Statistics of submissions</div>
						<canvas id="myChart2" width="200" height="300"></canvas>
					</div>
					<%
						} else {
					%>
					<div class="row">
						<div class="callout callout-info">
							<h5>No solution has been submited by this user</h5>
						</div>
					</div>
					<%
						}
					%>
				</div>
			</div>
			<!-- /.col -->

		</div>
		<!-- /.row -->
</div>
<!-- /.container-fluid -->
</section>
<!-- /.content -->
</div>

<%@include file="components/footer.jsp"%>
<script>
	
<%ArrayList<Document> listDoc = DAO
					.userSolvedCategoryNumber((String) request.getAttribute("username"));
			String data = "[";
			String label = "[";
			for (Document doc : listDoc) {
				data = data + "'" + doc.get("total") + "'" + ",";
				label = label + "'" + doc.get("_id") + "'" + ",";
			}

			if (data.length() > 2 && label.length() > 2) {
				data = data.substring(0, data.length() - 1);
				label = label.substring(0, label.length() - 1);
			}
			data = data + "]";
			label = label + "]";%>
	var ctx = document.getElementById('myChart').getContext('2d');
	var myChart = new Chart(ctx, {
		type : 'pie',
		data : {
			labels :
<%=label%>
	,
			datasets : [ {
				label : 'labels',
				data :
<%=data%>
	,

				backgroundColor : [ 'rgba(54, 162, 235, 0.2)',

				'rgba(255, 99, 132, 0.2)', 'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
						'rgba(255, 159, 64, 0.2)' ],
				borderColor : [ 'rgba(54, 162, 235, 1)',

				'rgba(255, 99, 132, 1)', 'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)' ],
				borderWidth : 1
			} ]
		}
	});
</script>
<script>
	
<%ArrayList<Document> listDoc2 = DAO
					.submissionStatistics((String) request.getAttribute("username"));
			String data2 = "[";
			String label2 = "[";
			for (Document doc : listDoc2) {
				data2 = data2 + "'" + doc.get("total") + "'" + ",";
				label2 = label2 + "'" + doc.get("_id") + "'" + ",";
			}

			if (data2.length() > 2 && label2.length() > 2) {
				data2 = data2.substring(0, data2.length() - 1);
				label2 = label2.substring(0, label2.length() - 1);
			}
			data2 = data2 + "]";
			label2 = label2 + "]";%>
	var ctx = document.getElementById('myChart2').getContext('2d');
	var myChart = new Chart(ctx, {
		type : 'doughnut',
		data : {
			labels :
<%=label2%>
	,
			datasets : [ {
				label : 'labels',
				data :
<%=data2%>
	,

				backgroundColor : [ 'rgba(0,128,0,0.2)',

				'rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)',
						'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
						'rgba(255, 159, 64, 0.2)' ],
				borderColor : [ 'rgba(0,128,0,0.2)',

				'rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)',
						'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)' ],
				borderWidth : 1
			} ]
		}
	});
</script>