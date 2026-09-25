package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class collegeModel extends BaseModel<CollegeBean>{
	
	@Override
	public long add(CollegeBean bean) throws ApplicationException, DuplicateRecordException{
		
		Connection conn = null;
		int pk = nextPk();
		
		try {
			
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement prestmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?)");
			
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
			JDBCDataSource.trnRollBack(conn);;
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}

	@Override
	public void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			PreparedStatement prestmt = conn.prepareStatement("update " + getTable() + " set name = ?, address = ?, state = ?, city = ?, phone_no = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			
		} catch (Exception e) {
			
		}
		
	}

	@Override
	public String getTable() {
		
		return "st_college";
	}

	@Override
	public CollegeBean getBean() {
		
		return new CollegeBean();
	}

}
