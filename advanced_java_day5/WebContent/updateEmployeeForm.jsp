<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="com.zensar.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<%Employee employee =(Employee)request.getAttribute("employee"); %>
<body>
<h2>Update Employee Form</h2>
<form action="updateEmployee" method="get">
<input type="hidden" name="requestAction" value="update">
<table>
       <tr>
          <td>Employee Id:</td>
          <td> <input type="text" name="employeeId" value="<%=employee.getEmployeeId() %>"/></td>
       </tr>
        <tr>
          <td>Employee Name:</td>
          <td> <input type="text" name="employeeName" value="<%=employee.getEmployeeName()%>"/></td>
         </tr>
         <tr> 
          <td>Designation:</td>
          <td> <input type="text" name="designation" value="<%=employee.getDesignation() %>"/></td>
         </tr> 
         <tr>
          <td>Salary:</td>
          <td> <input type="text" name="salary" value="<%=employee.getSalary() %>"/></td>
          
         
       </tr>
       <tr>
       <td>Gender:</td>
           <td> <input type="radio" name="gender" value="male" <%if(employee.getGender().equalsIgnoreCase("male")){ %>checked <%} %>> Male<br></td>
           <td>  <input type="radio" name="gender" value="female" <%if(employee.getGender().equalsIgnoreCase("female")){ %>checked <%} %>> Female<br></td>
       </tr>
         <tr>
         
          <td>
        <label for="hometown">Choose a city:     </label>
        <% if(employee.getCity().equalsIgnoreCase("pune")) {%>
        <select id="hometown" name="city">
            <option value="pune" selected>Pune</option>
            <option value="akola">akola</option>
            <option value="mumbai">mumbai</option>
            <option value="nagpur">nagpur</option>
        </select>
        <% } else if(employee.getCity().equalsIgnoreCase("akola")) {%>
        <select id="hometown" name="city">
            <option value="pune">Pune</option>
            <option value="akola" selected>akola</option>
            <option value="mumbai">mumbai</option>
            <option value="nagpur">nagpur</option>
        </select>
        <% } else if(employee.getCity().equalsIgnoreCase("mumbai")) {%>
        <select id="hometown" name="city">
            <option value="pune">Pune</option>
            <option value="akola" >akola</option>
            <option value="mumbai"selected>mumbai</option>
            <option value="nagpur">nagpur</option>
        </select>
         <% } else  {%>
        <select id="hometown" name="city">
            <option value="pune">Pune</option>
            <option value="akola" >akola</option>
            <option value="mumbai">mumbai</option>
            <option value="nagpur"selected>nagpur</option>
        </select>
        <% }%>
        
        
        </tr>
          <td> &nbsp; </td>
           <td> <input type="submit" value="Update Employee" /></td>
           </tr>
    </table>
    <br><br><br>
    <a href="logout.jsp">Log Out</a>

</form>
</body>
</html>