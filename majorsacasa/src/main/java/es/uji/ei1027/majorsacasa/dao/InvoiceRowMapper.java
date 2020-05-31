package es.uji.ei1027.majorsacasa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.majorsacasa.model.Invoice;

public final class InvoiceRowMapper implements RowMapper<Invoice> {
	public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
		Invoice invoice = new Invoice();
		invoice.setNumber(rs.getInt("number"));
		invoice.setElderlyDNI(rs.getString("elderlyDNI"));
		invoice.setRequestNumber(rs.getInt("requestNumber"));
		invoice.setDate(rs.getObject("date", LocalDate.class));
		invoice.setAmount(rs.getInt("amount"));
		invoice.setConcept(rs.getString("concept"));
		return invoice;
	}
}