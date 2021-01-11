package com.zensar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.zensar.bean.Employee;
import com.zensar.utility.DBUtil;



public class EmployeRepository {
	
	public void addEmploye(Employee employee) {
		Connection con = DBUtil.getMySqlDbConnection();
		String sql = "insert into employee values(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, employee.getEmployeeId());
			pst.setString(2, employee.getEmployeeName());
			pst.setString(3, employee.getDesignation());
			pst.setInt(4,employee.getSalary());
			pst.setString(5, employee.getGender());
			pst.setString(6, employee.getCity());
			int result = pst.executeUpdate();
			if(result==0) {
				System.out.println("Database Operation Fails");
			}else {
				System.out.println("Database Operation Successful");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Employee> getAllEmployees(){
		List<Employee> listOfAllEmployees = new ArrayList<Employee>();
		Connection con = DBUtil.getMySqlDbConnection();
		String sql = "select * from employee";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String employeeName = rs.getString("employee_name");
				String designation = rs.getString("designation");
				int salary = rs.getInt("salary");
				String gender = rs.getString("gender");
				String city = rs.getString("city");
				Employee employee = new Employee(employeeId, employeeName, designation, salary, gender, city);
				listOfAllEmployees.add(employee);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Occured"+e);
		}
		//System.out.println("listOfAllEmployees:"+listOfAllEmployees);
		return listOfAllEmployees;
	}
	
	
	public void deleteEmployee(int employeeId) {
		Connection con = DBUtil.getMySqlDbConnection();
		String sql = "delete from employee where employee_id=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, employeeId);
			int result = pst.executeUpdate();
			if(result==0) {
				System.out.println("Deletion Unsuccessful");
			}else {
				System.out.println("Deletion successful");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Occured:"+e);
		}
		
	}
	
	
	public Employee getEmployee(int employeeId) {
		Employee employee = null;
		//jdbc code to connect to database and fetch empoyee with this id
		Connection con = DBUtil.getMySqlDbConnection();
		String sql = "select * from employee where employee_id=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, employeeId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
			String 	employeeName = rs.getString("employee_name");
			String designation = rs.getString("designation");
			int salary = rs.getInt("salary");
			String gender = rs.getString("gender");
			String city = rs.getString("city");
			
			employee = new  Employee(employeeId, employeeName, designation, salary, gender, city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Occured:"+e);
		}
		return employee;
	}
	
	
	public void updateEmployee(Employee employee) {
		   Connection con = DBUtil.getMySqlDbConnection();
		   String sql = "update employee set employee_name=?,designation=?,salary=?,gender=?,city=? where employee_id=?";
		   try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, employee.getEmployeeName());
			pst.setString(2,employee.getDesignation());
			pst.setInt(3, employee.getSalary());
			pst.setString(4, employee.getGender());
			pst.setString(5,employee.getCity());
			pst.setInt(6, employee.getEmployeeId());
			int result = pst.executeUpdate();
			
			if(result ==0) {
				System.out.println("The employee given does not exist");
			}else {
				System.out.println("Successfully Updated");
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Occured:"+e);
		}
		   
	}
	
	public boolean checkLogin(String userName,String password) {
		boolean result= false;
		Connection con = DBUtil.getMySqlDbConnection();
		String sql = "select * from log_in where user_id=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String passwordDB = rs.getString("password");
				if(password.equals(passwordDB)) {
					result = true;
				}
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}	


