package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "MarksheetCtl", urlPatterns = {"/MarksheetCtl"})
public class MarksheetCtl extends BaseCtl {
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		MarksheetBean bean = new MarksheetBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNO")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));
		populateDTO(bean, request);
		return bean;
	}
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNO"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "rollNo"));
			pass = false;
		} else if (!DataValidator.isRollNo(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", "Invalid RollNo");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("Name"))) {
			request.setAttribute("name", "Invalid Student Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("physics"))) {
			request.setAttribute("physics", PropertyReader.getValue("error.require", "Physics Marks"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.require", "chemistry Marks"));
			pass = false;
		
		}
		if (DataValidator.isNull(request.getParameter("maths"))) {
			request.setAttribute("maths", PropertyReader.getValue("error.require", "Maths Marks"));
			pass = false;
		}	
		return pass;
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ServletUtility.forward(getView(), request, response);	
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ServletUtility.forward(getView(), request, response);		
	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_VIEW;
	}

}
