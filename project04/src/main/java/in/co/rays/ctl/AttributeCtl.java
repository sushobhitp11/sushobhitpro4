package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.rays.bean.AttributeBean;
import in.co.rays.bean.BaseBean;
import in.co.rays.model.AttributeModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "AttributeCtl", urlPatterns = { "/AttributeCtl" })
public class AttributeCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("displayName"))) {
			request.setAttribute("displayName", PropertyReader.getValue("error.require", "Display Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dataType"))) {
			request.setAttribute("dataType", PropertyReader.getValue("error.require", "Data Type"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("isActive"))) {
			request.setAttribute("isActive", PropertyReader.getValue("error.require", "Select Option"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Short Description"));
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		AttributeBean bean = new AttributeBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setDisplayName(DataUtility.getString(request.getParameter("displayName")));
		bean.setDataType(DataUtility.getString(request.getParameter("dataType")));
		bean.setIsActive(DataUtility.getString(request.getParameter("isActive")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			AttributeModel model = new AttributeModel();

			try {
				AttributeBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		AttributeModel model = new AttributeModel();

		AttributeBean bean = (AttributeBean) populateBean(request);

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
			ServletUtility.redirect(ORSView.ATTRIBUTE_CTL, request, response);
			return;
		}

	}

	@Override
	protected String getView() {
		return ORSView.ATTRIBUTE_VIEW;
	}

}
