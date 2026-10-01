package in.co.rays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;

public class TestCollegeModel {

	private static CollegeModel model = new CollegeModel();

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
		CollegeBean bean = new CollegeBean();

		bean.setName("SGSITS");
		bean.setAddress("23 Park Road");
		bean.setState("Madhya Pradesh");
		bean.setCity("Indore");
		bean.setPhoneNo("0731-2438100");

		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setModifiedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		long i = model.add(bean);
		System.out.println("record inserted at: " + i);
	}

	private static void testUpdate() throws Exception {
		CollegeBean bean = new CollegeBean();

		bean.setId(1);
		bean.setName("SGSITS Indore");
		bean.setAddress("23 Park Road");
		bean.setState("Madhya Pradesh");
		bean.setCity("Indore");
		bean.setPhoneNo("0731-2438100");

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
		CollegeBean bean = new CollegeBean();
		List list = new ArrayList();

		bean.setName("SGSITS");

		list = model.search(bean, 1, 5);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (CollegeBean) it.next();

			System.out.println("Name : " + bean.getName());
			System.out.println("Address : " + bean.getAddress());
			System.out.println("State : " + bean.getState());
			System.out.println("City : " + bean.getCity());
			System.out.println("Phone number : " + bean.getPhoneNo());
			System.out.println("Created By : " + bean.getCreatedBy());
			System.out.println("Modified By : " + bean.getModifiedBy());
			System.out.println("Created Datetime : " + bean.getCreatedDatetime());
			System.out.println("Modified Datetime : " + bean.getModifiedDatetime());
			System.out.println("--------------------------");
		}
	}

	private static void testFindByPk() throws Exception {
		CollegeBean bean = new CollegeBean();

		bean = model.findByPk(1);

		System.out.println("Name : " + bean.getName());
		System.out.println("Address : " + bean.getAddress());
		System.out.println("State : " + bean.getState());
		System.out.println("City : " + bean.getCity());
		System.out.println("Phone number : " + bean.getPhoneNo());
		System.out.println("Created By : " + bean.getCreatedBy());
		System.out.println("Modified By : " + bean.getModifiedBy());
		System.out.println("Created Datetime : " + bean.getCreatedDatetime());
		System.out.println("Modified Datetime : " + bean.getModifiedDatetime());
	}

}
