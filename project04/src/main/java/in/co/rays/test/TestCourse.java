package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.model.CourseModel;

public class TestCourse {
	
public static void main(String[] args) throws Exception {
		
		testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPk();
		//testSearch();
		
	}
	public static void testAdd() throws Exception {

		CourseBean bean = new CourseBean();

		bean.setName("MCA");
		bean.setDuration("3years");
		bean.setDescription("Master's in Computer Application");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		CourseModel model = new CourseModel();

		model.add(bean);
	}
	
	public static void testUpdate() throws Exception {

		CourseModel model = new CourseModel();
		CourseBean bean = model.findByPk(2);

		bean.setName("MCA");
		bean.setDuration("3years");
		bean.setDescription("Master's in Computer Application");
		bean.setCreatedBy("shivam@gmail.com");
		bean.setModifiedBy("shivam");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		model.update(bean);
	}
	private static void testDelete() throws Exception {

		CourseBean bean = new CourseBean();

		bean.setId(1);

		CourseModel model = new CourseModel();

		model.delete(bean);
	}

	private static void testFindByPk() throws Exception {

		CourseBean bean = new CourseBean();

		CourseModel model = new CourseModel();

		bean = model.findByPk(1);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDuration());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		}
	}
	private static void testSearch()throws Exception{
		
         CourseBean bean = new CourseBean();
		
		CourseModel model = new CourseModel();
		
		bean.setName("MCA");
		
		List list = model.search(bean, 1, 5);
		
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			
			bean = (CourseBean) it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDuration());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
		}
	}

}
