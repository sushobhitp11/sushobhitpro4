package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.model.CollegeModel;
import in.co.rays.model.StudentModel;

public class TestCollege {
	
public static void main(String[] args) throws Exception {
		
		testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPk();
		//testFindByEmail();
		//testSearch();
		
	}
	private static void testAdd() throws Exception {

		CollegeBean bean = new CollegeBean();

		bean.setName("Medicaps");
		bean.setAddress("indore");
		bean.setState("MP");
		bean.setCity("indore");
		bean.setPhoneNo("9898900098");
		bean.setCreatedBy("medicap@gmail.com");
		bean.setModifiedBy("medicap");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		CollegeModel model = new CollegeModel();

		model.add(bean);
	}
	public static void testUpdate() throws Exception {

		CollegeModel model = new CollegeModel();
		CollegeBean bean = model.findByPk(1);

		bean.setName("Medicaps");
		bean.setAddress("indore");
		bean.setState("MP");
		bean.setCity("indore");
		bean.setPhoneNo("9898900098");
		bean.setCreatedBy("medicap@gmail.com");
		bean.setModifiedBy("medicap");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		model.update(bean);
	}
	private static void testDelete() throws Exception {

		CollegeBean bean = new CollegeBean();

		bean.setId(1);

		CollegeModel model = new CollegeModel();

		//model.delete(bean);
	}

	private static void testFindByPk() throws Exception {

		CollegeBean bean = new CollegeBean();

		CollegeModel model = new CollegeModel();

		bean = model.findByPk(1);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		}
	}

	private static void testFindByEmail() throws Exception {

		CollegeBean bean = new CollegeBean();

		CollegeModel model = new CollegeModel();

		bean = model.findByName("aunrag@gmail.com");

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		}
	}
	private static void testSearch()throws Exception{
		
         CollegeBean bean = new CollegeBean();
		
         CollegeModel model = new CollegeModel();
		
		bean.setName("Medicaps");
		
		List list = model.search(bean, 1, 5);
		
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			
			bean = (CollegeBean) it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
		}
	}

}
