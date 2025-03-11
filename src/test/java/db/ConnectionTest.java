package db;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ConnectionTest {

    @Test
    void connectionTest(){
        DBClient client = new DBClient();
        try {
            client.openDBConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            client.closeDBConnection();
        }
    }
}
