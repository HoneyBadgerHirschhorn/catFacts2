package homeserver.publicapi;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;

@Repository
public class JDBC {
    public JDBC() {
    }

    ResultSet resultSet = null;
    PreparedStatement statement = null;
    Connection connection = null;

    String username = "*****";
    String password = "*****";
    String url = "jdbc:mysql://localhost:3306/da_projects";
    String tableName = "cat_facts";
    int port = 3306;

    public void readDB() throws SQLException, IOException, InterruptedException {
        String prep = "SELECT * FROM cat_facts";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(prep);
             ResultSet resultSet = statement.executeQuery(prep)) {
            while (resultSet.next()) {  // Iterate over the result set
                String id = resultSet.getString(1);
                String fact = resultSet.getString(2);
                System.out.println(id + ": " + fact);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // or log the exception
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // or log the exception
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace(); // or log the exception
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace(); // or log the exception
                    }
                }
            }
        }
    }

    public void writeDB() throws SQLException {
        String sql = "Insert into cat_facts (facts) values (?)";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            CatFacts facts = new CatFacts();
            String entry = facts.getResult();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entry);
            statement.executeUpdate();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace(); // or log the exception
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // or log the exception
            }

        }
    }
}






