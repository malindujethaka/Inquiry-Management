package com;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InquiryAPI
 */
@WebServlet("/InquiryAPI")
public class InquiryAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Inquiry inquiryObj =new Inquiry();

  
    public InquiryAPI() {
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String output=inquiryObj.createInquiry(
				request.getParameter("customerId"),
				request.getParameter("customerName"), 
				request.getParameter("customerNIC"), 
				request.getParameter("contactNo"),
				request.getParameter("inquiryType"), 
				request.getParameter("inquiryDate"), 
				request.getParameter("description"));
				
		response.getWriter().write(output);
		doGet(request, response);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
//			String output = inquiryObj.updateInquiry(
//					paras.get("hidCustomerIDSave").toString(),
//					paras.get("customerId").toString(),
//					paras.get("customerName").toString(), 
//					paras.get("customerNIC").toString(), 
//					paras.get("contactNo").toString(),
//					paras.get("inquiryType").toString(), 
//					paras.get("inquiryDate").toString(), 
//					paras.get("description").toString());
//			response.getWriter().write(output);
	}

	private static Map getParasMap(HttpServletRequest request) 
	{ 
		Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? 
			 scanner.useDelimiter("\\A").next() : ""; 
			 scanner.close(); 
			 String[] params = queryString.split("&"); 
			 for (String param : params) 
			 {
				 String[] p = param.split("=");
				 map.put(p[0], p[1]); 
			} 
		}catch (Exception e) 
		{ 
		} 
		return map; 
	}


	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		String output =inquiryObj.deleteInquiry(paras.get("inquiryId").toString());
		response.getWriter().write(output);
	}

}
