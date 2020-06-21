<%@ page
	import="java.util.List,java.util.Iterator,cpFinal.DAO,org.bson.Document"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/components/Header_navbar.html"%>
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
						<li class="breadcrumb-item "><a
							href="./problemSet?display=all">Mailbox</a></li>
					</ol>
				</div>
				<!-- /.col -->
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<div class="col-md-9">
		<div class="card">
			<div class="card-header p-2">
				<ul class="nav nav-pills">
					<li class="nav-item"><a class="nav-link active"
						href="#activity" data-toggle="tab">Received messages</a></li>
					<li class="nav-item"><a class="nav-link" href="#timeline"
						data-toggle="tab">Send message</a></li>
				</ul>
			</div>
			<!-- /.card-header -->
			<div class="card-body">
				<div class="tab-content">
					<div class="active tab-pane" id="activity">
						<div class="col-md-12">
							<div class="card card-primary card-outline">
								<div class="card-header">
									<h3 class="card-title">Inbox</h3>
									<!-- /.card-tools -->
								</div>
								<!-- /.card-header -->
								<div class="card-body p-0">
									<div class="table-responsive mailbox-messages">
										<div class="card-body">
											<table id="example2" class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>Sender</th>
														<th>Object</th>
														<th>Time</th>
														<th style="text-align: center"><i class="fas fa-eye"></i>
															View message body</th>
													</tr>
												</thead>
												<tbody>
													<%
														Iterator<org.bson.Document> cursor1 = (Iterator<org.bson.Document>) request
																.getAttribute("messageReceivedList");
														while (cursor1.hasNext()) {
															Document element = cursor1.next();
															String sender = element.get("sender").toString();
															String date = element.get("date").toString();
															String object = element.get("object").toString();
															String id = element.get("_id").toString();
															String content = element.get("content").toString();
															String receiver = element.get("receiver").toString();
													%>
													<tr>
														<%
															if (sender.equals("admin")) {
														%>
														<td class="mailbox-name"><span
															class="badge badge-warning">You</span></td>
														<%
															} else {
														%>
														<td class="mailbox-name"><%=sender%></td>
														<%
															}
														%>
														<td class="mailbox-subject"><b><%=object%></b></td>
														<td class="mailbox-date"><%=date%></td>
														<td><button type="button"
																class="btn btn-block btn-primary btn-sm"
																data-toggle="modal" data-target="#modal-lg<%=id%>">View
																message</button></td>
														<div class="modal fade" id="modal-lg<%=id%>">
															<div class="modal-dialog modal-lg">
																<div class="modal-content">
																	<div class="modal-header text-center">
																		<h4 class="modal-title"><%=object%>
																			<span class="badge badge-warning">to : <%
																				if (sender.equals("admin")) {
																			%> <%=receiver%> <%
 	} else {
 %> <%=sender%> <%
 	}
 %></span>
																		</h4>
																		<button type="button" class="close"
																			data-dismiss="modal" aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																	</div>
																	<div class="modal-body">
																		<p><%=content%>
																		</p>
																	</div>
																	<div class="modal-footer justify-content-center">
																		<button type="button" class="btn btn-default"
																			data-dismiss="modal">Close</button>
																	</div>
																</div>
																<!-- /.modal-content -->
															</div>
															<!-- /.modal-dialog -->
														</div>
													</tr>
													<%
														}
													%>
												
												</tfoot>
											</table>
										</div>


										<!-- /.table -->
									</div>
									<!-- /.mail-box-messages -->
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
					</div>
					<!-- /.tab-pane -->
					<div class="tab-pane" id="timeline">
						<ul class="nav nav-tabs" id="myTab" role="tablist">
							<li class="nav-item"><a class="nav-link" id="profile-tab"
								data-toggle="tab" href="#profile" role="tab"
								aria-controls="profile" aria-selected="false">To other users</a>
							</li>
						</ul>
						<div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="home" role="tabpanel"
								aria-labelledby="home-tab">
								<div class="card-body">
									<form class="form-horizontal" method="post"
										action="./SendMessage">
										<input type="hidden" value="admin" name="sender">
										<div class="form-group row">
											<label for="inputEmail" class="col-sm-2 col-form-label">Username</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="inputEmail"
													placeholder="username of receiver" name="receiver" required>
											</div>
										</div>
										<div class="form-group row">
											<label for="inputName2" class="col-sm-2 col-form-label">Object</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="inputName2"
													placeholder="Your message object" name="object" required>
											</div>
										</div>
										<div class="form-group row">
											<label for="inputExperience" class="col-sm-2 col-form-label">Message</label>
											<div class="col-sm-10">
												<textarea class="form-control" id="inputExperience"
													placeholder="The body of the message" name="content"
													required></textarea>
											</div>
										</div>
										<div class="form-group row justify-content-center">
											<div>
												<button type="submit" class="btn btn-danger">Send</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>

					</div>
					<!-- /.tab-pane -->
				</div>
				<!-- /.tab-content -->
			</div>
			<!-- /.card-body -->
		</div>
		<!-- /.nav-tabs-custom -->
	</div>
	<!-- /.col -->
</div>
</body>
</html>