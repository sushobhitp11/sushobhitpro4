package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CustomerBean;
import in.co.rays.model.CustomerModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet (name = "CustomerCtl" , urlPatterns = {"/CustomerCtl"})
public class CustomerCtl extends BaseCtl{

	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("clientName"))) {
			request.setAttribute("clientName", PropertyReader.getValue("error.require", "Client Name"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("clientName"))) {
			request.setAttribute("clientName", "Invalid Client Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("location"))) {
			request.setAttribute("location", PropertyReader.getValue("error.require", "Location"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("contactNumber"))) {
			request.setAttribute("contactNumber", PropertyReader.getValue("error.require", "Contact Number"));
			pass = false;
		}else if (!DataValidator.isPhoneLength(request.getParameter("contactNumber"))) {
			request.setAttribute("contactNumber", "Contact Number No. must have 10 digits");
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("contactNumber"))) {
			request.setAttribute("contactNumber", "Invalid Contact Number");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("importance"))) {
			request.setAttribute("importance", PropertyReader.getValue("error.require", "Importance"));
			pass = false;
		}
		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		CustomerBean bean = new CustomerBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setClientName(DataUtility.getString(request.getParameter("clientName")));
		System.out.println("date => " + request.getParameter("clientName"));
		bean.setLocation(DataUtility.getString(request.getParameter("location")));
		bean.setContactNumber(DataUtility.getString(request.getParameter("contactNumber")));
		bean.setImportance(DataUtility.getString(request.getParameter("importance")));
		populateDTO(bean, request);
		return bean;

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			CustomerModel model = new CustomerModel();

			try {
				CustomerBean bean = model.findByPk(id);
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

		CustomerModel model = new CustomerModel();

		CustomerBean bean = (CustomerBean) populateBean(request);

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
			ServletUtility.redirect(ORSView.CUSTOMER_CTL, request, response);
			return;
		}
	}
	@Override
	protected String getView() {
		return ORSView.CUSTOMER_VIEW;
	}

}
