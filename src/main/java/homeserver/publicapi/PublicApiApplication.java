package homeserver.publicapi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class PublicApiApplication {
	public static void main(String[] args) throws IOException, SQLException, InterruptedException {
		SpringApplication.run(PublicApiApplication.class, args);
		CatFacts catQuiz = new CatFacts();
		JDBC jdbc = new JDBC();
		catQuiz.getResult();
		jdbc.writeDB();
		jdbc.readDB();
		System.exit(0);
	}
}
