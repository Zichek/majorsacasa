package es.uji.ei1027.majorsacasa.dao;


import es.uji.ei1027.majorsacasa.model.SocialWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository // En Spring els DAOs van anotats amb @Repository
public class SocialWorkerDao {

    private JdbcTemplate jdbcTemplate;

    // Obt� el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix el socialWorker a la base de dades */
    public void addSocialWorker(SocialWorker socialWorker) {
        jdbcTemplate.update("INSERT INTO socialWorker VALUES(?, ?, ?, ?, ?)",
                socialWorker.getName(), socialWorker.getUserCAS(), socialWorker.getPwd(),
                socialWorker.getPhoneNumber(), socialWorker.getEmail());
    }

    /* Esborra el socialWorker de la base de dades */
    public void deleteSocialWorker(SocialWorker socialWorker) {
        jdbcTemplate.update("DELETE from socialWorker where nom=?", socialWorker.getUserCAS());
    }

    /* Actualitza els atributs del socialWorker
       (excepte el nom, que �s la clau prim�ria) */
    public void updateSocialWorker(SocialWorker socialWorker) {
        jdbcTemplate.update("UPDATE socialWorker SET name=?, socialWorkerDNI=?, userCAS=?, pwd=?, phoneNumber=?, email=? ",
               socialWorker.getName(), socialWorker.getUserCAS(), socialWorker.getPwd(),
               socialWorker.getPhoneNumber(), socialWorker.getEmail());
    }

    /* Obt� el socialWorker amb el userCAS donat. Torna null si no existeix. */
    public SocialWorker getSocialWorker(String userCAS) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from SocialWorker WHERE userCAS=?",
                    new SocialWorkerRowMapper(), userCAS);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obt� tots els socialWorkers. Torna una llista buida si no n'hi ha cap. */
    public List<SocialWorker> getSocialWorker() {
        try {
            return jdbcTemplate.query("SELECT * from socialWorker",
                    new SocialWorkerRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<SocialWorker>();
        }
    }
}
