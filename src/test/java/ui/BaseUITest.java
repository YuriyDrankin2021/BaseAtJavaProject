package ui;

import helpers.Browser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseUITest {

    @BeforeAll
    void setUp(){
        Browser.setDriver();
    }

    @AfterAll
    void tearDown(){
        closeWebDriver();
    }
}
