package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.PositionBean;
import in.co.rays.model.CollegeModel;
import in.co.rays.model.PositionModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "CollegeCtl", urlPatterns = {"/CollegeCtl"})
public class CollegeCtl extends BaseCtl {
	
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
	     CollegeBean bean =  new CollegeBean();
	     bean.setId(DataUtility.getLong(request.getParameter("id")));
	     bean.setName(DataUtility.getString(request.getParameter("name")));
	     bean.setAddress(DataUtility.getString(request.getParameter("address")));
	     bean.setState(DataUtility.getString(request.getParameter("state")));
	     bean.setCity(DataUtility.getString(request.getParameter("city")));
	     bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));
	     populateDTO(bean, request);
		 return bean;
	}
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Invalid Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue("error.require", "State"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", PropertyReader.getValue("error.require", "Phone No"));
			pass = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", "Phone No must have 10 digits");
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", "Invalid Phone No");
			pass = false;
		}
		return pass;
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			CollegeModel model = new CollegeModel();

			try {
				CollegeBean bean = model.findByPk(id);
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

		CollegeModel model = new CollegeModel();

		CollegeBean bean = (CollegeBean) populateBean(request);

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
			ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
			return;
		}	
	}

	@Override
	protected String getView() {
		return ORSView.COLLEGE_VIEW;
	}

}
