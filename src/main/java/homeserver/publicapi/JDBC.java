package homeserver.publicapi;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;

@Repository
public class JDBC {
    public JDBC() {
    }

    String username = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/da_projects";
    String tableName = "cat_facts";
    int port = 3306;

    public void readDB() throws SQLException, IOException, InterruptedException {

        String prep = "SELECT * FROM cat_facts";
        try (Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement statement = connection.prepareStatement(prep);
             ResultSet resultSet = statement.executeQuery(prep)) {

            while (resultSet.next()) {  // Iterate over the result set
                String id = resultSet.getString(1);
                String fact = resultSet.getString(2);
                System.out.println(id + ": " + fact);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }

    }

    public void writeDB() throws IOException, InterruptedException, SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {


            CatFacts facts = new CatFacts();
            String entry = facts.getResult();
            String sql = "Insert into cat_facts (facts) values (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entry);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
    }
}



