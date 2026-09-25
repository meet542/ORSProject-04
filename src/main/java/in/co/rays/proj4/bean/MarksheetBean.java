package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class MarksheetBean extends BaseBean {

	private String rollNo;
	private long studentId;
	private String name;
	private Integer physics;
	private Integer chemistry;
	private Integer maths;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhysics() {
		return physics;
	}

	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	public Integer getChemistry() {
		return chemistry;
	}

	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@Override
	public String getValue() {
		return name;
	}

	@Override
	public void setResultSet(ResultSet rs) {

		try {

			setRollNo(rs.getString("roll_no"));
			setStudentId(rs.getLong("student_id"));
			setName(rs.getString("name"));
			setPhysics(rs.getInt("physics"));
			setChemistry(rs.getInt("chemistry"));
			setMaths(rs.getInt("maths"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		super.setResultSet(rs);
	}

}