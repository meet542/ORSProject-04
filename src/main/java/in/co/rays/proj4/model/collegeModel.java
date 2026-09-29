package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CollegeModel extends BaseModel<CollegeBean> {

	@Override
	public long add(CollegeBean bean) throws ApplicationException, DuplicateRecordException {

		CollegeBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("college name already exist");
		}

		Connection conn = null;
		long pk = nextPk();

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement prestmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?)");

			prestmt.setLong(1, pk);
			prestmt.setString(2, bean.getName());
			prestmt.setString(3, bean.getAddress());
			prestmt.setString(4, bean.getState());
			prestmt.setString(5, bean.getCity());
			prestmt.setString(6, bean.getPhoneNo());
			prestmt.setString(7, bean.getCreatedBy());
			prestmt.setString(8, bean.getModifiedBy());
			prestmt.setTimestamp(9, bean.getCreatedDatetime());
			prestmt.setTimestamp(10, bean.getModifiedDatetime());

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
	public void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {

		CollegeBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("course name already exist.");
		}

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement prestmt = conn.prepareStatement("update " + getTable()
					+ " set name = ?, address = ?, state = ?, city = ?, phone_no = ?, modified_by = ?, modified_datetime = ? where id = ?");

			prestmt.setString(1, bean.getName());
			prestmt.setString(2, bean.getAddress());
			prestmt.setString(3, bean.getState());
			prestmt.setString(4, bean.getCity());
			prestmt.setString(5, bean.getPhoneNo());
			prestmt.setString(6, bean.getModifiedBy());
			prestmt.setTimestamp(7, bean.getModifiedDatetime());
			prestmt.setLong(8, bean.getId());

			prestmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public CollegeBean findByName(String name) {

		CollegeBean bean = findByUniqueColumn("name", name);

		return bean;
	}

	@Override
	public String getTable() {

		return "st_college";
	}

	@Override
	public CollegeBean getBean() {

		return new CollegeBean();
	}

	@Override
	public String getWhereClause(CollegeBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
