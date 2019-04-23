import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 *  @author     laowu
 *  @version    4/11/2019 6:23 PM
 *
*/
public class Connector {
    private static Connection connection;

    private static String url = "";

    public static Connection getConnection(){
        if (Objects.isNull(connection)) {
            synchronized (connection) {
                if (Objects.isNull(connection)) {
                    try {
                        connection = DriverManager.getConnection(url);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
}
