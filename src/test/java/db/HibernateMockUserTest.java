package db;

import com.github.javafaker.Faker;
import db.models.User;
import db.services.UserService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class HibernateMockUserTest {
    private static final Logger logger = LoggerFactory.getLogger(HibernateMockUserTest.class);
    Faker faker = new Faker();
    SoftAssertions softAssertions = new SoftAssertions();

    @Spy
    UserService userService;

    @Test
    @DisplayName("Create new user in db")
    @Tag("DB")
    public void createMockUserInDbTest() {
        logger.debug("Start test 'Create new user in db mock'");
        User mockUser = new User(faker.name().firstName(), faker.number().numberBetween(1, 100));
        Mockito.doReturn(mockUser).when(userService).saveUser(any(User.class));

        User resultUser = userService.saveUser( new User(faker.name().firstName(), faker.number().numberBetween(1, 100)));
        softAssertions.assertThat(resultUser).as("Check mock created user").isEqualTo(mockUser);
        softAssertions.assertAll();
    }
}
