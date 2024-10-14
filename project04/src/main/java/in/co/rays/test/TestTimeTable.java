package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.TimeTableBean;
import in.co.rays.model.TimeTableModel;

public class TestTimeTable {
	
	   public static void main(String[] args) throws Exception {
			
			testAdd();
			//testUpdate();
			//testDelete();
			//testFindByPk();
			//testSearch();
			
		}
		public static void testAdd() throws Exception {

			TimeTableBean bean = new TimeTableBean();

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

			TimeTableModel model = new TimeTableModel();

			model.add(bean);
		}
		
		public static void testUpdate() throws Exception {

			TimeTableModel model = new TimeTableModel();
			TimeTableBean bean = model.findByPk(2);

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

			TimeTableBean bean = new TimeTableBean();

			bean.setId(1);

			TimeTableModel model = new TimeTableModel();

			model.delete(bean);
		}

		private static void testFindByPk() throws Exception {

			TimeTableBean bean = new TimeTableBean();

			TimeTableModel model = new TimeTableModel();

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
			
	         TimeTableBean bean = new TimeTableBean();
			
			TimeTableModel model = new TimeTableModel();
			
			bean.setSemester("4");
			
			List list = model.search(bean, 1, 5);
			
			Iterator it = list.iterator();
			
			while(it.hasNext()) {
				
				bean = (TimeTableBean) it.next();
				
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
