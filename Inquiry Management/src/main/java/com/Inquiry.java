package com;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Inquiry {
	
	
	public String  getAllInquiry() throws ClassNotFoundException, SQLException{
	
		
		Connection con=DatabaseConnection.getConnection();
		String output="";
		try {
			output = "<table border=\"1\" class=\"table\"> <tr>"
					+ "<th>Inquiry ID</th><th>Customer ID</th> <th>Customer Name</th>"
			 		+ "<th>Customer NIC</th>"
			 		+ "<th>Contact No</th>"
			 		+ "<th>Inquiry Type</th>"
			 		+ "<th>Inquiry Date</th>"
			 		+ "<th>Description</th>"
			 		+ "<th>Update</th>  <th>Remove</th></tr>";
			String query="select * from inquiry ";
			PreparedStatement preparedstatement=con.prepareStatement(query);
			ResultSet result = preparedstatement.executeQuery(query);
			
			while(result.next()) {
				 String inquiryId =Integer.toString(result.getInt(1));
				 String customerId =result.getString(2);
				 String customerName =result.getString(3);
				 String customerNIC =result.getString(4);
				 String contactNo =result.getString(5);
				 String inquiryType =result.getString(6);
				 String inquiryDate =result.getString(7);
				 String description =result.getString(8);
				 
				// Add into the html table
				 	
					output += "<tr><td>"+inquiryId+ "</td>";
					output += "<td>" + customerId + "</td>";
					output += "<td>" + customerName + "</td>";
					output += "<td>" + customerNIC + "</td>";
					output += "<td>" + contactNo + "</td>";
					output += "<td>" + inquiryType + "</td>";
					output += "<td>" + inquiryDate + "</td>";
					output += "<td>" +  description + "</td>";
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
							 + "class='btnUpdate btn btn-success' data-inquiryId='" + inquiryId + "'></td>"
							 + "<td><input name='btnRemove' type='button' value='Remove' "
							 + "class='btnRemove btn btn-danger' data-inquiryId='" + inquiryId + "'></td></tr>"; 
			
			}
			con.close();
			output += "</table>";
			
		
		}
		catch(Exception e) 
		{
			output = "Error while reading the inquiry.";
			System.err.println(e.getMessage());
		}
		return output;
	
	}
		

	

	//Create Inquiry 
	public String createInquiry( String customerId, String customerName, String customerNIC, String contactNo,
			String inquiryType, String inquiryDate, String description) {
		
		String output = ""; 
		try {
			Connection con=DatabaseConnection.getConnection();
			String query="insert into  inquiry(`CustomerID`,`CustomerName`,`CustomerNIC`,`ContactNo`,`Inquirytype`,`InquiryDate`,`Description`) "+" values(?,?,?,?,?,?,?) ";
			PreparedStatement preparedstatement=con.prepareStatement(query);
			
		
			preparedstatement.setString(1, customerId);
			preparedstatement.setString(2, customerName);
			preparedstatement.setString(3, customerNIC);
			preparedstatement.setString(4, contactNo);
			preparedstatement.setString(5, inquiryType);
			preparedstatement.setString(6, inquiryDate);
			preparedstatement.setString(7, description);
			
			preparedstatement.execute();
			
			con.close(); 
			
			String newInquiry = getAllInquiry(); 
			output = "{\"status\":\"success\",\"data\":\""+newInquiry+"\"}"; 
		
		}catch(Exception e) 
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Inquiry information.\"}"; 
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//Update Inquiry 
	public String updateInquiry(String inquiryId,String customerId, String customerName, String customerNIC, String contactNo,
			String inquiryType, String inquiryDate, String description) {
		
		String output = ""; 
		try {
			Connection con=DatabaseConnection.getConnection();
			
			String query="update inquiry set CustomerID=?,CustomerName=?,CustomerNIC=?,ContactNo=?,Inquirytype=?,InquiryDate=?,Description=?  where InquiryID=? ";
			PreparedStatement preparedstatement=con.prepareStatement(query);
			
			preparedstatement.setString(1, customerId);
			preparedstatement.setString(2, customerName);
			preparedstatement.setString(3, customerNIC);
			preparedstatement.setString(4, contactNo);
			preparedstatement.setString(5, inquiryType);
			preparedstatement.setString(6, inquiryDate);
			preparedstatement.setString(7, description);
			preparedstatement.setInt(8, Integer.parseInt(inquiryId)); 
			preparedstatement.execute();
			
			con.close(); 
			
			String newInquiry = getAllInquiry(); 
			output = "{\"status\":\"success\",\"data\":\""+newInquiry+"\"}"; 
		
		}catch(Exception e) 
		{
			output = "{\"status\":\"error\",\"data\":\"Error while updating the Inquiry information.\"}"; 
			System.err.println(e.getMessage());
		}
		return output;
	}
	//delete Inquiry
	public String deleteInquiry(String inquiryId) {
		
		String output = ""; 
		try {
			Connection con=DatabaseConnection.getConnection();
			String query="DELETE FROM inquiry WHERE inquiryId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			preparedStmt.setInt(1, Integer.parseInt(inquiryId)); 
			preparedStmt.execute();
			con.close(); 
			String newInquiry = getAllInquiry(); 
			output = "{\"status\":\"success\",\"data\":\""+newInquiry+"\"}"; 
		}catch(Exception e) 
		{
			output = "{\"status\":\"error\",\"data\":\"Error while deleting the inquiry information.\"}";
			System.err.println(e.getMessage());
		}
		return output;


	}




}
