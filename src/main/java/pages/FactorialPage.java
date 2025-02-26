package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FactorialPage {

    public SelenideElement field = $(By.id("number"));
    public SelenideElement button = $(By.id("getFactorial"));
    public SelenideElement result = $(By.id("resultDiv"));
}
