package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class CourseBean extends BaseBean {

	private String name;
	private String description;
	private String duration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String getValue() {
		
		return name;
	}

	@Override
	public void setResultSet(ResultSet rs) {

		try {
			setName(rs.getString("name"));
			setDescription(rs.getString("description"));
			setDuration(rs.getString("duration"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.setResultSet(rs);
	}

}
