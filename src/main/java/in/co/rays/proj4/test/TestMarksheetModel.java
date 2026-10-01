package in.co.rays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;

public class TestMarksheetModel {
	private static MarksheetModel model = new MarksheetModel();

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
		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo("R001");
		bean.setStudentId(1L);
		bean.setName("Rahul Sharma");
		bean.setPhysics(85);
		bean.setChemistry(88);
		bean.setMaths(92);

		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setModifiedDatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		long i = model.add(bean);
		System.out.println("record inserted at: " + i);
	}

	private static void testUpdate() throws Exception {
		MarksheetBean bean = new MarksheetBean();

		bean.setId(1);
		bean.setRollNo("R001");
		bean.setStudentId(1L);
		bean.setName("Rahul Sharma");
		bean.setPhysics(90);
		bean.setChemistry(91);
		bean.setMaths(95);

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
		MarksheetBean bean = new MarksheetBean();
		List list = new ArrayList();

		bean.setName("Rahul");

		list = model.search(bean, 1, 5);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (MarksheetBean) it.next();

			System.out.println("Roll No : " + bean.getRollNo());
			System.out.println("Student Id : " + bean.getStudentId());
			System.out.println("Name : " + bean.getName());
			System.out.println("Physics : " + bean.getPhysics());
			System.out.println("Chemistry : " + bean.getChemistry());
			System.out.println("Maths : " + bean.getMaths());
			System.out.println("Created By : " + bean.getCreatedBy());
			System.out.println("Modified By : " + bean.getModifiedBy());
			System.out.println("Created Datetime : " + bean.getCreatedDatetime());
			System.out.println("Modified Datetime : " + bean.getModifiedDatetime());
			System.out.println("--------------------------");
		}
	}

	private static void testFindByPk() throws Exception {
		MarksheetBean bean = new MarksheetBean();

		bean = model.findByPk(1);

		System.out.println("Roll No : " + bean.getRollNo());
		System.out.println("Student Id : " + bean.getStudentId());
		System.out.println("Name : " + bean.getName());
		System.out.println("Physics : " + bean.getPhysics());
		System.out.println("Chemistry : " + bean.getChemistry());
		System.out.println("Maths : " + bean.getMaths());
		System.out.println("Created By : " + bean.getCreatedBy());
		System.out.println("Modified By : " + bean.getModifiedBy());
		System.out.println("Created Datetime : " + bean.getCreatedDatetime());
		System.out.println("Modified Datetime : " + bean.getModifiedDatetime());
	}
}
