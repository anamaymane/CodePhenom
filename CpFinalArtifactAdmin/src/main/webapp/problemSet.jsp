<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.DAO,org.bson.Document"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@include file="/components/Header_navbar.html" %>
<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.DAO,org.bson.Document"%>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2 ">
				<div class="col-sm-6">
            		<a href="./addProblem" class="btn btn-primary btn-sm "  >
            			<i class="nav-icon fas fa-plus "></i>
                  		 &nbsp; Add new problem
            		</a>
            	<a href="./addLanguage" class="btn btn btn-light btn-sm "  >
            			<i class="nav-icon fas fa-plus "></i>
                  		 &nbsp; Add new Language
            		</a>
            	</div>
          	<div class="col-sm-6">
            	<ol class="breadcrumb float-sm-right">
              		<li class="breadcrumb-item"><a href="./">Home</a></li>
              		<li class="breadcrumb-item ">Problems</li>
					              		
 	           </ol>
          </div><!-- /.col -->
		</div>
</div>
		<!-- /.container-fluid -->
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row justify-content-center" >
			<div class="col-10">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">List of available problems</h3>
					</div>
					<!-- /.card-header -->
					<div class="card-body">
						<form class="needs-validation" method="post"
							action="./problemSet?display=difficulty">
							<div class="row">
								<div class="col">
									<input type="text" class="form-control"
										placeholder="Minimum points" name="min" required>
								</div>
								<div class="col">
									<input type="text" class="form-control"
										placeholder="Maximum points" name="max" required>
								</div>
								<div class="col">
									<button type="submit" class="btn btn-warning">Search
										by difficulty</button>
								</div>
							</div>

						</form>
						<form method="post"
							action="./problemSet?display=type">
							<div class="row" style="margin-top: 20px">
								<div class="col">
									<div class="form-group">

										<select class="form-control" name="type" required>
											<option></option>
											<%
												ArrayList<Document> cursor3 = (ArrayList<Document>) request.getAttribute("categoryList");
																					for (Document doc : cursor3) {
											%>
											<option><%=(String)doc.get("_id")%></option>
											<%
												}
											%>
										</select>
									</div>
								</div>

								<div class="col">
									<button type="submit" class="btn btn-warning">Search
										by category</button>
								</div>
							</div>

						</form>
						</form>
						<table id="example2" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>external ID</th>
									<th>Problem Name</th>
									<th>Type</th>
									<th>Difficulty</th>
									<th>Solved</th>
									<th>time limit</th>
									<th>memory limit</th>
									<th> Number of submissions</th>
									<th>Test cases</th>
								</tr>
							</thead>
							<tbody>
								<%
									Iterator<org.bson.Document> cursor = (Iterator<org.bson.Document>) request.getAttribute("problemList");
															while (cursor.hasNext()) {
																Document element = cursor.next();
																String problemId = element.get("problemId").toString();
																String name = element.get("name").toString();
																String type = element.get("type").toString();
																String difficulty = element.get("difficulty").toString();
																String nbrOfSolve = DAO.problemNumberOfSolves(problemId);
																String timeLimit = element.get("timelimit").toString();
																String memoryLimit = element.get("memlimit").toString();
																int nbrOfTestCases = DAO.getNumberOfTestCases(problemId);
																int nbrOfSubmissions = DAO.getNumberOfSubmissionsPerProblem(problemId);
								%>
								<tr>
									<td><%=problemId%></td>
									<td>
									 <i class="fas fa-edit"></i>
										<a
										href="./editProblem?problemId=<%=problemId%>"><%=name%></a>
										</td>
									<td><%=type%></td>
									<td><%=difficulty%></td>
									<td><%=nbrOfSolve%></td>
									<td><%= timeLimit%></td>
									<td><%=memoryLimit%></td>
									<td><%=nbrOfSubmissions %> &nbsp; &nbsp; &nbsp;
									<a href="./ProblemSubmission?problemId=<%=problemId%>" ><i class="fas fa-eye" ></i></a>
									</td>										<td><%=nbrOfTestCases %> &nbsp; &nbsp; &nbsp;
									<a href="./addTestCase?problemId=<%=problemId%>" ><i class="fas fa-plus" ></i></a>
									</td>
							
								</tr>
								<%
									}
								%>
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

<!-- page script -->
<%@include file="components/footer.jsp"%>
<script>
  $(function () {
	  $('#example2').DataTable({
	      "paging": true,
	      "lengthChange": false,
	      "searching": false,
	      "ordering": false,
	      "info": false,
	      "autoWidth": false,
	    });
  });
  </script>

 </html>
 