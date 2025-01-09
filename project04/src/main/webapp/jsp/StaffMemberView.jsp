<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.ctl.StaffMemberCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
      <form action="<%=ORSView.STAFFMEMBER_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.bean.StaffMemberBean"
			scope="request"></jsp:useBean>

		<div align="center">

			<h1>
				<font color="navy">Add Staff Member</font>
			</h1>

			<h3>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</h3>
			<h3>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</h3>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<tr>
					<th>Full Name:</th>
					<td><input type="text" name="fullName"
						placeholder="Enter Full Name"
						value="<%=DataUtility.getStringData(bean.getFullName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("fullName", request)%></font></td>
				</tr>
				<tr>
					<th>Joining Date:</th>
					<td><input type="date" name="joiningDate" id="udate"
						placeholder="Enter Joining Date"
						value="<%=DataUtility.getStringData(bean.getJoiningDate())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("joiningDate", request)%></font></td>
				</tr>
				<tr>
					<th>Division:</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Software Development", "Software Development");
							map.put("Quality Assurance (QA)", "Quality Assurance (QA)");
							map.put("IT Support", "IT Support");
							map.put("DevOps", "DevOps");
							map.put("Project Management", "Project Management");
							String hlist = HTMLUtility.getList("division", bean.getDivision(), map);
						%> <%=hlist%>
					</td>
				<tr>
					<th>Previous Employer:</th>
					<td><input type="text" name="previousEmployer"
						placeholder="Enter Division"
						value="<%=DataUtility.getStringData(bean.getPreviousEmployer())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("previousEmployer", request)%></font></td>
				</tr>
				
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("importance", request)%></font></td>
				</tr>
				<th></th>
				<td><input type="submit" name="operation"
					value="<%=StaffMemberCtl.OP_SAVE%>">&nbsp; <input type="submit"
					name="operation" value="<%=StaffMemberCtl.OP_RESET%>"></td>
				</tr>
			</table>
		</div>
	</form>

</body>
</html>