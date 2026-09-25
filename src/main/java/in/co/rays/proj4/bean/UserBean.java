package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.util.Date;

public class UserBean extends BaseBean {

	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private Date dob;
	private String mobileNo;
	private long roleId;
	private int unsuccessfulLogin;
	private String gender;
	private Date lastLogin;
	private String UserLock;
	private String registeredIp;
	private String lastLoginIp;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int getUnsuccessfulLogin() {
		return unsuccessfulLogin;
	}

	public void setUnsuccessfulLogin(int unsuccessfulLogin) {
		this.unsuccessfulLogin = unsuccessfulLogin;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getUserLock() {
		return UserLock;
	}

	public void setUserLock(String userLock) {
		UserLock = userLock;
	}

	public String getRegisteredIp() {
		return registeredIp;
	}

	public void setRegisteredIp(String registeredIp) {
		this.registeredIp = registeredIp;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Override
	public String getValue() {

		return firstName;
	}
	
	@Override
	public void setResultSet(ResultSet rs) {
		
		try {
			
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setLogin(rs.getString("login"));
			setPassword(rs.getString("password"));
			setDob(rs.getDate("dob"));
			setMobileNo(rs.getString("mobile_no"));
			setRoleId(rs.getLong("role_id"));
			setUnsuccessfulLogin(rs.getInt("unsuccessful_login"));
			setGender(rs.getString("gender"));
			setLastLogin(rs.getDate("last_login"));
			setUserLock(rs.getString("user_lock"));
			setRegisteredIp(rs.getString("registered_ip"));
			setLastLoginIp(rs.getString("last_login_ip"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.setResultSet(rs);
	}

}
