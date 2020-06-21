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
            	<ol class="breadcrumb float-sm-right">
              		<li class="breadcrumb-item"><a href="./">Home</a></li>
              		<li class="breadcrumb-item "><a href="./problemSet?display=all">Problems</a></li>
					<li class="breadcrumb-item ">add Language</li>              		
 	           </ol>
          </div><!-- /.col -->
		</div>
</div>
		<!-- /.container-fluid -->
	</section>
		
		<section class="content">
      <div class="row">
        <div class="col-12">
          <div class="card">
            <div class="card-header">
              <h3 class="card-title">Available Languages</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Language</th>
                  <th style="width: 10%; text-align:center">delete</th>
                </tr>
                </thead>
                <tbody>
					<%
						Iterator<org.bson.Document> cursorlang = DAO.supportedProgrammingLanguage();
								while(cursorlang.hasNext()){
									
									Document elementCurrentLanguage = cursorlang.next();
									String Language = elementCurrentLanguage.get("name").toString();
									String id = elementCurrentLanguage.get("_id").toString();
					%>
					<tr>
					<td> <%= Language %></td>
					<td><a href="./DeleteLanguage?id=<%=id%>" class="btn byn-danger btn-sm">
                              <i class="fas fa-trash"></i>
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
          <form action="./addLanguage" method="post" enctype="multipart/form-data">
         <label >Add new supported language</label>
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="Add language " name="LanguageName" required>
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-file"></span>
            </div>
          </div>
        </div>
         <div class="col-4">
          	<div class="col-lg-6">
                <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Submit</button>
          </div>
          <!-- /.col -->
        </div>
        </div>
        </form>
 </html>
 