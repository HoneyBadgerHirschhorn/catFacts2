package homeserver.publicapi;

import org.springframework.jdbc.core.JdbcTemplate;

public class jdbcCatFactRepository {
    private JdbcTemplate jdbcTemplate;
    public void JdbcCatFactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveCatFact(String fact){
        jdbcTemplate.update("INSERT INTO cat_facts VALUES (?)",fact);
}

}
