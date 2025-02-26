package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.FactorialPage;

import java.time.Duration;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FactorialPageTest {
    Logger logger = LoggerFactory.getLogger(FactorialPageTest.class);

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    public static void tearDown() {
        closeWebDriver();
    }

    @Test
    @Description("Check get factorial if 5")
    public void checkFactorialPositiveTest() {
        logger.debug("Start test: Check get factorial of 5");
        open("https://qainterview.pythonanywhere.com/");
        FactorialPage page = new FactorialPage();
        int input = 5;
        page.field.sendKeys(String.valueOf(input));
        page.button.click();
        String expectedResult = String.format("The factorial of %d is: %d", input, 120);
        page.result.shouldHave(Condition.text(expectedResult));
    }

    @ParameterizedTest
    @MethodSource("data")
    @Description("Check input {0} , result must be {1}")
    public void checkFactorialNegativeTest(String input, String expectedResult) {
        logger.debug("Start test: Check get factorial of {}", input);
        open("https://qainterview.pythonanywhere.com/");
        FactorialPage page = new FactorialPage();
        page.field.sendKeys(input);
        page.button.click();
        page.result.shouldHave(Condition.text(expectedResult), Duration.ofSeconds(5));
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments("0", "The factorial of 0 is: 1"),
                arguments("-1", "Incorrect value"),
                arguments("1.5", "Incorrect value"),
                arguments("bububub", "Incorrect value")
        );
    }
}
