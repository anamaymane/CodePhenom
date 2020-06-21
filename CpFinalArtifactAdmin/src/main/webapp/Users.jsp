<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.DAO,org.bson.Document"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@include file="/components/Header_navbar.html" %>

<!DOCTYPE html>

<html>
  <!-- Content Wrapper. Contains page content -->

  <div class="content-wrapper">

    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <a href="./addUser" class="btn btn-primary btn-sm "  >Add user</a>
            	<i class="fas fa-user-plus"></i>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="./">Home</a></li>
              <li class="breadcrumb-item active">Users</li>

            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
  <!-- Content Wrapper. Contains page content -->
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-12">
          <div class="card">
            <div class="card-header">
              <h3 class="card-title">Users List</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Username</th>                
                  <th>Full name</th>
                  <th>e-mail</th>
                  <th>Score</th>
                  <th style="width: 10%; text-align:center">edit</th>
                </tr>
                </thead>
                <tbody>
					<%
						Iterator<org.bson.Document> cursorCurrentUser = (Iterator<org.bson.Document>) DAO.getAllUsersList();
								while(cursorCurrentUser.hasNext()){
									
									Document elementCurrentUser = cursorCurrentUser.next();
									String username = elementCurrentUser.get("username").toString();
									String mail = elementCurrentUser.get("email").toString(); 
									String full_name = elementCurrentUser.get("fullName").toString(); 
									String score = elementCurrentUser.get("score").toString();
					%>
					<tr>
					<td> <%= username %></td>
					<td> <%= full_name %></td>
					<td> <%= mail %></td>
					<td> <%=score %></td>
					<td><a href="./editUser?username=<%=username%>" class="btn btn-primary btn-sm">
                              <i class="fas fa-eye"></i>
                              show info
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
    $("#example1").DataTable();
    $('#example2').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false,
    });
  });
</script>
</body>

<%@include file="/components/footer.jsp" %>
</html>