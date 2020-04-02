package es.uji.ei1027.majorsacasa.dao;


import es.uji.ei1027.majorsacasa.model.Elderly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository // En Spring els DAOs van anotats amb @Repository
public class ElderlyDao {

    private JdbcTemplate jdbcTemplate;

    // Obt� el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix el elderly a la base de dades */
    public void addElderly(Elderly elderly) {
        jdbcTemplate.update("INSERT INTO elderly VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                elderly.getName(), elderly.getDNI(), elderly.getSurname(), elderly.getBirthDate(), elderly.getAddress(), elderly.getPhoneNumber(), elderly.getBankAccountNumber(),
                elderly.getEmail(), elderly.getUserPwd(), elderly.getDateCreation(), elderly.getAlergies(), elderly.getDiseases());
    }

    /* Esborra el elderly de la base de dades */
    public void deleteElderly(String DNI) {
        jdbcTemplate.update("DELETE from elderly where DNI=?", DNI);
    }

    /* Actualitza els atributs del elderly
       (excepte el nom, que �s la clau prim�ria) */
    public void updateElderly(Elderly elderly) {
        jdbcTemplate.update("UPDATE elderly SET name=?, surname=?, birthDate=?, address=?, phoneNumber=?,"
        		+ "bankAccountNumber=?, email=?, userPwd=?, dateCreation=?, alergies=?, diseases=? where DNI=?",
               elderly.getName(), elderly.getSurname(), elderly.getBirthDate(), elderly.getAddress(), elderly.getPhoneNumber(),
               elderly.getBankAccountNumber(), elderly.getEmail(), elderly.getUserPwd(), elderly.getDateCreation(), elderly.getAlergies(),
               elderly.getDiseases());
    }

    /* Obt� el elderly amb el nom donat. Torna null si no existeix. */
    public Elderly getElderly(String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from Elderly WHERE name=?",
                    new ElderlyRowMapper(), name);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obt� tots els elderlys. Torna una llista buida si no n'hi ha cap. */
    public List<Elderly> getElderlys() {
        try {
            return jdbcTemplate.query("SELECT * from Elderly",
                    new ElderlyRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Elderly>();
        }
    }
}
