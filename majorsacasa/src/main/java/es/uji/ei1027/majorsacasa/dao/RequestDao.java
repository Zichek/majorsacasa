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
		jdbcTemplate.update("INSERT INTO REQUEST VALUES(DEFAULT,?,?,CAST(? AS serviceType),?,?,CAST(? AS state),?,?,?,?)",
				request.getElderlyDNI(),null,request.getServiceType().name(),request.getCreationDate(), request.getSchedule(),
				"DONE",null,null,request.getComments(),request.getEndDate());
		System.out.println("Email al elderly: Su solicitud se ha registrado correctamente. Nos pondremos en contacto con usted con la mayor brevedad posible.");
	}
	
	public void deleteRequest(int number) {
		jdbcTemplate.update("DELETE FROM REQUEST WHERE NUMBER=?", number);
	}
	
	public void updateRequest(Request request) {
		jdbcTemplate.update("UPDATE REQUEST SET elderlyDNI=?, contractNumber=?, serviceType=CAST(? AS serviceType),"
				+ "creationDate=?,schedule=?, state=CAST(? AS state), approvedDate=?, rejectedDate=?, comments=?, endDate=? where number=?",
				request.getElderlyDNI(),request.getContractNumber(),request.getServiceType().name(),request.getCreationDate(),request.getSchedule(),request.getState().name(),
				request.getApprovedDate(),request.getRejectedDate(),request.getComments(),request.getEndDate(), request.getNumber());
		System.out.println("Email a la empresa: Buenas, se le acaba de asignar un nuevo contrato con el numero "+ request.getContractNumber()+" para dar servicio al anciano con DNI: "+
				request.getElderlyDNI()+" entre el "+request.getApprovedDate()+" y "+request.getEndDate()+".");
	}
	
	public Request getRequest(int number) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM REQUEST WHERE NUMBER = ?",
					new RequestRowMapper(), number);
		} catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	}
	
	public List<Request> getRequestByCIF(String companyCIF) {
		try {
			return jdbcTemplate.query("SELECT r.* FROM request r, contract c, company co WHERE r.number=c.number AND c.companyCIF=co.CIF AND c.companyCIF = ? AND r.state='ACCEPTED' ",
					new RequestRowMapper(), companyCIF);
		} catch(EmptyResultDataAccessException e) {
				return new ArrayList<Request>();
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
	
	public List<Request> getAllRequestStateDone() {
		try {
			return jdbcTemplate.query("SELECT * FROM Request WHERE state='DONE'",
					new RequestRowMapper());
		} catch(EmptyResultDataAccessException e) {
	           return new ArrayList<Request>();
	       }
	}
	
	public List<Request> getRequestByDNI(String elderlyDNI) {
		try {
			return jdbcTemplate.query("SELECT * FROM request WHERE elderlyDNI= ? ",
					new RequestRowMapper(), elderlyDNI);
		} catch(EmptyResultDataAccessException e) {
				return new ArrayList<Request>();
	       }
	}
}