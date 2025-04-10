import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private final static String URL="jdbc:mysql://localhost:3306/db_futebol?createDatabaseIfNotExist=true";
    private final static String USER = "root";
    private final static String PASSWORD = "root"; // TEORIA: Talvez tenha alterado para root quando a gente utilizou o Docker

    public static Connection getConnection () throws SQLException {
        return DriverManager.getConnection( URL, USER, PASSWORD );
    }
}