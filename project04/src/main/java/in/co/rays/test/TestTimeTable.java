package in.co.rays.test;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.bean.TimetableBean;
import in.co.rays.model.TimetableModel;

public class TesttimeTable {
	
	   public static void main(String[] args) throws Exception {
			
			testAdd();
			//testUpdate();
			//testDelete();
			//testFindByPk();
			//testSearch();
			
		}
		public static void testAdd() throws Exception {

			TimetableBean bean = new TimetableBean();

			bean.setSemester("4");
			bean.setDescription("Description");
			bean.setExamDate(new Date(2024-11-25));
			bean.setExamTime("1pm to 4pm");
			bean.setCourseId(1L);
			bean.setSubjectId(1L);
			bean.setCreatedBy("abc@gmail.com");
			bean.setModifiedBy("abc");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			TimetableModel model = new TimetableModel();

			model.add(bean);
		}
		
		public static void testUpdate() throws Exception {

			TimetableModel model = new TimetableModel();
			TimetableBean bean = model.findByPk(2);

			bean.setSemester("4");
			bean.setDescription("Description");
			bean.setExamDate(new Date(2024-11-25));
			bean.setExamTime("1pm to 4pm");
			bean.setCourseId(1L);
			bean.setSubjectId(1L);
			bean.setCreatedBy("abc@gmail.com");
			bean.setModifiedBy("abc");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			bean.setId(1);

			model.update(bean);
		}
		private static void testDelete() throws Exception {

			TimetableBean bean = new TimetableBean();

			bean.setId(1);

			TimetableModel model = new TimetableModel();

			model.delete(bean);
		}

		private static void testFindByPk() throws Exception {

			TimetableBean bean = new TimetableBean();

			TimetableModel model = new TimetableModel();

			bean = model.findByPk(1);

			if (bean != null) {

				System.out.println(bean.getId());
				System.out.println(bean.getSemester());
				System.out.println(bean.getDescription());
				System.out.println(bean.getExamDate());
				System.out.println(bean.getExamTime());
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getExamTime());
				System.out.println(bean.getExamTime());
				System.out.println(bean.getSubjectId());
				System.out.println(bean.getSubjectName());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());

			}
		}
		private static void testSearch()throws Exception{
			
			TimetableBean bean = new TimetableBean();
			
	         TimetableModel model = new TimetableModel();
			
			bean.setSemester("4");
			
			List list = model.search(bean, 1, 5);
			
			Iterator it = list.iterator();
			
			while(it.hasNext()) {
				
				bean = (TimetableBean) it.next();
				
				System.out.println(bean.getId());
				System.out.println(bean.getSemester());
				System.out.println(bean.getDescription());
				System.out.println(bean.getExamDate());
				System.out.println(bean.getExamTime());
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getExamTime());
				System.out.println(bean.getExamTime());
				System.out.println(bean.getSubjectId());
				System.out.println(bean.getSubjectName());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());
				
			}
		}

}
