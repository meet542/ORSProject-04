package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class RoleModel extends BaseModel<RoleBean> {

	@Override
	public long add(RoleBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = nextPk();

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement prestmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?)");

			prestmt.setLong(1, pk);
			prestmt.setString(2, bean.getName());
			prestmt.setString(3, bean.getDescription());
			prestmt.setString(4, bean.getCreatedBy());
			prestmt.setString(5, bean.getModifiedBy());
			prestmt.setTimestamp(6, bean.getCreatedDatetime());
			prestmt.setTimestamp(7, bean.getModifiedDatetime());

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
	public void update(RoleBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement prestmt = conn.prepareStatement(
					"update st_role set name = ?, description = ?, modified_by = ?, modified_datetime = ? where id = ?");

			prestmt.setString(1, bean.getName());
			prestmt.setString(2, bean.getDescription());
			prestmt.setString(3, bean.getModifiedBy());
			prestmt.setTimestamp(4, bean.getModifiedDatetime());
			prestmt.setLong(5, bean.getId());

			prestmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	@Override
	public String getTable() {
		return "st_role";
	}

	@Override
	public RoleBean getBean() {
		return new RoleBean();
	}

}
