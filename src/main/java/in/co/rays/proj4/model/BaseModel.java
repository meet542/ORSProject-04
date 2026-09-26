package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public abstract class BaseModel<T extends BaseBean> {

	public abstract long add(T bean) throws ApplicationException, DuplicateRecordException;

	public abstract void update(T bean) throws ApplicationException, DuplicateRecordException;

	public abstract String getTable();

	public abstract T getBean();

	public abstract String getWhereClause(T bean);

	public long nextPk() {

		Connection conn = null;
		long pk = 0;

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement prestmt = conn.prepareStatement("select max(id) from " + getTable());
			ResultSet rs = prestmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;

	}

	public void delete(long id) {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement prestmt = conn.prepareStatement("delete from " + getTable() + " where id = ?");
			prestmt.setLong(1, id);

			int i = prestmt.executeUpdate();
			conn.commit();
			System.out.println("record deleted at: " + i);

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public T findByPk(long pk) {

		T bean = null;
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement prestmt = conn.prepareStatement("select * from " + getTable() + " where id = ?");
			prestmt.setLong(1, pk);

			ResultSet rs = prestmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	public List<T> search(T bean, int pageNo, int pageSize) {

		List<T> list = new ArrayList<T>();
		Connection conn = null;
		StringBuffer sql = new StringBuffer("select * from " + getTable() + " where 1=1");// sql injection

		sql.append(getWhereClause(bean));

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql =======>>>>> " + sql.toString());

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement prestmt = conn.prepareStatement(sql.toString());

			ResultSet rs = prestmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}

	public T findByUniqueColumn(String column, String value) {

		T bean = null;
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement prestmt = conn
					.prepareStatement("select * from " + getTable() + " where " + column + " = ?");

			prestmt.setString(1, value);

			ResultSet rs = prestmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

}
