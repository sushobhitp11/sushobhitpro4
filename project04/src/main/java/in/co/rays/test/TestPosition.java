package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.PositionBean;
import in.co.rays.model.PositionModel;

public class TestPosition {

	public static void main(String[] args) throws Exception {

		// testAdd();
		//testUpdate();
		//testDelete();
		//testSearch();
		//testFindByPk();
		//testFindByDesignation();

	}

	private static void testAdd() throws Exception {

		PositionBean bean = new PositionBean();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setDesignation("Sequirty");
		bean.setOpeningDate(sdf.parse("2024-10-12"));
		bean.setReqiredExperience("0years");
		bean.setCondition("Open");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		PositionModel model = new PositionModel();

		model.add(bean);

	}

	private static void testUpdate() throws Exception {

		PositionBean bean = new PositionBean();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setDesignation("Intern");
		bean.setOpeningDate(sdf.parse("2024-10-12"));
		bean.setReqiredExperience("0years");
		bean.setCondition("open");
		bean.setCreatedBy("User");
		bean.setModifiedBy("User");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(4);

		PositionModel model = new PositionModel();

		model.update(bean);

	}
	private static void testDelete() throws Exception {
		
		PositionBean bean = new PositionBean();
		
		PositionModel model = new PositionModel();
		
		bean.setId(7);
		
	     //model.delete(bean);
		
	}
	private static void testSearch() throws Exception{
		
		 PositionBean bean = new PositionBean();
		 
		 PositionModel model = new PositionModel();
		 
		 bean.setDesignation("manager");
		 
		 List list = model.search(bean, 1, 5);
		 
		 Iterator it  = list.iterator();
		 
		 while (it.hasNext()) {
			  bean = (PositionBean) it.next();
			  
			  System.out.println(bean.getId());
			  System.out.println(bean.getDesignation());
			  System.out.println(bean.getOpeningDate());
			  System.out.println(bean.getReqiredExperience());
			  System.out.println(bean.getCondition());
			  System.out.println(bean.getCreatedBy());
			  System.out.println(bean.getModifiedBy());
			  System.out.println(bean.getCreatedDatetime());
			  System.out.println(bean.getModifiedDatetime());
		 }
		 
	}
	private static void testFindByPk() throws Exception{
		
		PositionBean bean = new PositionBean();
		
		PositionModel model =  new PositionModel();
		
		bean =  model.findByPk(1);
		
		if(bean !=  null ) {
			
			  System.out.println(bean.getId());
			  System.out.println(bean.getDesignation());
			  System.out.println(bean.getOpeningDate());
			  System.out.println(bean.getReqiredExperience());
			  System.out.println(bean.getCondition());
			  System.out.println(bean.getCreatedBy());
			  System.out.println(bean.getModifiedBy());
			  System.out.println(bean.getCreatedDatetime());
			  System.out.println(bean.getModifiedDatetime());
		}
	}
	private static void testFindByDesignation() throws Exception{
		
		PositionBean bean = new PositionBean();
		
		PositionModel model = new PositionModel();
		
		bean = model.findBydesignation("services");
		
		if (bean !=  null) {
			
			  System.out.println(bean.getId());
			  System.out.println(bean.getDesignation());
			  System.out.println(bean.getOpeningDate());
			  System.out.println(bean.getReqiredExperience());
			  System.out.println(bean.getCondition());
			  System.out.println(bean.getCreatedBy());
			  System.out.println(bean.getModifiedBy());
			  System.out.println(bean.getCreatedDatetime());
			  System.out.println(bean.getModifiedDatetime());
			
			
		}
	}

}
