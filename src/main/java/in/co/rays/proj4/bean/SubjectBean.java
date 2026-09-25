package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectBean extends BaseBean{
	
	private String name;
	private String description;
	private long courseId;

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

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
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
			setCourseId(rs.getLong("course_id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		super.setResultSet(rs);
	}

}
