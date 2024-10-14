package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.model.MarksheetModel;


public class TestMarksheet {
	
public static void main(String[] args) throws Exception {
		
		testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPk();
		//testFindByRollNo();
		//testSearch();
		
	}
	public static void testAdd() throws Exception {

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo("101");
		bean.setStudentId(1);
		bean.setPhysics(25);
		bean.setChemistry(56);
		bean.setMaths(36);
		bean.setCreatedBy("prince@gmail.com");
		bean.setModifiedBy("prince@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		MarksheetModel model = new MarksheetModel();

		model.add(bean);
	}
	
	public static void testUpdate() throws Exception {

		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = model.findByPk(2);

		bean.setRollNo("101");
		bean.setStudentId(1);
		bean.setName("prince");
		bean.setPhysics(25);
		bean.setChemistry(56);
		bean.setMaths(36);
		bean.setCreatedBy("prince@gmail.com");
		bean.setModifiedBy("prince");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		model.update(bean);
	}
	private static void testDelete() throws Exception {

		MarksheetBean bean = new MarksheetBean();

		bean.setId(1);

		MarksheetModel model = new MarksheetModel();

		model.delete(bean);
	}

	private static void testFindByPk() throws Exception {

		MarksheetBean bean = new MarksheetBean();

		MarksheetModel model = new MarksheetModel();

		bean = model.findByPk(1);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getStudentId());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		}
	}

	private static void testFindByRollNo() throws Exception {

		MarksheetBean bean = new MarksheetBean();

		MarksheetModel model = new MarksheetModel();

		bean = model.findByRollNo("aunrag@gmail.com");

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getStudentId());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		}
	}
	private static void testSearch()throws Exception{
		
         MarksheetBean bean = new MarksheetBean();
		
		MarksheetModel model = new MarksheetModel();
		
		bean.setName("anurag");
		
		List list = model.search(bean, 1, 5);
		
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			
			bean = (MarksheetBean) it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getStudentId());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
		}
	}

}
