$(document).on("click", "#btnSave", function(event)
{ 
	alert("malith");
// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
	// Form validation-------------------
	var status = validateInquiryForm(); 
	if (status != true) 
		 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
		 } 
	 // If valid------------------------
	var type = ($("#hidInquiryIDSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
		 url : "InquiryAPI", 
		 type : type, 
		 data : $("#formInquiry").serialize(), 
		 dataType : "text", 
	 	complete : function(response, status) 
		 { 
		 onInquirySaveComplete(response.responseText, status); 
		 } 
	 }); 
});
function onInquirySaveComplete(response, status)
{ 
	if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
	 	 { 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#divInquiryGrid").html(resultSet.data); 
	 	 } else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
	 } else if (status == "error") 
	 { 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	 } else
	 { 
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
	 }
	$("#hidInquiryIDSave").val(""); 
	$("#formInquiry")[0].reset(); 
}
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidInquiryIDSave").val($(this).data("inquiryid"));
		 $("#customerId").val($(this).closest("tr").find('td:eq(0)').text());  
		 $("#customerName").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#customerNIC").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#contactNo").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#inquiryType").val($(this).closest("tr").find('td:eq(4)').text()); 
		 $("#inquiryDate").val($(this).closest("tr").find('td:eq(5)').text()); 
		 $("#description").val($(this).closest("tr").find('td:eq(6)').text()); 
		 
		});




$(document).on("click", ".btnRemove", function(event)
{ 
	 $.ajax( 
	 { 
		 url : "InquiryAPI", 
		 type : "DELETE", 
		 data : "inquiryId=" + $(this).data("inquiryid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onInquiryDeleteComplete(response.responseText, status); 
		 } 
	 }); 
});
	
		
function onInquiryDeleteComplete(response, status)
{ 
	if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully deleted."); 
			 $("#alertSuccess").show(); 
			 $("#divInquiryGrid").html(resultSet.data); 
		 } else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
		 } else if (status == "error") 
		 { 
			 $("#alertError").text("Error while deleting."); 
			 $("#alertError").show(); 
		 } else
		 { 
			 $("#alertError").text("Unknown error while deleting.."); 
			 $("#alertError").show(); 
	 } 
}
// CLIENT-MODEL================================================================
function validateInquiryForm()
{
	// customerId
	if ($("#customerId").val().trim() == "")
	{
	return "Insert Customer Id.";
	}
	// customerName
	if ($("#customerName").val().trim() == "")
	{
	return "Insert Customer Name.";
	}
	// customerNIC
	if ($("#customerNIC").val().trim() == "")
	{
	return "Insert Customer NIC.";
	
    }
	// contactNo
	if ($("#contactNo").val().trim() == "")
	{
	return "Insert Contact NO.";
	}
	// inquiryType
	if ($("#inquiryType").val().trim() == "")
	{
	return "Insert Inquiry Type.";
	
    }
	// inquiryDate
	if ($("#inquiryDate").val().trim() == "")
	{
	return "Insert Inquiry Date.";
	}
	// description
	if ($("#description").val().trim() == "")
	{
	return "Insert Description.";
	
    }
	return true;
}