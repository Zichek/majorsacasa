package es.uji.ei1027.majorsacasa.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.majorsacasa.model.Contract;
import es.uji.ei1027.majorsacasa.model.ServiceType;


public final class ContractRowMapper implements RowMapper<Contract> {
	public Contract mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contract contract = new Contract();
		contract.setNumber(rs.getInt("number"));
		contract.setCompanyCIF(rs.getString("companyCIF"));
		contract.setDateBeginning(rs.getObject("dateBeginning", LocalDate.class));
		contract.setDateEnding(rs.getObject("dateEnding", LocalDate.class));
		contract.setDescription(rs.getString("description"));
		contract.setQuantityServices(rs.getInt("quantityServices"));
		contract.setServiceType(ServiceType.valueOf(rs.getString("serviceType")));
		contract.setUnitsOfMeasure(rs.getString("unitsOfMeasure"));
		contract.setPriceUnit(rs.getInt("priceUnit"));
		System.out.println(contract.toString());
		return contract;
	}
}