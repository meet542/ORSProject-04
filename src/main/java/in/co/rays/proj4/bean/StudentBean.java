package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.util.Date;

public class StudentBean extends BaseBean{

	private long collegeId;
	private String collegeName;
	private String firstName;
	private String lastName;
	private Date dob;
	private String mobileNo;
	private String email;

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getValue() {
		
		return firstName;
	}
	
	@Override
	public void setResultSet(ResultSet rs) {
		
		try {
			
			setCollegeId(rs.getLong("college_id"));
			setCollegeName(rs.getString("college_name"));
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setDob(rs.getDate("dob"));
			setMobileNo(rs.getString("mobile_no"));
			setEmail(rs.getString("email"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.setResultSet(rs);
	}

}
