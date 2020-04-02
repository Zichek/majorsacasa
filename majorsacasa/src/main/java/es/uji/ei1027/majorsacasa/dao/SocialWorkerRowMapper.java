package es.uji.ei1027.majorsacasa.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.majorsacasa.model.SocialWorker;;


public class SocialWorkerRowMapper implements RowMapper<SocialWorker> {
	public SocialWorker mapRow(ResultSet rs, int rowNum) throws SQLException {
		SocialWorker socialWorker = new SocialWorker();
		socialWorker.setName(rs.getString("name"));
		socialWorker.setElderlyDNI(rs.getString("elderlyDNI"));
		socialWorker.setUserCAS(rs.getString("userCAS"));
		socialWorker.setPwd(rs.getString("pwd"));
		socialWorker.setPhoneNumber(rs.getInt("phoneNumber"));
		socialWorker.setEmail(rs.getString("email"));
		return socialWorker;
	}
}