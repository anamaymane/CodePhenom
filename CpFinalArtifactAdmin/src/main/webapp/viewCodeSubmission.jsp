<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.DAO,org.bson.Document"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@include file="/components/Header_navbar.html" %>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="lib/codemirror.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/theme/dracula.css">
    <!-- Main content -->
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style="margin-bottom: 54px">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>Submission of <a href = "./editUser?username=<%= (String)request.getAttribute("username")%>" class="d-block"> <%= (String)request.getAttribute("username") %>   </a> 
					in <%= (String)request.getAttribute("problemId") %> 
					at <%= (String)request.getAttribute("dateSubmissions") %></h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="./">Home</a></li>
						<li class="breadcrumb-item " ><a href="./submissionAdmin">submissions</a></li>
						<li class="breadcrumb-item active">submission details </li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<section>
	         <div class="row">
               <div class="col-sm-7" style="margin-right : 40px">
               	<label class="text-muted" >submission code </label>
                <div>
                  	<div>
                  		<textarea class="codeSource" id="codeSource" style="text-align: right!important;" name="codeSource"><%=(String)request.getAttribute("codeSource")%></textarea>
                  	</div>
                 </div>
             </div>
			<div class="col-12 col-md-12 col-lg-4 order-1 order-md-2">
              <h3 class="text-primary"><i class="far fa-file-code"></i> Submission</h3>
              <p class="text-muted">submission Id : <%= (String)request.getAttribute("submissionId")%>  </p>
              <br>
              
              <div class="text-muted">
                <p class="text-sm">problem Id 
                  <b class="d-block"><%= (String)request.getAttribute("problemId")%></b>
                </p>
                <p class="text-sm">User 
                  <a href = "./editUser?username=<%= (String)request.getAttribute("username")%>" class="d-block"><%= (String)request.getAttribute("username")%></a>
                </p>      
              <ul class="list-unstyled">
                <li>
                  	<a class="btn-link text-secondary"><i>Date Submission</i> <%= (String)request.getAttribute("dateSubmissions")%></a>
                </li>
                <li>
                  	<a  class="btn-link text-secondary"><i >memory :</i>  <%= (String)request.getAttribute("memoryResult")%> &nbsp; KB</a>
                </li>
                <li>
                  	<a class="btn-link text-secondary"><i class="far fa-fw fa-clock"></i> <%= (String)request.getAttribute("timeResult")%>  &nbsp; ms</a>
                </li>
                <li>
                	<% String verdict =(String)request.getAttribute("verdict");
                	if (verdict.compareTo("Accepted")==0) {%>
                  	<i >Verdict :</i><a class="btn-link text-success"> &nbsp;<%=(String)request.getAttribute("verdict")%></a>
					<%}
                	
                	else{ %>
				  	<i >Verdict :</i><a class="btn-link text-danger"> &nbsp;<%=(String)request.getAttribute("verdict")%></a>
				  	
				  	<%} %>
				  	
                </li>
              </ul>
              </div>
			</div>
          </div>
        </section>
     </div>
     
     
     <!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
<!-- Summernote -->
<script src="plugins/summernote/summernote-bs4.min.js"></script>
<!-- Page Script -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/mode/javascript/javascript.js"></script>
<script>

var config;
config ={
        lineNumbers: true,
        theme: "dracula",
        indentWithTabs: true,
        readOnly: true
    };
    
var editor = CodeMirror.fromTextArea(document.getElementById('codeSource'),config);
editor.setSize(900, 900);
</script>

 </html>
            
	
	
	