import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        System.out.println( ClubeCRUD.adicionarClube( new Clube( "a", "2")).toString() );
    }


}