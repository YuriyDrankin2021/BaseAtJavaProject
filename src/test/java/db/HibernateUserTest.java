package db;

import com.github.javafaker.Faker;
import db.models.Auto;
import db.models.User;
import db.services.AutoService;
import db.services.UserService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateUserTest {
    private static final Logger logger = LoggerFactory.getLogger(HibernateUserTest.class);
    UserService userService = new UserService();
    AutoService autoService = new AutoService();
    Faker faker = new Faker();
    SoftAssertions softAssertions = new SoftAssertions();
    User user;

    @AfterAll
    void tearDown() {
        userService.deleteUser(user);
    }

    @Test
    @DisplayName("Create new user in db")
    public void createUserInDbTest() {
        logger.debug("Start test 'Create new user in db'");
        user = new User(faker.name().firstName(), faker.number().numberBetween(1, 100));
        user = userService.saveUser(user);
        logger.info(user.toString());
        User userResult = userService.findUserById(user.getId());

        softAssertions.assertThat(userResult.getName()).as("Check user name").isEqualTo(user.getName());
        softAssertions.assertThat(userResult.getAge()).as("Check user age").isEqualTo(user.getAge());

        for (int i = 0; i < 2; i++) {
            Auto testAuto = new Auto(faker.commerce().productName(), faker.color().name());
            testAuto.setUser(user);
            user.addAuto(testAuto);
        }
        user = userService.updateUser(user);
        List<Auto> autoList = autoService.findAllAutos();
        softAssertions.assertThat(autoList).hasSize(2);
        softAssertions.assertAll();
    }

}
