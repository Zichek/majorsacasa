package es.uji.ei1027.majorsacasa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsacasa.model.Request;

@Repository
public class RequestDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addRequest(Request request) {
		jdbcTemplate.update("INSERT INTO REQUEST VALUES(?,?,?,CAST(? AS serviceType),?,CAST(? AS state),?,?,?,?)",
				request.getNumber(),request.getElderlyDNI(),request.getContractNumber(),request.getServiceType().name(),request.getCreationDate(),
				request.getState().name(),request.getApprovedDate(),request.getRejectedDate(),request.getComments(),request.getEndDate());
	}
	
	public void deleteRequest(int number) {
		jdbcTemplate.update("DELETE FROM REQUEST WHERE NUMBER=?", number);
	}
	
	public void updateRequest(Request request) {
		jdbcTemplate.update("UPDATE REQUEST SET elderlyDNI=?, contractNumber=?, CAST(? AS serviceType),"
				+ "creationDate=?, CAST(? AS state), approvedDate=?, rejectedDate=?, comments=?, endDate=? where number=?",
				request.getElderlyDNI(),request.getContractNumber(),request.getServiceType().name(),request.getCreationDate(),request.getState().name(),
				request.getApprovedDate(),request.getRejectedDate(),request.getComments(),request.getEndDate());
	}
	
	public Request getRequest(int number) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM REQUEST WHERE NUMBER = ?",
					new RequestRowMapper(), number);
		} catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	}
	
	public List<Request> getAllRequest() {
		try {
			return jdbcTemplate.query("SELECT * FROM Request",
					new RequestRowMapper());
		} catch(EmptyResultDataAccessException e) {
	           return new ArrayList<Request>();
	       }
	}
}