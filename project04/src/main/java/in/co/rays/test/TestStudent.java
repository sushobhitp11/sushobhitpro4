package in.co.rays.test;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.bean.StudentBean;
import in.co.rays.model.StudentModel;



public class TestStudent {
	
	public static void main(String[] args) throws Exception {
		
		//testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPk();
		testFindByEmail();
		//testSearch();
		
	}
	public static void testAdd() throws Exception {

		StudentBean bean = new StudentBean();

		bean.setFirstName("Avnish");
		bean.setLastName("Upadhyay");
		bean.setDob(new Date());
		bean.setGender("male");
		bean.setMobileNo("7648880017");
		bean.setEmail("avnish@gmail.com");
		bean.setCollegeId(1);
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		StudentModel model = new StudentModel();

		model.add(bean);
	}
	
	public static void testUpdate() throws Exception {

		StudentModel model = new StudentModel();
		StudentBean bean = model.findByPk(2);

		bean.setFirstName("shivam");
		bean.setLastName("tripathi");
		bean.setDob(new Date(2012-05-11));
		bean.setGender("male");
		bean.setMobileNo("9898989898");
		bean.setEmail("shivam@gmail.com");
		bean.setCollegeId(1);
		bean.setCreatedBy("shivam@gmail.com");
		bean.setModifiedBy("shivam");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		model.update(bean);
	}
	private static void testDelete() throws Exception {

		StudentBean bean = new StudentBean();

		bean.setId(1);

		StudentModel model = new StudentModel();

		//model.delete(bean);
	}

	private static void testFindByPk() throws Exception {

		StudentBean bean = new StudentBean();

		StudentModel model = new StudentModel();

		bean = model.findByPk(1);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getGender());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		}
	}

	private static void testFindByEmail() throws Exception {

		StudentBean bean = new StudentBean();

		StudentModel model = new StudentModel();

		bean = model.findByEmail("avnish@gmail.com");

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getGender());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		}
	}
	private static void testSearch()throws Exception{
		
         StudentBean bean = new StudentBean();
		
		StudentModel model = new StudentModel();
		
		bean.setFirstName("anurag");
		
		List list = model.search(bean, 1, 5);
		
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			
			bean = (StudentBean) it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getGender());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
		}
	}
}
