package db;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ConnectionTest {

    @Test
    @Tag("DB")
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
