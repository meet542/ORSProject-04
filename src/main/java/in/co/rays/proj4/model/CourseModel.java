package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CourseModel extends BaseModel<CourseBean> {

	@Override
	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		CourseBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("course name already exist.");
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
			prestmt.setString(4, bean.getDuration());
			prestmt.setString(5, bean.getCreatedBy());
			prestmt.setString(6, bean.getModifiedBy());
			prestmt.setTimestamp(7, bean.getCreatedDatetime());
			prestmt.setTimestamp(8, bean.getModifiedDatetime());

			prestmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {

			e.printStackTrace();
			JDBCDataSource.closeConnection(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);

		}

		return pk;
	}

	@Override
	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		CourseBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("course name already exist");
		}

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement prestmt = conn.prepareStatement("update " + getTable()
					+ " set name = ?, description = ?, duration = ?, modified_by = ?, modified_datetime = ? where id = ?");

			prestmt.setString(1, bean.getName());
			prestmt.setString(2, bean.getDescription());
			prestmt.setString(3, bean.getDuration());
			prestmt.setString(4, bean.getModifiedBy());
			prestmt.setTimestamp(5, bean.getModifiedDatetime());
			prestmt.setLong(6, bean.getId());

			prestmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {

			e.printStackTrace();
			JDBCDataSource.closeConnection(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public CourseBean findByName(String name) {

		CourseBean bean = findByUniqueColumn("name", name);
		return bean;
	}
	
	@Override
	public String getWhereClause(CourseBean bean) {
		StringBuffer sql = new StringBuffer();

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION like '" + bean.getDescription() + "%'");
			}
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" AND DURATION like '" + bean.getDuration() + "%'");
			}

		}

		return sql.toString();
	}

	@Override
	public String getTable() {

		return "st_course";
	}

	@Override
	public CourseBean getBean() {

		return new CourseBean();
	}


}
