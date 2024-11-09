<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.ctl.PositionCtl"%>
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
<script src="/project04/js/datepicker.js"></script>
</head>
<body>
       <form action="<%=ORSView.POSITION_CTL%>" method="post">
      <%@ include file="Header.jsp"%>
       
       <jsp:useBean id="bean" class="in.co.rays.bean.PositionBean"
			scope="request"></jsp:useBean>

		<div align="center">

			<h1>
				<font color="navy">Add Position</font>
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
					<th>Designation:</th>
					<td><input type="text" name="designation"
						placeholder="Enter Designation"
						value="<%=DataUtility.getStringData(bean.getDesignation())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("designation", request)%></font></td>
				</tr>
				<tr>
					<th>Opening Date:</th>
					<td><input type="date" name="openingDate" id="udate"
						placeholder="Enter Opening Date"
						value="<%=DataUtility.getStringData(bean.getOpeningDate())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("openingDate", request)%></font></td>
				</tr>
				<tr>
					<th>Required Experience:</th>
					<td><input type="text" name="requiredExperience"
						placeholder="Enter Required Experience"
						value="<%=DataUtility.getStringData(bean.getReqiredExperience())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("requiredExperience", request)%></font></td>
				</tr>
				<tr>
					<th>Condition:</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("open", "open");
							map.put("close", "close");
							map.put("hold", "hold");
							map.put("coming soon", "coming soon");
						%> <%=HTMLUtility.getList("condition", bean.getCondition(), map)%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("condition", request)%></font></td>
				</tr>
				<th></th>
					<td><input type="submit" name="operation"
						value="<%=PositionCtl.OP_SAVE%>">&nbsp; <input type="submit"
						name="operation" value="<%=PositionCtl.OP_RESET%>"></td>
				</tr>
			</table>
			</div>
	</form>	     
</body>
</html>