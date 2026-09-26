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

		Connection conn = null;
		long pk = nextPk();

		try {

			conn = JDBCDataSource.getConnection();
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

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement prestmt = conn.prepareStatement("update " + getTable() + " set name = ?, description = ?, duration = ?, modified_by = ?, modified_datetime = ? where id = ?");

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

	@Override
	public String getTable() {

		return "st_course";
	}

	@Override
	public CourseBean getBean() {
		
		return new CourseBean();
	}

	@Override
	public String getWhereClause(CourseBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
