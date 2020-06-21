<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.DAO,org.bson.Document,cpFinal.mongodbConnection"%>
<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.DAO,org.bson.Document"%>
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
					<h1>edit problem</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="./index.jsp">Home</a></li>
						<li class="breadcrumb-item"><a href="./problemSet?display=all">problems</a></li>
						<li class="breadcrumb-item active">edit <%= (String)request.getAttribute("problemId") %> </li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<section>
       <form action="./editProblem" method="post" enctype="multipart/form-data">
      <!-- /.col -->
          <div class="col-md-9">
            <div class="card card-primary card-outline">
              <div class="card-header">
                <h3 class="card-title ">edit problem</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
          <h3 class="card-title"><label>Problem Name</label></h3>
           <div class="input-group mb-3">
							<input type="text" class="form-control"
								placeholder="Problem Name"
								value=<%=(String)request.getAttribute("name")%>
								name="problemName" required>
							<div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-file"></span>
            </div>
          </div>
        </div>
          <h3 class="card-title"><label>ProblemId</label></h3>
        <div class="input-group mb-3" >
           <input readonly="readonly" type="text" class="form-control" placeholder="ProblemId" value="<%= (String)request.getAttribute("problemId") %>" name="problemId" >
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas "></span>
            </div>
          </div>
        </div>
        <h3 class="card-title"><label>Problem Categorie</label></h3>
         <div class="input-group mb-3">
          <input type="text" class="form-control"placeholder="Problem Categorie" value="<%= (String)request.getAttribute("type") %>" name="Type" required>
          <div class="input-group-append"> 
            <div class="input-group-text">
              <span class="fas "></span>
            </div>
          </div>
        </div>
        <h3 class="card-title"><label>Memory Limit in MB </label></h3>
        <div class="input-group mb-3">
          <input type="Number" class="form-control" placeholder="Memory Limit in MB !" value="<%= request.getAttribute("memlimit") %>" name="memlimit" required>
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas "></span>
            </div>
          </div>		
        </div>
        <h3 class="card-title"><label>Time limit in seconds </label></h3>        
        <div class="input-group mb-3">
          <input type="Number" class="form-control" placeholder="Time limit in seconds !" value="<%= request.getAttribute("timelimit") %> "  name="timelimit" required>
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-"></span>
            </div>
          </div>
        </div>
        <h3 class="card-title"><label>Number of points </label></h3>           
        <div class="input-group mb-3">
          <input type="Number" class="form-control"placeholder="Number of points" value="<%=request.getAttribute("difficulty") %> " name="points" required>
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas "></span>
            </div>
          </div>
                  </div>
          
          <div class="col-sm-6">
                      <!-- select -->
                      <div class="form-group">
                        <label >Visible</label>
                        <select class="form-control" name ="visibility">
                          <option>true</option>
                          <option>false</option>
                        </select>
                      </div>
                    </div>
          <div class="card-header">
                <h3 class="card-title"><label>Problem Text</label></h3>
           </div>
           <div>
             <textarea id="compose-textarea" name= "problemtext" class="form-control" style="height: 300px">
             <%= (String)request.getAttribute("problemtext") %> 
             
             <!--  
                   <h1><u>Problem Name</u></h1>
                   <h4>description</h4>
                      <p>
                      But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain
                        was born and I will give you a complete account of the system, and expound the actual teachings
                        of the great explorer of the truth, the master-builder of human happiness. No one rejects,
                        dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know
                        how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again
                        is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain,
                        but because occasionally circumstances occur in which toil and pain can procure him some great
                        pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise,
                        except to obtain some advantage from it? But who has any right to find fault with a man who
                        chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that
                        produces no resultant pleasure? On the other hand, we denounce with righteous indignation and
                        dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so
                        blinded by desire, that they cannot foresee 
                      </p>
            	<div class="input-format">
        					<h3>Input format</h3>
        						<div>
          							The first line of the input contains <strong>N</strong>, the size of the array A .<br />
          							Each line of the next <strong>N</strong> lines contains an integer
          							<i>a<sub>i</sub></i>, the ith element of the array A.
        						</div>
      			</div>
      					<div class="output-format">
        					<h3>Output format</h3>
        						<div>
          							Output a single positive integer.y A.
        						</div>
      					</div>
							<div class="constraints">
        					<h3>Constraints</h3>
        						<ul>
          							<li>2 &le; <i>N</i> &le; 250<sup></li>
          							<li>1 &le; <i>a<sub>i</sub></i> &le; 40<sup></li>
        						</ul>
      					</div>
      					<div class="sample">
       					 <h3>Sample Input/Output</h3>
        					<table>
          						<thead>
            						<td>Input</td>
            						<td>Output</td>
          						</thead>
          						<tbody>
            						<tr>
              							<td>
                							4<br />
                							2<br />
               							 	2<br />
                							2<br />
                							3<br />
                						</td>
              							<td  class="align-top">4</td>
            						</tr>
          						</tbody>
        					</table>
      					</div>						
                      <p>Explication </p>
                      <p>explication</p>
                      
                 -->
                </textarea>
                
              </div>
              </div>
              <!-- /.card-body -->
              <div class="card-footer">
                <div class="float-right">
                  <button type="submit" class="btn btn-primary"><i class="fas fa-plus"></i> &nbsp; edit</button>
                </div>
                  <a  class="btn btn-default " href="./problemSet">
                              <i class="fas fa-times"> &nbsp; Discard</i>         
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
      table,
      tr,
      td {
        border: 0.5px solid black;
        padding: 7px;
      }
    </style>
   
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
   
    <script>
    
  $(function () {
    //Add text editor
    $('#compose-textarea').summernote()
  })
</script>
    </html>