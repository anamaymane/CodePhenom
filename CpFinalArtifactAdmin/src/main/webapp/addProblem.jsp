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
                                <h1>Add new problem</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="./index.jsp">Home</a></li>
                                    <li class="breadcrumb-item"><a href="./problemSet?display=all">porblemSet</a></li>
                                    <li class="breadcrumb-item active">add new Problem</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                    <!-- /.container-fluid -->
                </section>
                <section>
                    <form action="./addProblem" method="post" enctype="multipart/form-data">
                        <!-- /.col -->
                        <div class="col-md-9">
                            <div class="card card-primary card-outline">
                                <div class="card-header">
                                    <h3 class="card-title">add problem</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" placeholder="Problem Name" name="problemName" required>
                                        <div class="input-group-append">
                                            <div class="input-group-text">
                                                <span class="fas fa-file"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" placeholder="ProblemId" name="problemId" required>
                                        <div class="input-group-append">
                                            <div class="input-group-text">
                                                <span class="fas "></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" placeholder="Problem Categorie" name="Type" required>
                                        <div class="input-group-append">
                                            <div class="input-group-text">
                                                <span class="fas "></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="Number" class="form-control" placeholder="Memory Limit in MB !" value="256" name="memlimit" required>
                                        <div class="input-group-append">
                                            <div class="input-group-text">
                                                <span class="fas "></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="Number" class="form-control" placeholder="Time limit in seconds !" value="2" name="timelimit" required>
                                        <div class="input-group-append">
                                            <div class="input-group-text">
                                                <span class="fas fa-"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="Number" class="form-control" placeholder="Number of points" name="points" required>
                                        <div class="input-group-append">
                                            <div class="input-group-text">
                                                <span class="fas "></span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <!-- select -->
                                        <div class="form-group">
                                            <label>Visible</label>
                                            <select class="form-control" name="visibility">
                                                <option>true</option>
                                                <option>false</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="card-header">
                                        <h3 class="card-title">
								<label>Problem Text</label>
							</h3>
                                    </div>
                                    <div>
                                        <textarea id="compose-textarea" name="problemtext" class="form-control" style="height: 300px">
                                            <div class="title">
                                                <h2> Number Game		</h2>
                                                <div class="limits">
                                                    <p>points : <strong>100</strong>
                                                    </p>
                                                    <p>Time limit per test : <strong>2</strong> seconds</p>
                                                    <p>Memory limit per test : <strong>256</strong> MB</p>

                                                </div>
                                            </div>
                                            <div class="description">
                                                <h3>Problem Description</h3>
                                                <div>
                                                    <p>Sabir likes to play with numbers.</p>
                                                    He defined a function G(<b>x</b>) as a multiplication of factorials of x digits. For exemple,<strong> G(254) = 2!*5!*4! = 5670 </strong>.
                                                    <p>He invited his roommate to guess a number with the following rules:</p>
                                                    First, Sabir have to choose a number <strong>x</strong> consisting of n digits (<b>x</b>>=1). This number may possibly start with zeroes. Then his roommate should find the maximum positive number <b>y</b> satisfying the following two conditions:

                                                    <br>
                                                    <ol>
                                                        <li><b>y</b> doesn't contain digit 0 or digit 1.</li>
                                                        <li> G(<b>x</b>) = G(<b>y</b>)</li>
                                                    </ol>
                                                    Help his roommate find such a number.

                                                </div>
                                            </div>
                                            <div class="input-format">
                                                <h3>Input format</h3>
                                                <div>
                                                    The first line contains an integer <b>n</b>, the number of digits in <b>x</b>.</p>
                                                    The second line contains n digits of <b>x</b>. There is at least one digit in <b>x</b> that is larger than 1.
                                                </div>
                                            </div>
                                            <div class="output-format">
                                                <h3>Output format</h3>
                                                <div>
                                                    Output a maximum possible integer satisfying the conditions above.
                                                    <b>There should be neither zeroes nor ones in this number decimal representation.</b>
                                                </div>
                                            </div>
                                            <div class="constraints">
                                                <h3>Constraints</h3>
                                                <ul>
                                                    <li>1 &le; <strong>n</strong> &le; 20<sup></li>
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
                								 4223<br />
                									</td>
              						                <td valign="top">332222</td>
            									     </tr>
          									 </tbody>
        					</table>
        					<table>
          						<thead>
            						<td>Input</td>
            						<td>Output</td>
          						</thead>
         						<tbody>
            						<tr>
              						 <td>
                					5<br />
                					77777<br />
                					</td>
              						<td valign="top">77777	</td>
            						</tr>
          							</tbody>
        					</table>
      					</div>						
                      <p>Explication </p>
                      <p>explication</p>
                </textarea>
						</div>
					</div>
					<!-- /.card-body -->
					<div class="card-footer">
						<div class="float-right">
							<button type="submit" class="btn btn-primary">
								<i class="fas fa-plus"></i> &nbsp; Add
							</button>
						</div>
						<a class="btn btn-default " href="./problemSet"> <i
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