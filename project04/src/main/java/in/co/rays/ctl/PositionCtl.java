package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.PositionBean;
import in.co.rays.model.PositionModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet (name = "PositionCtl", urlPatterns = {"/PositionCtl"})
public class PositionCtl extends BaseCtl {
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("designation"))) {
			request.setAttribute("designation", PropertyReader.getValue("error.require", "Designation"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("openingDate"))) {
			request.setAttribute("openingDate", PropertyReader.getValue("error.require", "Opening Date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("requiredExperience"))) {
			request.setAttribute("requiredExperience", PropertyReader.getValue("error.require", "Required Experience"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("condition"))) {
			request.setAttribute("condition", PropertyReader.getValue("error.require", "condition"));
			pass = false;
		}
		
		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		PositionBean bean = new PositionBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setDesignation(DataUtility.getString(request.getParameter("designation")));
		bean.setOpeningDate(DataUtility.getDate(request.getParameter("openingDate")));
		bean.setReqiredExperience(DataUtility.getString(request.getParameter("requiredExperience")));
		bean.setCondition(DataUtility.getString(request.getParameter("condition")));
		populateDTO(bean, request);
		return bean;
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));
		
		if (id > 0) {
			
			PositionModel model = new PositionModel();
			
			try {
				PositionBean bean = model.findByPk(id);
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
		
		PositionModel model = new PositionModel();
		
		PositionBean bean = (PositionBean) populateBean(request);
		
		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				try {
					model.add(bean);
				} catch (Exception e) {
					ServletUtility.setSuccessMessage("Data Added Successfully..!!", request);
					ServletUtility.forward(getView(), request, response);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Data already exist", request);
					ServletUtility.forward(getView(), request, response);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.POSITION_CTL, request, response);
			return;
		}

	}

	@Override
	protected String getView() {
		return ORSView.POSITION_VIEW;
	}

}
