<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.DAO,org.bson.Document,cpFinal.mongodbConnection"%>
<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.DAO,org.bson.Document"%>
<%@include file="/components/Header_navbar.html"%>
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
					<h1>Add new Announcement</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="./index.jsp">Home</a></li>
						<li class="breadcrumb-item"><a
							href="./problemSet?display=all">Announcement</a></li>
						<li class="breadcrumb-item active">add new Announcement</li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
		<%
			String id = (String) request.getAttribute("id");
		%>
	</section>
	<section>
		<form action="./EditAnnouncement" method="post"
			enctype="multipart/form-data">
			<!-- /.col -->
			<div class="col-md-9">
				<div class="card card-primary card-outline">
					<div class="card-header">
						<h3 class="card-title">add Announecment</h3>
					</div>
					<!-- /.card-header -->
					<div class="card-body">
						<div class="input-group mb-3">
							<input type="text" class="form-control"
								placeholder="Announcement Title" name=titre
								value="<%=(String) request.getAttribute("title")%>" required>
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-file"></span>
								</div>
							</div>
						</div>
						<div class="input-group mb-3">
							<input type="text" class="form-control"
								placeholder="Announcement id" name="id" value="<%=id%>"
								readonly="readonly">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-file"></span>
								</div>
							</div>
						</div>
						<div class="card-header">
							<h3 class="card-title">
								<label>Announcement Body</label>
							</h3>
						</div>
						<div>
							<textarea id="compose-textarea" name="Body" class="form-control"
								style="height: 300px">
									<%=(String) request.getAttribute("Body")%>
                            </textarea>
						</div>
					</div>
					<!-- /.card-body -->
					<div class="card-footer">
						<div class="float-right">
							<button type="submit" class="btn btn-primary">
								<i class="fas fa-plus"></i> &nbsp; Edit
							</button>
						</div>
						<a class="btn btn-default " href="./Announcement"> <i
							class="fas fa-times"> &nbsp; Discard</i>
						</a>
					</div>
					<!-- /.card-footer -->
				</div>
				<!-- /.card -->
			</div>
			<!-- /.col -->
		</form>
	</section>
	<style>
.container {
	width: 80%;
	margin-left: 10%;
	padding: 15px 0;
	font-size: 17px;
}

.title {
	text-align: center;
}

.title .limits {
	font-size: 13px;
	margin-top: -10px;
	margin-bottom: 20px;
}

.sample table {
	width: 100%;
	border-collapse: collapse;
}

.sample thead {
	font-weight: bold;
}

table, tr, td {
	border: 0.5px solid black;
	padding: 7px;
}
</style>
	<script src="../../plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="../../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../../dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="../../dist/js/demo.js"></script>
	<!-- Summernote -->
	<script src="../../plugins/summernote/summernote-bs4.min.js"></script>
	<!-- Page Script -->
	<script>
		$(function() {
			//Add text editor
			$('#compose-textarea').summernote()
		})
	</script>
</html>