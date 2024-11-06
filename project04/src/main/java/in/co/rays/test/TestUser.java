package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.bean.UserBean;
import in.co.rays.model.RoleModel;
import in.co.rays.model.UserModel;

public class TestUser {

	public static void main(String[] args) throws Exception {

		testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByLogin();
		// testSearch();
	}

	public static void testAdd() throws Exception {

		UserBean bean = new UserBean();

		SimpleDateFormat sdf = new SimpleDateFormat();

		bean.setFirstName("anurag");
		bean.setLastName("tripathi");
		bean.setLogin_id("anurag@gmail.com");
		bean.setPassword("12345");
		bean.setDob(new Date(2012 - 05 - 11));
		bean.setMobileNo("4545454");
		bean.setRoleId(1);
		bean.setGender("male");
		bean.setCreatedBy("anurag@gmail.com");
		bean.setModifiedBy("anurag");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		UserModel model = new UserModel();

		model.add(bean);
	}

	public static void testUpdate() throws Exception {

		UserModel model = new UserModel();
		UserBean bean = model.findByPK(2);

		bean.setFirstName("anurag");
		bean.setLastName("tripathi");
		bean.setLogin_id("anurag@gmail.com");
		bean.setPassword("12345");
		bean.setDob(new Date(2012 - 05 - 11));
		bean.setMobileNo("4545454");
		bean.setRoleId(1);
		bean.setGender("male");
		bean.setCreatedBy("anurag@gmail.com");
		bean.setModifiedBy("anurag");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		model.update(bean);
	}

	public static void testDelete() throws Exception {
		UserModel model = new UserModel();
		model.delete(1);
	}


	public static void testFindByPk() throws Exception {

		UserBean bean = new UserBean();

		UserModel model = new UserModel();

		bean = model.findByPK(1);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin_id());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		}
	}

	public static void testFindByLogin() throws Exception {

		UserBean bean = new UserBean();

		UserModel model = new UserModel();

		bean = model.findBylogin("aunrag@gmail.com");

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin_id());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		}
	}
	public static void testSearch()throws Exception{
		
         UserBean bean = new UserBean();
		
		UserModel model = new UserModel();
		
		bean.setFirstName("anurag");
		
		List list = model.search(bean, 0, 5);
		
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			
			bean = (UserBean) it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin_id());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
		}
		
	}
}
