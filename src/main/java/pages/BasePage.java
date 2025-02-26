package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.annotations.ElementName;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;

public class BasePage {
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public SelenideElement getSelenideElement(String elementName) {
        return (SelenideElement) getElement(elementName);
    }

    public ElementsCollection getElements(String elementsName) {
        return (ElementsCollection) getElement(elementsName);
    }

    private Object getElement(String elementName) throws IllegalArgumentException {
        try {
            for (Field field : this.getClass().getDeclaredFields()) {
                if (isElementNameAnnotationMatches(field, elementName)) {
                    return field.get(this);
                }
            }
        } catch (Exception e) {
            logger.debug(e.getLocalizedMessage());
        }
        throw new IllegalArgumentException("В классе, описывающем страницу, отсутствует элемент с аннотацией '" + elementName + "'");
    }

    public Method getMethod(String elementName) {
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (isElementNameAnnotationMatches(method, elementName))
                return method;
        }
        throw new IllegalArgumentException("В классе, описывающем страницу, отсутствует метод с аннотацией '" + elementName + "'");
    }

    public void clickOnElement(String elementName) {
        getSelenideElement(elementName).click();
    }

    public void fillValueInField(String elementName, String value) {
        getSelenideElement(elementName).setValue(value);
    }

    public void selectOptionFromDropdownByText(String elementName, String text) {
        getSelenideElement(elementName).selectOption(text);
    }

    public void verifyElementExist(String elementName) {
        getSelenideElement(elementName).shouldBe(visible);
    }

    public void verifyElementExistWithWait(String elementName, int duration) {
        getSelenideElement(elementName).shouldBe(visible, Duration.ofSeconds(duration));
    }

    public void verifyElementsCount(String elementName, int count) {
        getElements(elementName).shouldBe(size(count));
    }

    public void verifyElementNotExist(String elementName) {
        getSelenideElement(elementName).shouldNotBe(visible);
    }

    public void verifyElementsCollectionExist(String elementsName) {
        getElements(elementsName).shouldHave(sizeGreaterThan(0))
                .asDynamicIterable().forEach(element -> element.shouldBe(visible));
    }

    public void verifyElementsCollectionExistWithWait(String elementsName, int duration) {
        getElements(elementsName).asDynamicIterable().forEach(element -> element.shouldBe(visible, Duration.ofSeconds(duration)));
        getElements(elementsName).shouldHave(sizeGreaterThan(0))
                .asDynamicIterable().forEach(element -> element.shouldBe(visible));
    }

    public void verifyElementsCollectionNotExist(String elementsName) {
        getElements(elementsName).asDynamicIterable().forEach(element -> element.shouldNotBe(visible));
    }

    public void verifyElementText(String elementName, String expectedText) {
        getSelenideElement(elementName).shouldHave(exactTextCaseSensitive(expectedText));
    }

    public void verifyElementIncludeText(String elementName, String expectedText) {
        getSelenideElement(elementName).shouldHave(textCaseSensitive(expectedText));
    }

    public void verifyElementValue(String elementName, String expectedValue) {
        getSelenideElement(elementName).shouldHave(exactValue(expectedValue));
    }

    public void verifyElementIncludeValue(String elementName, String expectedValue) {
        getSelenideElement(elementName).shouldHave(value(expectedValue));
    }

    public void clearInputElement(String elementName){
        getSelenideElement(elementName).sendKeys(Keys.CONTROL + "A");
        getSelenideElement(elementName).sendKeys(Keys.BACK_SPACE);
    }


    private boolean isElementNameAnnotationMatches(Object annotationHolder, String elementName) {
        try {
            if (annotationHolder instanceof Field field)
                return (field).getAnnotation(ElementName.class).value().equals(elementName);
            else
                return ((Method) annotationHolder).getAnnotation(ElementName.class).value().equals(elementName);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
