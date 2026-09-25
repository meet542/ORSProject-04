package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;

//1 admin
//2 student
//3 college
//4 faculty
//5 KIOSK
public class TestRoleModel {

	private static RoleModel model = new RoleModel();

	public static void main(String[] args) {

//		testAdd();
//		testUpdate();
		testDelete();

	}

	private static void testAdd() {

		RoleBean bean = new RoleBean();

		bean.setName("dummy");
		bean.setDescription("dummy role");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		long pk = model.add(bean);
		System.out.println("record inserted at: " + pk);
	}
	
	private static void testUpdate() {
		
		RoleBean bean = new RoleBean();
		
		bean.setId(1);
		bean.setName("admin");
		bean.setDescription("updated role");
		bean.setModifiedBy("admin");
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		model.update(bean);
	}
	
	private static void testDelete() {
		
		model.delete(6);
		
	}

}
