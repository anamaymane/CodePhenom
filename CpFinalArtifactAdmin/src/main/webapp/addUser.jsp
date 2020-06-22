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
					<h1>Add new user</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="./index.jsp">Home</a></li>
						<li class="breadcrumb-item"><a href="./Users">Users</a></li>
						<li class="breadcrumb-item active">add User</li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->

	</section>
	<section>
		<div class="card">
			<div class="card-body register-card-body">
				<p class="login-box-msg">Add new user</p>

				<form action="./addUser" method="post" enctype="multipart/form-data">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Username"
							name="username" required>
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-user"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Full Name"
							name="fullName" required>
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-user"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="email" class="form-control" placeholder="Email"
							name="email" required>
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-envelope"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control" placeholder="Password"
							name="password" required>
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control"
							placeholder="Re-type password" name="rpassword" required>
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="exampleInputFile">File input</label>
						<div class="input-group">
							<div class="custom-file">
								<input type="file" class="custom-file-input" name="fileName" > <label class="custom-file-label"
									for="fileName">Choose file</label>
							</div>
						</div>
					</div>
					<div class="row">
						<!-- /.col -->
						<div class="col-4">
							<div class="col-lg-6">
								<div class="form-group">
									<button type="submit" class="btn btn-primary btn-block">Submit</button>
								</div>
								<!-- /.col -->
							</div>
						</div>
					</div>

				</form>
			</div>
		</div>
	</section>
</div>