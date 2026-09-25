package in.co.rays.proj4.bean;

import java.util.Date;
import java.sql.ResultSet;

public class FacultyBean extends BaseBean {

	private long collegeId;
	private String collegeName;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNo;
	private String address;
	private String gender;
	private Date dob;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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
			setEmail(rs.getString("email"));
			setMobileNo(rs.getString("mobile_no"));
			setAddress(rs.getString("address"));
			setGender(rs.getString("gender"));
			setDob(rs.getDate("dob"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.setResultSet(rs);
	}

}
