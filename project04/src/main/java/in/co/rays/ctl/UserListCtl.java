package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.model.RoleModel;

@WebServlet(name = "UserListCtl", urlPatterns = {"/UserListCtl"})
public class UserListCtl extends BaseCtl {
	
	@Override
	protected void preload(HttpServletRequest request) {
		RoleModel roleModel = new RoleModel();
		
		try {
			List roleList = roleModel.list();
			request.setAttribute("roleList", roleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();
		return bean;
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected String getView() {
		return ORSView.USER_LIST_CTL ;
	}

}
