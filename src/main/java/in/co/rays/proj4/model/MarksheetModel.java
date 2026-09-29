package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class MarksheetModel extends BaseModel<MarksheetBean> {

	@Override
	public long add(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		MarksheetBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("name already exist");
		}

		Connection conn = null;
		long pk = nextPk();

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement prestmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?)");

			prestmt.setLong(1, pk);
			prestmt.setString(2, bean.getRollNo());
			prestmt.setLong(3, bean.getStudentId());
			prestmt.setString(4, bean.getName());
			prestmt.setInt(5, bean.getPhysics());
			prestmt.setInt(6, bean.getChemistry());
			prestmt.setInt(7, bean.getMaths());
			prestmt.setString(8, bean.getCreatedBy());
			prestmt.setString(9, bean.getModifiedBy());
			prestmt.setTimestamp(10, bean.getCreatedDatetime());
			prestmt.setTimestamp(11, bean.getModifiedDatetime());

			prestmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return 0;
	}

	@Override
	public void update(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		MarksheetBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("name already exist");
		}

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement prestmt = conn.prepareStatement("update " + getTable()
					+ " set roll_no = ?, student_id = ?, name = ?, physics = ?, chemistry = ?, maths = ?, modified_by = ?, modified_datetime = ? where id = ?");

			prestmt.setString(1, bean.getRollNo());
			prestmt.setLong(2, bean.getStudentId());
			prestmt.setString(3, bean.getName());
			prestmt.setInt(4, bean.getPhysics());
			prestmt.setInt(5, bean.getChemistry());
			prestmt.setInt(6, bean.getMaths());
			prestmt.setString(7, bean.getModifiedBy());
			prestmt.setTimestamp(8, bean.getModifiedDatetime());
			prestmt.setLong(9, bean.getId());

			prestmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public MarksheetBean findByName(String name) {

		MarksheetBean bean = findByUniqueColumn("name", name);
		return bean;

	}

	@Override
	public String getTable() {

		return "st_marksheet";
	}

	@Override
	public MarksheetBean getBean() {

		return new MarksheetBean();
	}

	@Override
	public String getWhereClause(MarksheetBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
