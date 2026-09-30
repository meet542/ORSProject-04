package in.co.rays.proj4.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;

public class TestUserModel {

	private static UserModel model = new UserModel();
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	public static void main(String[] args) {

		try {
//			testAdd();
//			testUpdate();
//			testDelete();
			testSearch();
//			testFindByPk();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void testAdd() throws Exception {

		UserBean bean = new UserBean();

		bean.setFirstName("Rahul");
		bean.setLastName("Sharma");
		bean.setLogin("rahul123");
		bean.setPassword("rahul@123");
		bean.setDob(sdf.parse("2016/11/04"));
		bean.setMobileNo("9123456780");
		bean.setRoleId(1);
		bean.setUnsuccessfulLogin(0);
		bean.setGender("Male");
		bean.setLastLogin(sdf.parse("2024/02/13"));
		bean.setUserLock("N");
		bean.setRegisteredIp("192.168.1.10");
		bean.setLastLoginIp("192.168.1.10");

		long i = model.add(bean);
		System.out.println("record inserted at: " + i);

	}

	private static void testUpdate() throws Exception {

		UserBean bean = new UserBean();

		bean.setFirstName("Rahul");
		bean.setLastName("Verma");
		bean.setLogin("rahul123");
		bean.setPassword("rahul@123");
		bean.setDob(sdf.parse("2016/11/04"));
		bean.setMobileNo("9121156780");
		bean.setRoleId(1);
		bean.setUnsuccessfulLogin(0);
		bean.setGender("Male");
		bean.setLastLogin(sdf.parse("2024/02/13"));
		bean.setUserLock("N");
		bean.setRegisteredIp("192.168.1.10");
		bean.setLastLoginIp("192.168.1.10");

		model.update(bean);

	}

	private static void testDelete() throws Exception {

		model.delete(0);

	}

	private static void testSearch() throws Exception {

		UserBean bean = new UserBean();

		List list = new ArrayList();

		bean.setLastName("a");

		list = model.search(bean, 1, 5);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (UserBean) it.next();
			System.out.println("First Name : " + bean.getFirstName());
			System.out.println("Last Name : " + bean.getLastName());
			System.out.println("Login : " + bean.getLogin());
			System.out.println("Password : " + bean.getPassword());
			System.out.println("Dob : " + bean.getDob());
			System.out.println("Mobile number : " + bean.getMobileNo());
			System.out.println("Role id : " + bean.getRoleId());
			System.out.println("unsuccessful login : " + bean.getUnsuccessfulLogin());
			System.out.println("Gender : " + bean.getGender());
			System.out.println("Last Login : " + bean.getLastLogin());
			System.out.println("User Lock : " + bean.getUserLock());
			System.out.println("Registered ip : " + bean.getRegisteredIp());
			System.out.println("Last login ip : " + bean.getLastLoginIp());
			System.out.println("--------------------------");
		}

	}

	private static void testFindByPk() throws Exception {

		UserBean bean = new UserBean();

		bean = model.findByPk(5);

		System.out.println("First Name : " + bean.getFirstName());
		System.out.println("Last Name : " + bean.getLastName());
		System.out.println("Login : " + bean.getLogin());
		System.out.println("Password : " + bean.getPassword());
		System.out.println("Dob : " + bean.getDob());
		System.out.println("Mobile number : " + bean.getMobileNo());
		System.out.println("Role id : " + bean.getRoleId());
		System.out.println("unsuccessful login : " + bean.getUnsuccessfulLogin());
		System.out.println("Gender : " + bean.getGender());
		System.out.println("Last Login : " + bean.getLastLogin());
		System.out.println("User Lock : " + bean.getUserLock());
		System.out.println("Registered ip : " + bean.getRegisteredIp());
		System.out.println("Last login ip : " + bean.getLastLoginIp());
	}

}
