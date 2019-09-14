package com.sgic.spring.project;

import org.springframework.jdbc.core.JdbcTemplate;

public class ProjectDao {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int saveProject(Project p) {
		String query = "INSERT INTO projects (project_name,project_description) VALUES ('"+p.getProjectName()+"','"+p.getProjectDescription()+"')";
		return jdbcTemplate.update(query);
	}
}
