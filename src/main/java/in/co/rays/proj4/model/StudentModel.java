package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class StudentModel extends BaseModel<StudentBean> {

	@Override
	public long add(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		StudentBean existBean = findByMobileNo(bean.getMobileNo());

		if (existBean != null) {
			throw new DuplicateRecordException("student mobile number already exist");
		}

		Connection conn = null;
		long pk = nextPk();

		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPk(bean.getCollegeId());
		bean.setCollegeName(cbean.getName());

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement prestmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?)");

			prestmt.setLong(1, pk);
			prestmt.setLong(2, bean.getCollegeId());
			prestmt.setString(3, bean.getCollegeName());
			prestmt.setString(4, bean.getFirstName());
			prestmt.setString(5, bean.getLastName());
			prestmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			prestmt.setString(7, bean.getMobileNo());
			prestmt.setString(8, bean.getEmail());
			prestmt.setString(9, bean.getCreatedBy());
			prestmt.setString(10, bean.getModifiedBy());
			prestmt.setTimestamp(11, bean.getCreatedDatetime());
			prestmt.setTimestamp(12, bean.getModifiedDatetime());

			prestmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	@Override
	public void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		StudentBean existBean = findByMobileNo(bean.getMobileNo());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("mobile number already exist");
		}

		Connection conn = null;

		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPk(bean.getCollegeId());
		bean.setCollegeName(cbean.getName());

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement prestmt = conn.prepareStatement("update " + getTable()
					+ " set college_id = ?, college_name = ?, first_name = ?, last_name = ?, date_of_birth = ?, mobile_no = ?, email = ?, modified_by = ?, modified_datetime = ? where id = ?");

			prestmt.setLong(1, bean.getCollegeId());
			prestmt.setString(2, bean.getCollegeName());
			prestmt.setString(3, bean.getFirstName());
			prestmt.setString(4, bean.getLastName());
			prestmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			prestmt.setString(6, bean.getMobileNo());
			prestmt.setString(7, bean.getEmail());
			prestmt.setString(8, bean.getModifiedBy());
			prestmt.setTimestamp(9, bean.getModifiedDatetime());
			prestmt.setLong(10, bean.getId());

			prestmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public StudentBean findByMobileNo(String phone) {
		StudentBean bean = findByUniqueColumn("mobile_no", phone);
		return bean;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getWhereClause(StudentBean bean) {
		
		StringBuffer sql = new StringBuffer();
		
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO like '" + bean.getMobileNo() + "%'");
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" AND EMAIL like '" + bean.getEmail() + "%'");
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" AND COLLEGE_NAME = " + bean.getCollegeName());
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {

		return "st_student";
	}

	@Override
	public StudentBean getBean() {

		return new StudentBean();
	}

}
