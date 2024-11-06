<%@page import="in.co.rays.ctl.TimetableCtl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
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
      <form action="<%=ORSView.TIMETABLE_CTL%>" method="post">
      <%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.bean.TimetableBean"
			scope="request"></jsp:useBean>

		<%
			List courseList = (List) request.getAttribute("courseList");
			List subjectList = (List) request.getAttribute("subjectList");
		%>

		<div align="center">

			<h1>
				<font color="navy">Add Timetable</font>
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
					<th align="left">Semester<span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("1", "1");
							map.put("2", "2");
							map.put("3", "3");
							map.put("4", "4");
							map.put("5", "5");
							map.put("6", "6");
							map.put("7", "7");
							map.put("8", "8");
							/* map.put("9", "9");
							map.put("10", "10"); */

							String htmlList = HTMLUtility.getList("semester", bean.getSemester(), map);
						%> <%=htmlList%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("semester", request)%></font></td>
				</tr>
				<tr>
					<th>Description:</th>
					<td><input type="text" name="description"
						placeholder="Enter Short Description"
						value="<%=DataUtility.getStringData(bean.getDescription())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr>
					<th align="left">Exam Date<span style="color: red">*</span></th>
					<td>
						<div class="input-group">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
							<div data-datepicker date-format="MM/dd/yyyy" date-typer="true"
								date-max-limit="{{maxDateTime}}"
								date-min-limit="{{minDateTime1}}">
								<input type="text" name="examDate"
									placeholder="Select Date of Exam" readonly="readonly"
									value="<%=DataUtility.getDateString(bean.getExamDate())%>">
							</div>
						</div>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("examDate", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Exam Time<span style="color: red">*</span></th>
					<td>
						<%
							LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();
							map1.put("08:00 AM to 11:00 AM", "08:00 AM to 11:00 AM");
							map1.put("12:00 PM to 03:00 PM", "12:00 PM to 03:00 PM");
							map1.put("04:00 PM to 07:00 PM", "04:00 PM to 07:00 PM");

							String htmlList1 = HTMLUtility.getList("examTime", bean.getExamTime(), map1);
						%> <%=htmlList1%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("examTime", request)%></font></td>
				</tr>
				<tr>
					<th>Course:</th>
					<td><%=HTMLUtility.getList("courseId", DataUtility.getStringData(bean.getCourseId()), courseList)%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("courseId", request)%></font></td>
				</tr>
				<tr>
					<th>Subject:</th>
					<td><%=HTMLUtility.getList("subjectId", DataUtility.getStringData(bean.getSubjectId()), subjectList)%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("subjectId", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=TimetableCtl.OP_SAVE%>">&nbsp; <input
						type="submit" name="operation" value="<%=TimetableCtl.OP_RESET%>"></td>
				</tr>
			</table>
		</div>
	</form>

</body>
</html>