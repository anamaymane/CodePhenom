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
					<h1>
						Add Test case for
						<%=(String)request.getAttribute("problemId")%>
					</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item"><a
							href="./problemSet?display=all">problemSet</a></li>
						<li class="breadcrumb-item active">add test case for <%=(String)request.getAttribute("problemId")%></li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<section>
		<form action="./addTestCase" method="post"
			enctype="multipart/form-data">
			<div class="form-group ">
				<input type="hidden" name="problemId"
					value="<%=(String)request.getAttribute("problemId")%>"> <label
					for="exampleInputFile">data input</label>
				<div class="input-group col-sm-8">
					<div class="custom-file">
						<input type="file" class="custom-file-input" name="inputfile"
							required> <label class="custom-file-label"
							for="inputfile">Choose input</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="exampleInputFile">data output</label>
				<div class="input-group col-sm-8">
					<div class="custom-file">
						<input type="file" class="custom-file-input" name="outputfile"
							required> <label class="custom-file-label"
							for="outputfile">Choose Output</label>
					</div>
				</div>
			</div>
			<!-- select -->
			<div class="form-group col-sm-5">
				<label> test type</label> <select class="form-control"
					name="typeOfTest">
					<option>simple</option>
					<option>secret</option>
				</select>
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
	</section>

	<!-- existed testcases here  -->

	<section class="content">
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">Existing testcases</h3>
					</div>
					<!-- /.card-header -->
					<div class="card-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>test id</th>
									<th>Date</th>
									<th>test input</th>
									<th>test Output</th>
									<th>test Type</th>
									<th style="width: 10%; text-align: center">edit</th>
								</tr>
							</thead>
							<tbody>
								<%
									Iterator<org.bson.Document> cursortests = DAO.getProblemTestCases((String)request.getAttribute("problemId"));
											while(cursortests.hasNext()){
												
												Document elementCurrentTestCase = cursortests.next();
												String _id = elementCurrentTestCase.get("_id").toString();
												String input = elementCurrentTestCase.get("input").toString(); 
												String output = elementCurrentTestCase.get("output").toString(); 
												String typeOfTest = elementCurrentTestCase.get("typeOfTest").toString();
												String Date = elementCurrentTestCase.get("dateAjout").toString();
								%>
								<tr>
									<td><%= _id %></td>
									<td><%= Date %></td>
									<td><a href="<%= input %> " download>Download</a></td>
									<td><a href="<%=output %>" download>Download</a></td>
									<td><%=typeOfTest %></td>
									<td><a href="./DeleteTestCase?id=<%=_id%>&problemId=<%=(String)request.getAttribute("problemId") %>"
										class="btn byn-danger btn-sm"> <i class="fas fa-trash"></i>
											delete
									</a></td>
								</tr>
								<% }%>

							</tbody>
						</table>
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
</html>


<%@include file="components/footer.jsp"%>
