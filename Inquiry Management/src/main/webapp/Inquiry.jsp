<%@page import="com.Inquiry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inquiry Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/inquiry.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Inquiry Management V10.1</h1>
				<form id="formInquiry" name="formInquiry">
					Customer ID: <input id="customerId" name="customerId"
						type="text" class="form-control form-control-sm"> <br>
						
					Customer Name: <input id="customerName" name="customerName"
						type="text" class="form-control form-control-sm"> <br>
						
					Customer NIC: <input id="customerNIC"
						name="customerNIC" type="text"
						class="form-control form-control-sm"> <br> 
						
					Contact NO: <input
						id="contactNo" name="contactNo" type="text"
						class="form-control form-control-sm"> <br> 
						
					Inquiry Type:
					<input id="inquiryType" name="inquiryType" type="text"
						class="form-control form-control-sm"> <br> 
						
					Inquiry Date: <input
						id="inquiryDate" name="inquiryDate" type="text"
						class="form-control form-control-sm"> <br> 
						
					Description: <input
						id="description" name="description" type="text"
						class="form-control form-control-sm"> <br> 
				
					<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> 
						
					<input type="hidden"
						id="hidInquiryIDSave" name="hidInquiryIDSave" value="">
				</form>
				<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divInquiryGrid">
					<%
						Inquiry inquiryObj = new Inquiry();
					out.print(inquiryObj.getAllInquiry());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>