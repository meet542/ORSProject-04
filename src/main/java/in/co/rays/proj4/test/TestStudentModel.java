package in.co.rays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;

public class TestStudentModel {
	private static StudentModel model = new StudentModel();

	public static void main(String[] args) {
		try {
			testAdd();
			testUpdate();
			testDelete();
			testSearch();
			testFindByPk();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testAdd() throws Exception {
		StudentBean bean = new StudentBean();

		bean.setCollegeId(1);
		bean.setCollegeName("SGSITS");
		bean.setFirstName("Rahul");
		bean.setLastName("Sharma");
		bean.setDob(new java.sql.Date(new java.text.SimpleDateFormat("yyyy/MM/dd").parse("2005/11/04").getTime()));
		bean.setMobileNo("9123456780");
		bean.setEmail("rahul.sharma@gmail.com");

		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setModifiedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		long i = model.add(bean);
		System.out.println("record inserted at: " + i);
	}

	private static void testUpdate() throws Exception {
		StudentBean bean = new StudentBean();

		bean.setId(1);
		bean.setCollegeId(1);
		bean.setCollegeName("SGSITS");
		bean.setFirstName("Rahul");
		bean.setLastName("Verma");
		bean.setDob(new java.sql.Date(new java.text.SimpleDateFormat("yyyy/MM/dd").parse("2005/11/04").getTime()));
		bean.setMobileNo("9121156780");
		bean.setEmail("rahul.verma@gmail.com");

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
		StudentBean bean = new StudentBean();
		List list = new ArrayList();

		bean.setLastName("a");

		list = model.search(bean, 1, 5);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (StudentBean) it.next();

			System.out.println("College Id : " + bean.getCollegeId());
			System.out.println("College Name : " + bean.getCollegeName());
			System.out.println("First Name : " + bean.getFirstName());
			System.out.println("Last Name : " + bean.getLastName());
			System.out.println("DOB : " + bean.getDob());
			System.out.println("Mobile number : " + bean.getMobileNo());
			System.out.println("Email : " + bean.getEmail());
			System.out.println("Created By : " + bean.getCreatedBy());
			System.out.println("Modified By : " + bean.getModifiedBy());
			System.out.println("Created Datetime : " + bean.getCreatedDatetime());
			System.out.println("Modified Datetime : " + bean.getModifiedDatetime());
			System.out.println("--------------------------");
		}
	}

	private static void testFindByPk() throws Exception {
		StudentBean bean = new StudentBean();

		bean = model.findByPk(1);

		System.out.println("College Id : " + bean.getCollegeId());
		System.out.println("College Name : " + bean.getCollegeName());
		System.out.println("First Name : " + bean.getFirstName());
		System.out.println("Last Name : " + bean.getLastName());
		System.out.println("DOB : " + bean.getDob());
		System.out.println("Mobile number : " + bean.getMobileNo());
		System.out.println("Email : " + bean.getEmail());
		System.out.println("Created By : " + bean.getCreatedBy());
		System.out.println("Modified By : " + bean.getModifiedBy());
		System.out.println("Created Datetime : " + bean.getCreatedDatetime());
		System.out.println("Modified Datetime : " + bean.getModifiedDatetime());
	}
}
