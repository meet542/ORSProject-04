package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class SubjectModel extends BaseModel<SubjectBean> {

	@Override
	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {

		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("role name already exist");
		}

		Connection conn = null;
		long pk = nextPk();

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement prestmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");

			prestmt.setLong(1, pk);
			prestmt.setString(2, bean.getName());
			prestmt.setString(3, bean.getDescription());
			prestmt.setLong(4, bean.getCourseId());
			prestmt.setString(5, bean.getCreatedBy());
			prestmt.setString(6, bean.getModifiedBy());
			prestmt.setTimestamp(7, bean.getCreatedDatetime());
			prestmt.setTimestamp(8, bean.getModifiedDatetime());

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
	public void update(SubjectBean bean) throws ApplicationException, DuplicateRecordException {

		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("role name already exist");
		}

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement prestmt = conn.prepareStatement("update " + getTable()
					+ " set name = ?, description = ?, course_id = ?, modified_by = ?, modified_datetime = ? where id = ?");

			prestmt.setString(1, bean.getName());
			prestmt.setString(2, bean.getDescription());
			prestmt.setLong(3, bean.getCourseId());
			prestmt.setString(4, bean.getModifiedBy());
			prestmt.setTimestamp(5, bean.getModifiedDatetime());
			prestmt.setLong(6, bean.getId());

			prestmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public SubjectBean findByName(String name) {

		SubjectBean bean = findByUniqueColumn("name", name);

		return bean;
	}

	@Override
	public String getTable() {

		return "st_subject";
	}

	@Override
	public SubjectBean getBean() {

		return new SubjectBean();
	}

	@Override
	public String getWhereClause(SubjectBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
