package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.FacultyBean;
import in.co.rays.model.FacultyModel;

public class TestFaculty {
	
	public static void main(String[] args) throws Exception {
		
		testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPk();
		//testSearch();
		
	}
	public static void testAdd() throws Exception {

		FacultyBean bean = new FacultyBean();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("anshul sir");
		bean.setLastName("prajapati");
		bean.setDob(new Date(1999-03-11));
		bean.setGender("male");
		bean.setEmailId("anshul@gmail.com");
		bean.setMobileNo("8987554411");
		bean.setCollegeId(1);
		bean.setCourseId(1);
		bean.setSubjectId(1);
		bean.setCreatedBy("abc@gmail.com");
		bean.setModifiedBy("abc");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		FacultyModel model = new FacultyModel();

		model.add(bean);
	}
	
	public static void testUpdate() throws Exception {

		FacultyModel model = new FacultyModel();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		FacultyBean bean = model.findByPk(2);

		bean.setFirstName("Sawan sir");
		bean.setLastName("panwar");
		bean.setDob(new Date(1994-03-11));
		bean.setGender("male");
		bean.setEmailId("sawan@gmail.com");
		bean.setMobileNo("1548796521");
		bean.setCollegeId(1);
		bean.setCourseId(1);
		bean.setSubjectId(1);
		bean.setCreatedBy("abc@gmail.com");
		bean.setModifiedBy("abc");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);
	}
	private static void testDelete() throws Exception {

		FacultyBean bean = new FacultyBean();

		bean.setId(1);

		FacultyModel model = new FacultyModel();

		model.delete(bean);
	}

	private static void testFindByPk() throws Exception {

		FacultyBean bean = new FacultyBean();

		FacultyModel model = new FacultyModel();

		bean = model.findByPk(1);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getGender());
			System.out.println(bean.getEmailId());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		}
	}
	private static void testSearch()throws Exception{
		
         FacultyBean bean = new FacultyBean();
		
		FacultyModel model = new FacultyModel();
		
		bean.setId(1);
		
		List list = model.search(bean, 1, 5);
		
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			
			bean = (FacultyBean) it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getGender());
			System.out.println(bean.getEmailId());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
		}
	}


}
