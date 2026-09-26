package in.co.rays.proj4.model;

import java.sql.Connection;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class MarksheetModel extends BaseModel<MarksheetBean>{

	@Override
	public long add(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return 0;
	}

	@Override
	public void update(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MarksheetBean getBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWhereClause(MarksheetBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
