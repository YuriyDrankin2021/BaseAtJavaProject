package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.FactorialPage;

import java.time.Duration;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FactorialPageTest extends BaseUITest {
    Logger logger = LoggerFactory.getLogger(FactorialPageTest.class);

    @BeforeAll
    public void setUp() {
        super.setUp();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @DisplayName("Check get factorial of 5")
    @Tag("UI")
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

    @ParameterizedTest(name = "Check input \"{0}\" , result must be \"{1}\"")
    @MethodSource("data")
    @Tag("UI")
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
//                arguments("-1", "Incorrect value"),
                arguments("1.5", "Please enter an integer"),
                arguments("bububu", "Please enter an integer")
        );
    }
}
