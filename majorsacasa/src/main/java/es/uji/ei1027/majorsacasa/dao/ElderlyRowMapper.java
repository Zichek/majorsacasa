package es.uji.ei1027.majorsacasa.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.majorsacasa.model.Elderly;


public class ElderlyRowMapper implements RowMapper<Elderly> {
	public Elderly mapRow(ResultSet rs, int rowNum) throws SQLException {
		Elderly elderly = new Elderly();
		elderly.setName(rs.getString("name"));
		elderly.setDNI(rs.getString("DNI"));
		elderly.setSurname(rs.getString("surname"));
		elderly.setBirthDate(rs.getObject("birthDate", Date.class));
		elderly.setAddress(rs.getString("address"));
		elderly.setPhoneNumber(rs.getString("phoneNumber"));
		elderly.setBankAccountNumber(rs.getInt("bankAccountNumber"));
		elderly.setEmail(rs.getString("email"));
		elderly.setUserPwd(rs.getString("userPwd"));
		elderly.setDateCreation(rs.getObject("dateCreation", Date.class));
		elderly.setAlergies(rs.getString("alergies"));
		elderly.setDiseases(rs.getString("diseases"));
		return elderly;
	}
}