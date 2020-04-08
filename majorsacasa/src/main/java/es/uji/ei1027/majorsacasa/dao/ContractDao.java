package es.uji.ei1027.majorsacasa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsacasa.model.Contract;

@Repository
public class ContractDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addContract(Contract contract) {
		jdbcTemplate.update("INSERT INTO CONTRACT VALUES(?,?,?,?,CAST(? AS serviceType),?,?,?,?)",
				contract.getNumber(),contract.getDateBeginning(),contract.getDateEnding(),contract.getDescription(),contract.getServiceType().name(),
				contract.getQuantityServices(),contract.getUnitsOfMesure(),contract.getPriceUnit());
	}
	
	public void deleteContract(int number) {
		jdbcTemplate.update("DELETE FROM CONTRACT WHERE NUMBER=?", number);
	}
	
	public void updateContract(Contract contract) {
		jdbcTemplate.update("UPDATE CONTRACT SET companyCIF=?, dateBeginning=?, dateEnding=?,"
				+ "description=?, CAST(? AS serviceType), quantityService=?, unitsOfMesure=?, priceUnit=? where number=?",
				contract.getCompanyCIF(),contract.getDateBeginning(),contract.getDateEnding(), contract.getDescription(),
				contract.getServiceType().name(),contract.getQuantityServices(),contract.getUnitsOfMesure(),contract.getPriceUnit());
	}
	
	public Contract getContract(int number) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM CONTRACT WHERE NUMBER = ?",
					new ContractRowMapper(), number);
		} catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	}
	
	public List<Contract> getAllContract() {
		try {
			return jdbcTemplate.query("SELECT * FROM CONTRACT",
					new ContractRowMapper());
		} catch(EmptyResultDataAccessException e) {
	           return new ArrayList<Contract>();
	       }
	}
}