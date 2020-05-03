package es.uji.ei1027.majorsacasa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsacasa.model.User;

@Repository
public class UserDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addUser(String username, String password, String role) {
		jdbcTemplate.update("INSERT INTO users VALUES(?,?,?)",
				username, password, role);
		}
	
	public void deleteUser(String username) {
		jdbcTemplate.update("DELETE FROM users WHERE username=?", username);
	}
	
	public void updateUser(String username, String password) {
		jdbcTemplate.update("UPDATE users SET password=? where username=?",
				username, password);
	}
	public void updateUser(User user) {
		jdbcTemplate.update("UPDATE users SET password=? where username=?",
				user.getPassword(), user.getUsername());
	}
	
	public User getUser(String username, String password) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=? AND password=?",
					new UserRowMapper(), username, password);
		} catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	}
	public User getUser(User user) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=?",
					new UserRowMapper(), user.getUsername());
		} catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	}
	
	public List<User> getAllUser() {
		try {
			return jdbcTemplate.query("SELECT * FROM users",
					new UserRowMapper());
		} catch(EmptyResultDataAccessException e) {
	           return new ArrayList<User>();
	       }
	}
}
