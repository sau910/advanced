

package com.zensar;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zensar.bean.Employee;

public class EmployeeController extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		
		String requestAction = request.getParameter("requestAction");
		if(requestAction.equalsIgnoreCase("viewAll")) {
		System.out.println("I am inside do get now");
		EmployeRepository employeeRepository = new EmployeRepository();
		List<Employee> listOfAllEmployees = employeeRepository.getAllEmployees();
		System.out.println("listOfAllEmployees"+listOfAllEmployees);
		
		RequestDispatcher rd = request.getRequestDispatcher("viewAllEmployees.jsp");
		request.setAttribute("listOfAllEmployees", listOfAllEmployees);
		try {
			rd.forward(request,response);
		}catch(Exception e) {
			System.out.println("Exception Occured:"+e);
		}
		}
		else if(requestAction.equalsIgnoreCase("delete")) {
			System.out.println("We are going to delete");
			EmployeRepository employeeRepository = new EmployeRepository();
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			employeeRepository.deleteEmployee(employeeId);
		
			//RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			try {
				//rd.forward(request, response);
				response.sendRedirect("index.jsp");
			} catch (Exception e) {
				System.out.println("Exception Occured:"+e);
			}
		}
		else if(requestAction.equalsIgnoreCase("openUpdateForm")) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		  //connect to db using repository and fetch the details of this employee
			EmployeRepository employeeRepository = new EmployeRepository();
			Employee employee = employeeRepository.getEmployee(employeeId);
			
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("updateEmployeeForm.jsp");
			request.setAttribute("employee", employee);
			try {
				rd.forward(request,response);
			}catch(Exception e) {
				System.out.println("Exception Ocured"+e);
			}
          }
		else if(requestAction.equalsIgnoreCase("update")) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String employeeName =request.getParameter("employeeName");
			String designation = request.getParameter("designation");
			int salary =Integer.parseInt(request.getParameter("salary"));
			String gender = request.getParameter("gender");
			String city = request.getParameter("city");
			
			Employee employee = new Employee(employeeId, employeeName, designation, salary, gender, city);
			EmployeRepository employeRepository = new EmployeRepository();
			employeRepository.updateEmployee(employee);
			
			try {
				response.sendRedirect("index.jsp");
			}catch(Exception e) {
				System.out.println("Exception Occured"+e);
			}
			
		} 
		
		else if(requestAction.equalsIgnoreCase("add")) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String employeeName =request.getParameter("employeeName");
			String designation = request.getParameter("designation");
			int salary =Integer.parseInt(request.getParameter("salary"));
			String gender = request.getParameter("gender");
			String city = request.getParameter("city");
			
			
			System.out.println("employeeId:"+employeeId);
			System.out.println("employeeName"+employeeName);
			System.out.println("designation:"+designation);
			System.out.println("salary:"+salary);
			System.out.println("gender:"+gender);
			System.out.println("city:"+city);
			
			
			
			EmployeRepository  employeeRespository = new EmployeRepository();
			Employee employee = new Employee(employeeId, employeeName, designation, salary, gender, city);
			employeeRespository.addEmploye(employee);
			
			try {
				response.sendRedirect("index.jsp");
			}catch(Exception e) {
				System.out.println("Exception Occured"+e);
			}
		   }
		else if(requestAction.equalsIgnoreCase("login")) {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			
			EmployeRepository employeeRespository = new EmployeRepository();
			boolean loginResult=employeeRespository.checkLogin(userName, password);
			if(loginResult) {
				try {
					response.sendRedirect("index.jsp");
				}catch(Exception e) {
					System.out.println("Exception Occured"+e);
				}
			}else {
				
				System.out.println("login Failed");
				request.setAttribute("errorMessage", "Invalid user or password");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				try {
					rd.forward(request,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
				
		
	}
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) {
	
		doGet(request, response);
	
	
	
	

	}
}
