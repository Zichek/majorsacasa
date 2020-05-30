package es.uji.ei1027.majorsacasa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsacasa.model.Invoice;

@Repository
public class InvoiceDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addInvoice(Invoice invoice) {
		jdbcTemplate.update("INSERT INTO INVOICE VALUES(DEFAULT,?,?,?,?,?)",
				invoice.getElderlyDNI(), invoice.getRequestNumber(),invoice.getDate(),invoice.getAmount(),invoice.getConcept());
	}
	
	public void deleteInvoice(int number) {
		jdbcTemplate.update("DELETE FROM INVOICE WHERE NUMBER=?", number);
	}
	
	public void updateInvoice(Invoice invoice) {
		jdbcTemplate.update("UPDATE INVOICE SET elderlyDNI=?, requestNumber=?,"
				+ "date=?, amount=?, concept=? where number=?",
				invoice.getElderlyDNI(),invoice.getRequestNumber(),invoice.getDate(),invoice.getAmount(),invoice.getConcept(),invoice.getNumber());
	}
	
	public Invoice getInvoice(int number) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM invoice WHERE number = ?",
					new InvoiceRowMapper(), number);
		} catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	}
	
	public List<Invoice> getAllInvoice() {
		try {
			return jdbcTemplate.query("SELECT * FROM INVOICE",
					new InvoiceRowMapper());
		} catch(EmptyResultDataAccessException e) {
	           return new ArrayList<Invoice>();
	       }
	}
}