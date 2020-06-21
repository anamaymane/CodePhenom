<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.DAO,org.bson.Document"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@include file="/components/Header_navbar.html" %>


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
					<h1>Submissions of the user :  <%=(String)request.getAttribute("username")%></h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="./">Home</a></li>
						<li class="breadcrumb-item active" ></li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<!-- Main content -->
	<section class="content">
	<div class="row">
        <div class="col-12">
          <div class="card">
            <div class="card-header">
              <h3 class="card-title">submissions</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body">
              <table id="example2" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Submission</th>
                  <th>problemId</th>
                  <th>Submition date</th>
                  <th>Memory Result(KB)</th>
                  <th>Time Result(ms)</th>
                   <th >Verdict</th>
                </tr>
                </thead>
                <tbody>
                                                <%
                                                	Iterator<org.bson.Document> cursor = (Iterator<org.bson.Document>)
                                                							DAO.getSubmissions((String)request.getAttribute("username"));
                                                							while (cursor.hasNext()) {
                                                								
                                                								Document element = cursor.next();
                                                								String id = element.get("_id").toString();
                                                								String problemId = element.get("problemId").toString();
                                                								String dateSubmission = element.get("dateSubmission").toString();
                                                								String memoryResult = element.get("memoryResult").toString();
                                                								String timeResult = element.get("timeResult").toString();
                                                								String verdict = element.get("totalVerdict").toString();
                                                %>
								<tr>
				  <td><i class="fas fa-eye"></i>
						<a href="./ViewCodeSubmission?submissionId=<%=id%>">view code source</a>
				  </td>
                  <td><%= problemId %></td>
                  <td><%= dateSubmission %></td>
                  <td><%= memoryResult %></td>
                  <td><%= timeResult %></td>
                  
                  <%if (verdict.compareTo("Accepted")==0) {%>
                  <td class="text-success"> <%= verdict %></td>
					<%}else{ %>
				  <td class="text-danger"><%= verdict %> </td>
				  <%} %>
                </tr>
<%}%>
              </table>
              </div>
              </div>
            </div>
		</div>
		<!-- /.row -->
	</section>
	<!-- /.content -->
</div>
<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- DataTables -->
<script src="plugins/datatables/jquery.dataTables.js"></script>
<script src="plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
<!-- page script -->
<script>
  $(function () {
    $('#example2').DataTable({
      "paging": true,
      "lengthChange": true,
      "searching": true	,
      "ordering": true,
      "info": true,
      "autoWidth": false,
    });
  });
</script>
</html>