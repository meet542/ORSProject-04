package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class FacultyModel extends BaseModel<FacultyBean> {

	@Override
	public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			throw new DuplicateRecordException("faculty email already exist");
		}

		Connection conn = null;
		long pk = nextPk();

		CollegeModel cModel = new CollegeModel();
		CollegeBean cBean = cModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(cBean.getName());

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement prestmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			prestmt.setLong(1, pk);
			prestmt.setLong(2, bean.getCollegeId());
			prestmt.setString(3, bean.getCollegeName());
			prestmt.setString(4, bean.getFirstName());
			prestmt.setString(5, bean.getLastName());
			prestmt.setString(6, bean.getEmail());
			prestmt.setString(7, bean.getMobileNo());
			prestmt.setString(8, bean.getAddress());
			prestmt.setString(9, bean.getGender());
			prestmt.setDate(10, new java.sql.Date(bean.getDob().getTime()));
			prestmt.setString(11, bean.getCreatedBy());
			prestmt.setString(12, bean.getModifiedBy());
			prestmt.setTimestamp(13, bean.getCreatedDatetime());
			prestmt.setTimestamp(14, bean.getModifiedDatetime());

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
	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {
		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("faculty email already exist");
		}

		Connection conn = null;

		CollegeModel cModel = new CollegeModel();
		CollegeBean cBean = cModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(cBean.getName());

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement prestmt = conn.prepareStatement("update " + getTable()
					+ " set college_id = ?, college_name = ?, first_name = ?, last_name = ?, email = ?, mobile_no = ?, address = ?, gender = ?, date_of_birth = ? , modified_by = ?, modified_datetime = ? where id = ?");

			prestmt.setLong(1, bean.getCollegeId());
			prestmt.setString(2, bean.getCollegeName());
			prestmt.setString(3, bean.getFirstName());
			prestmt.setString(4, bean.getLastName());
			prestmt.setString(5, bean.getEmail());
			prestmt.setString(6, bean.getMobileNo());
			prestmt.setString(7, bean.getAddress());
			prestmt.setString(8, bean.getGender());
			prestmt.setDate(9, new java.sql.Date(bean.getDob().getTime()));
			prestmt.setString(10, bean.getModifiedBy());
			prestmt.setTimestamp(11, bean.getModifiedDatetime());
			prestmt.setLong(12, bean.getId());

			prestmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public FacultyBean findByEmail(String email) {

		FacultyBean bean = findByUniqueColumn("email", email);

		return bean;
	}

	@Override
	public String getWhereClause(FacultyBean bean) {

		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" AND EMAIL like '" + bean.getEmail() + "%'");
			}
			if (bean.getCollegeId() > 0) {
				sql.append(" AND COLLEGE_ID = " + bean.getCollegeId());
			}
		}
		return sql.toString();

	}

	@Override
	public String getTable() {

		return "st_faculty";
	}

	@Override
	public FacultyBean getBean() {

		return new FacultyBean();

	}

}
