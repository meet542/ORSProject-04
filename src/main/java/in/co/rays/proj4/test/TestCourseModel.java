package in.co.rays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;

public class TestCourseModel {

	private static CourseModel model = new CourseModel();

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
		CourseBean bean = new CourseBean();

		bean.setName("Computer Science");
		bean.setDescription("Computer Science and Engineering");
		bean.setDuration("4 Years");

		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setModifiedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		long i = model.add(bean);
		System.out.println("record inserted at: " + i);
	}

	private static void testUpdate() throws Exception {
		CourseBean bean = new CourseBean();

		bean.setId(1);
		bean.setName("Computer Science Engineering");
		bean.setDescription("Computer Science and Engineering Course");
		bean.setDuration("4 Years");

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
		CourseBean bean = new CourseBean();
		List list = new ArrayList();

		bean.setName("Computer");

		list = model.search(bean, 1, 5);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (CourseBean) it.next();

			System.out.println("Name : " + bean.getName());
			System.out.println("Description : " + bean.getDescription());
			System.out.println("Duration : " + bean.getDuration());
			System.out.println("Created By : " + bean.getCreatedBy());
			System.out.println("Modified By : " + bean.getModifiedBy());
			System.out.println("Created Datetime : " + bean.getCreatedDatetime());
			System.out.println("Modified Datetime : " + bean.getModifiedDatetime());
			System.out.println("--------------------------");
		}
	}

	private static void testFindByPk() throws Exception {
		CourseBean bean = new CourseBean();

		bean = model.findByPk(1);

		System.out.println("Name : " + bean.getName());
		System.out.println("Description : " + bean.getDescription());
		System.out.println("Duration : " + bean.getDuration());
		System.out.println("Created By : " + bean.getCreatedBy());
		System.out.println("Modified By : " + bean.getModifiedBy());
		System.out.println("Created Datetime : " + bean.getCreatedDatetime());
		System.out.println("Modified Datetime : " + bean.getModifiedDatetime());
	}

}
