<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.ctl.AttributeCtl"%>
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
       <form action="<%=ORSView.ATTRIBUTE_CTL%>" method="post">
      <%@ include file="Header.jsp"%>
       
       <jsp:useBean id="bean" class="in.co.rays.bean.AttributeBean"
			scope="request"></jsp:useBean>

		<div align="center">

			<h1>
				<font color="navy">Add Attribute</font>
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
					<th>Display Name:</th>
					<td><input type="text" name="displayName"
						placeholder="Enter Display Name"
						value="<%=DataUtility.getStringData(bean.getDisplayName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("displayName", request)%></font></td>
				</tr>
				<tr>
					<th>Data Type:</th>
					<td><input type="text" name="dataType" 
						placeholder="Enter Data Type"
						value="<%=DataUtility.getStringData(bean.getDataType())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("dataType", request)%></font></td>
				</tr>
				<tr>
					<th>Is Active:</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Yes", "Yes");
							map.put("No", "No");
						%> <%=HTMLUtility.getList("isActive", bean.getIsActive(), map)%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("isActive", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Description<span style="color: red">*</span></th>
					<td align="center"><textarea style="width: 173px; resize: none;"
							name="description" rows="3" placeholder="Enter Short description"><%
								if (bean != null && bean.getId() > 0) {
							%><%=DataUtility.getStringData(bean.getDescription())%><%
								}
							%></textarea></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr>
				<th></th>
					<td><input type="submit" name="operation"
						value="<%=AttributeCtl.OP_SAVE%>">&nbsp; <input type="submit"
						name="operation" value="<%=AttributeCtl.OP_RESET%>"></td>
				</tr>
			</table>
			</div>
	</form>	     
</body>
</html>