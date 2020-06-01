package es.uji.ei1027.majorsacasa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsacasa.model.Company;

@Repository
public class CompanyDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addCompany(Company company) {
		jdbcTemplate.update("INSERT INTO COMPANY VALUES(?,?, ?,?,?,?,?,CAST(? AS serviceType))",
				company.getName(),company.getCIF(),company.getPassword(), company.getAddress(),company.getContactPersonName(),
				company.getContactPersonPhoneNumber(),company.getContactPersonEmail(),company.getServiceType().name());
		System.out.println("Buenas "+company.getName()+" ,queriamos informarles de que su empresa ha sido aceptada dentro del programa MajorsADomicili. "
				+ "Su usuario y contraseña para acceder a la plataforma es: "+company.getCIF()+" y "+ company.getPassword());
	}
	
	public void deleteCompany(String CIF) {
		jdbcTemplate.update("DELETE FROM COMPANY WHERE CIF=?", CIF);
	}
	
	public void updateCompany(Company company) {
		jdbcTemplate.update("UPDATE COMPANY SET name=?, password=?, address=?, contactPersonName=?,"
				+ "contactPersonPhoneNumber=?, contactPersonEmail=?, serviceType=CAST(? AS serviceType) where CIF=?",
				company.getName(), company.getPassword(),  company.getAddress(), company.getContactPersonName(), 
				company.getContactPersonPhoneNumber(), company.getContactPersonEmail(),
				company.getServiceType().name(), company.getCIF());
	}
	
	
	public Company getCompany(String CIF) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM COMPANY WHERE CIF = ?",
					new CompanyRowMapper(), CIF);
		} catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	}
	
	public List<Company> getAllCompany() {
		try {
			return jdbcTemplate.query("SELECT * FROM COMPANY",
					new CompanyRowMapper());
		} catch(EmptyResultDataAccessException e) {
	           return new ArrayList<Company>();
	       }
	}
	
}
