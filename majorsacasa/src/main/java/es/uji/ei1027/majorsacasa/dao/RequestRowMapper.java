package es.uji.ei1027.majorsacasa.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.majorsacasa.model.Request;
import es.uji.ei1027.majorsacasa.model.ServiceType;
import es.uji.ei1027.majorsacasa.model.State;

public final class RequestRowMapper implements RowMapper<Request> {
	public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
		Request request = new Request();
		request.setNumber(rs.getInt("number"));
		request.setElderlyDNI(rs.getString("elderlyDNI"));
		request.setContractNumber(rs.getInt("contractNumber"));
		request.setServiceType(ServiceType.valueOf(rs.getString("serviceType")));
		request.setCreationDate(rs.getObject("creationDate", Date.class));
		request.setState(State.valueOf(rs.getString("state")));
		request.setApprovedDate(rs.getObject("approvedDate", Date.class));
		request.setRejectedDate(rs.getObject("rejectedDate", Date.class));
		request.setComments(rs.getString("comments"));
		request.setEndDate(rs.getObject("endDate", Date.class));
		return request;
	}
}
