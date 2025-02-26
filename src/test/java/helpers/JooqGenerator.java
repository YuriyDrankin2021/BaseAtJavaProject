package helpers;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static helpers.TestConfig.config;

public class JooqGenerator {
    public static final Logger logger = LoggerFactory.getLogger(JooqGenerator.class);
    static Properties properties = config();
    static String userName = properties.getProperty("db.User");
    static String userPass = properties.getProperty("db.Pass");
    static String dbUrl = properties.getProperty("db.Url");

    static void generateSchema(){
        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver("org.postgresql.Driver")
                        .withUrl(dbUrl)
                        .withUser(userName)
                        .withPassword(userPass)
                ).withGenerator(new Generator()
                        .withDatabase(new Database()
                                .withName("org.jooq.postgres.PostgresDataBase")
                                .withIncludes(".*")
                                .withExcludes("")
                                .withInputSchema("public"))
                        .withTarget(new Target()
                                .withPackageName("db.dto")
                                .withDirectory("src/main/java")
                        )
                );
        try {
            GenerationTool.generate(configuration);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        generateSchema();
    }
}
