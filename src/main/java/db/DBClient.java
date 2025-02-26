package db;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static helpers.TestConfig.config;

@Getter
public class DBClient {
    private static final Logger logger = LoggerFactory.getLogger(DBClient.class);
    static Properties properties = config();
    static String userName = properties.getProperty("db.User");
    static String userPass = properties.getProperty("db.Pass");
    static String dbUrl = properties.getProperty("db.Url");

    private Connection connection;

    public void openDBConnection() throws SQLException {
        connection = DriverManager.getConnection(dbUrl,userName,userPass);
        logger.info("Connection to the database has been created");
    }

    public void closeDBConnection(){
        try {
            connection.close();
        }catch (Exception e){
            logger.info(String.valueOf(e));
        }

        logger.info("Connection to the database has been closed");
    }


}
