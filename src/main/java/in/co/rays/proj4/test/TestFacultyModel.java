package in.co.rays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;

public class TestFacultyModel {

	private static FacultyModel model = new FacultyModel();

	public static void main(String[] args) {
		try {
			testAdd();
//			testUpdate();
//			testDelete();
//			testSearch();
//			testFindByPk();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testAdd() throws Exception {
		FacultyBean bean = new FacultyBean();

		bean.setCollegeId(1);
		bean.setCollegeName("SGSITS");
		bean.setFirstName("Rahul");
		bean.setLastName("Sharma");
		bean.setEmail("rahul.sharma@gmail.com");
		bean.setMobileNo("9123456780");
		bean.setAddress("Indore");
		bean.setGender("Male");
		bean.setDob(new java.sql.Date(new java.text.SimpleDateFormat("yyyy/MM/dd").parse("1995/05/15").getTime()));

		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setModifiedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		long i = model.add(bean);
		System.out.println("record inserted at: " + i);
	}

	private static void testUpdate() throws Exception {
		FacultyBean bean = new FacultyBean();

		bean.setId(1);
		bean.setCollegeId(1);
		bean.setCollegeName("SGSITS");
		bean.setFirstName("Rahul");
		bean.setLastName("Verma");
		bean.setEmail("rahul.verma@gmail.com");
		bean.setMobileNo("9121156780");
		bean.setAddress("Indore");
		bean.setGender("Male");
		bean.setDob(new java.sql.Date(new java.text.SimpleDateFormat("yyyy/MM/dd").parse("1995/05/15").getTime()));

		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setModifiedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		model.update(bean);
	}

	private static void testDelete() throws Exception {
		model.delete(1);
	}

	private static void testSearch() throws Exception {
		FacultyBean bean = new FacultyBean();
		List list = new ArrayList();

		bean.setLastName("a");

		list = model.search(bean, 1, 5);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (FacultyBean) it.next();

			System.out.println("College Id : " + bean.getCollegeId());
			System.out.println("College Name : " + bean.getCollegeName());
			System.out.println("First Name : " + bean.getFirstName());
			System.out.println("Last Name : " + bean.getLastName());
			System.out.println("Email : " + bean.getEmail());
			System.out.println("Mobile number : " + bean.getMobileNo());
			System.out.println("Address : " + bean.getAddress());
			System.out.println("Gender : " + bean.getGender());
			System.out.println("DOB : " + bean.getDob());
			System.out.println("Created By : " + bean.getCreatedBy());
			System.out.println("Modified By : " + bean.getModifiedBy());
			System.out.println("Created Datetime : " + bean.getCreatedDatetime());
			System.out.println("Modified Datetime : " + bean.getModifiedDatetime());
			System.out.println("--------------------------");
		}
	}

	private static void testFindByPk() throws Exception {
		FacultyBean bean = new FacultyBean();

		bean = model.findByPk(1);

		System.out.println("College Id : " + bean.getCollegeId());
		System.out.println("College Name : " + bean.getCollegeName());
		System.out.println("First Name : " + bean.getFirstName());
		System.out.println("Last Name : " + bean.getLastName());
		System.out.println("Email : " + bean.getEmail());
		System.out.println("Mobile number : " + bean.getMobileNo());
		System.out.println("Address : " + bean.getAddress());
		System.out.println("Gender : " + bean.getGender());
		System.out.println("DOB : " + bean.getDob());
		System.out.println("Created By : " + bean.getCreatedBy());
		System.out.println("Modified By : " + bean.getModifiedBy());
		System.out.println("Created Datetime : " + bean.getCreatedDatetime());
		System.out.println("Modified Datetime : " + bean.getModifiedDatetime());
	}

}
