package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.StaffMemberBean;
import in.co.rays.model.StaffMemberModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "StaffMemberCtl", urlPatterns = {"/StaffMemberCtl"})
public class StaffMemberCtl extends BaseCtl {

	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("fullName"))) {
			request.setAttribute("fullName", PropertyReader.getValue("error.require", "Full Name"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("fullName"))) {
			request.setAttribute("fullName", "Invalid Full Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("joiningDate"))) {
			request.setAttribute("joiningDate", PropertyReader.getValue("error.require", "Joining Date"));
			pass = false;
		}else if (!DataValidator.isDate(request.getParameter("joiningDate"))) {
			request.setAttribute("joiningDate", "Invalid Joining Date");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("division"))) {
			request.setAttribute("division", PropertyReader.getValue("error.require", "Division"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("previousEmployer"))) {
			request.setAttribute("previousEmployer", PropertyReader.getValue("error.require", "Previous Employer"));
			pass = false;
		}
		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		StaffMemberBean bean = new StaffMemberBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFullName(DataUtility.getString(request.getParameter("fullName")));
		System.out.println("date => " + request.getParameter("fullName"));
		bean.setJoiningDate(DataUtility.getDate(request.getParameter("joiningDate")));
		bean.setDivision(DataUtility.getString(request.getParameter("division")));
		bean.setPreviousEmployer(DataUtility.getString(request.getParameter("previousEmployer")));
		populateDTO(bean, request);
		return bean;

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			StaffMemberModel model = new StaffMemberModel();

			try {
				StaffMemberBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String op = DataUtility.getString(request.getParameter("operation"));

		StaffMemberModel model = new StaffMemberModel();

		StaffMemberBean bean = (StaffMemberBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {

				model.add(bean);
				ServletUtility.setSuccessMessage("Data Added Successfully..!!", request);
				ServletUtility.forward(getView(), request, response);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Data already exist", request);
				ServletUtility.forward(getView(), request, response);
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.STAFFMEMBER_CTL, request, response);
			return;
		}
	}
	@Override
	protected String getView() {
		return ORSView.STAFFMEMBER_VIEW;
	}

}
