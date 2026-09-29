package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class UserModel extends BaseModel<UserBean> {

	@Override
	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {
		
		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null) {
			throw new DuplicateRecordException("login already exist");
		}

		Connection conn = null;
		long pk = nextPk();

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement prestmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			prestmt.setLong(1, pk);
			prestmt.setString(2, bean.getFirstName());
			prestmt.setString(3, bean.getLastName());
			prestmt.setString(4, bean.getLogin());
			prestmt.setString(5, bean.getPassword());
			prestmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			prestmt.setString(7, bean.getMobileNo());
			prestmt.setLong(8, bean.getRoleId());
			prestmt.setInt(9, bean.getUnsuccessfulLogin());
			prestmt.setString(10, bean.getGender());
			prestmt.setDate(11, new java.sql.Date(bean.getLastLogin().getTime()));
			prestmt.setString(12, bean.getUserLock());
			prestmt.setString(13, bean.getRegisteredIp());
			prestmt.setString(14, bean.getLastLoginIp());
			prestmt.setString(15, bean.getCreatedBy());
			prestmt.setString(16, bean.getModifiedBy());
			prestmt.setTimestamp(17, bean.getCreatedDatetime());
			prestmt.setTimestamp(18, bean.getModifiedDatetime());

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
	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {

		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("login email already exist");
		}

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement prestmt = conn.prepareStatement("update " + getTable()
					+ " set first_name = ?, last_name = ?, login = ?, password = ?, date_of_birth = ?, mobile_no = ?, role_id = ?, unsuccessful_login = ?, gender = ?, last_login = ?, user_lock = ?, registered_ip = ?, last_login_ip = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			prestmt.setString(1, bean.getFirstName());
			prestmt.setString(2, bean.getLastName());
			prestmt.setString(3, bean.getLogin());
			prestmt.setString(4, bean.getPassword());
			prestmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			prestmt.setString(6, bean.getMobileNo());
			prestmt.setLong(7, bean.getRoleId());
			prestmt.setInt(8, bean.getUnsuccessfulLogin());
			prestmt.setString(9, bean.getGender());
			prestmt.setDate(10, new java.sql.Date(bean.getLastLogin().getTime()));
			prestmt.setString(11, bean.getUserLock());
			prestmt.setString(12, bean.getRegisteredIp());
			prestmt.setString(13, bean.getLastLoginIp());
			prestmt.setString(14, bean.getCreatedBy());
			prestmt.setString(15, bean.getModifiedBy());
			prestmt.setTimestamp(16, bean.getCreatedDatetime());
			prestmt.setTimestamp(17, bean.getModifiedDatetime());
			prestmt.setLong(18, bean.getId());

			prestmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public UserBean findByLogin(String login) {

		UserBean bean = findByUniqueColumn("login", login);

		return bean;
	}

	@Override
	public String getTable() {

		return "st_user";
	}

	@Override
	public UserBean getBean() {

		return new UserBean();
	}

	@Override
	public String getWhereClause(UserBean bean) {

//		StringBuffer sql = new StringBuffer("");

		return null;
	}

}
