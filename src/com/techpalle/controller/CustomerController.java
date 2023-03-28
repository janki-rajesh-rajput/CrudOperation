package com.techpalle.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techpalle.dao.CustomerDao;
import com.techpalle.model.Customer;

@WebServlet("/")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		String path=request.getServletPath();
		switch(path)
		{
		case "/delete":
			deleteCustomer(request,response);
			break;
		case "/edit":
			editCustomer(request,response);
			break;
		case "/editForm":
			getEditForm(request,response);
			break;
		case "/insertForm": 
			getInsertForm(request,response);
		break;
		case "/add":
			getAddCustomer(request,response);
		break;
		default:
			getStartUpPage(request,response);
		break;
		}
	}

		private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
			//read the id from url
			int i=Integer.parseInt(request.getParameter("id"));
			
			//call the dao method to delete the row in database
			CustomerDao.deleteCustomer(i);
			
			//redirect user to customer list page
			try {
				response.sendRedirect("list");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
	}

		private void editCustomer(HttpServletRequest request, HttpServletResponse response) {

			int i=Integer.parseInt(request.getParameter("tbid"));
			String n=request.getParameter("tbname");
			String e=request.getParameter("tbemail");
			long m=Long.parseLong(request.getParameter("tbmobail"));
			
			Customer c= new Customer(i,n,e,m);
			CustomerDao.editCustomer(c);
			//redirect user to customer list page
			try {
				response.sendRedirect("list");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	}

		private void getEditForm(HttpServletRequest request, HttpServletResponse response) {

			//fetch the id from url:
			int i=Integer.parseInt(request.getParameter("id"));
			Customer c=CustomerDao.getOneCustomer(i);
			
			
			try {
				
				//send  c object to jsp page
				RequestDispatcher rd=request.getRequestDispatcher("customer-form.jsp");
				request.setAttribute("customer", c);
				rd.forward(request, response);
				
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

		private void getInsertForm(HttpServletRequest request, HttpServletResponse response) {
		
			try {
				RequestDispatcher rd=request.getRequestDispatcher("customer-form.jsp");
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

		private void getAddCustomer(HttpServletRequest request, HttpServletResponse response) {

			//read the data from customer form page
			String n=request.getParameter("tbname");
			String e=request.getParameter("tbemail");
			long m=Long.parseLong(request.getParameter("tbmobail"));
			
			//store the admin given data into model
			Customer c=new Customer(n,e,m);
			
			//call the method addCustomer present in dao(insert customer data to db)
			CustomerDao.addCustomer(c);
			
			//redirect Admin to homePage(customer-list page)
			getStartUpPage(request,response);
			 
	}

		private void getStartUpPage(HttpServletRequest request, HttpServletResponse response) {
		
			try {
				ArrayList<Customer> alCustomer = CustomerDao.getAllCustomer();
				
				RequestDispatcher rd=request.getRequestDispatcher("customer-list.jsp");
				request.setAttribute("al", alCustomer);
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
