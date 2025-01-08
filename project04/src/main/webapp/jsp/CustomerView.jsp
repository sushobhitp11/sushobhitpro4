<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.ctl.CustomerCtl"%>
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
      <form action="<%=ORSView.CUSTOMER_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.bean.CustomerBean"
			scope="request"></jsp:useBean>

		<div align="center">

			<h1>
				<font color="navy">Add Customer</font>
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
					<th>Client Name:</th>
					<td><input type="text" name="clientName"
						placeholder="Enter Client Name"
						value="<%=DataUtility.getStringData(bean.getClientName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("clientName", request)%></font></td>
				</tr>
				<tr>
					<th>Location:</th>
					<td><input type="text" name="location"
						placeholder="Enter Location"
						value="<%=DataUtility.getStringData(bean.getLocation())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("location", request)%></font></td>
				</tr>
				<tr>
					<th>Contact Number:</th>
					<td><input type="text" name="contactNumber"
						placeholder="Enter Contact Number"
						value="<%=DataUtility.getStringData(bean.getContactNumber())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("contactNumber", request)%></font></td>
				</tr>
				<tr>
					<th>Importance:</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("High", "High");
							map.put("Medium", "Medium");
							map.put("Low", "Low");
							
							String hlist = HTMLUtility.getList("importance", bean.getImportance(), map);
						%> <%=hlist%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("importance", request)%></font></td>
				</tr>
				<th></th>
				<td><input type="submit" name="operation"
					value="<%=CustomerCtl.OP_SAVE%>">&nbsp; <input type="submit"
					name="operation" value="<%=CustomerCtl.OP_RESET%>"></td>
				</tr>
			</table>
		</div>
	</form>

</body>
</html>