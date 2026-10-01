package in.co.rays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;

public class TestSubjectModel {

	private static SubjectModel model = new SubjectModel();

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
		SubjectBean bean = new SubjectBean();

		bean.setName("Data Structures");
		bean.setDescription("Study of data structures and algorithms");
		bean.setCourseId(1);

		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setModifiedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		long i = model.add(bean);
		System.out.println("record inserted at: " + i);
	}

	private static void testUpdate() throws Exception {
		SubjectBean bean = new SubjectBean();

		bean.setId(1);
		bean.setName("Advanced Data Structures");
		bean.setDescription("Advanced study of data structures and algorithms");
		bean.setCourseId(1);

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
		SubjectBean bean = new SubjectBean();
		List list = new ArrayList();

		bean.setName("Data");

		list = model.search(bean, 1, 5);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (SubjectBean) it.next();

			System.out.println("Name : " + bean.getName());
			System.out.println("Description : " + bean.getDescription());
			System.out.println("Course Id : " + bean.getCourseId());
			System.out.println("Created By : " + bean.getCreatedBy());
			System.out.println("Modified By : " + bean.getModifiedBy());
			System.out.println("Created Datetime : " + bean.getCreatedDatetime());
			System.out.println("Modified Datetime : " + bean.getModifiedDatetime());
			System.out.println("--------------------------");
		}
	}

	private static void testFindByPk() throws Exception {
		SubjectBean bean = new SubjectBean();

		bean = model.findByPk(1);

		System.out.println("Name : " + bean.getName());
		System.out.println("Description : " + bean.getDescription());
		System.out.println("Course Id : " + bean.getCourseId());
		System.out.println("Created By : " + bean.getCreatedBy());
		System.out.println("Modified By : " + bean.getModifiedBy());
		System.out.println("Created Datetime : " + bean.getCreatedDatetime());
		System.out.println("Modified Datetime : " + bean.getModifiedDatetime());
	}

}
