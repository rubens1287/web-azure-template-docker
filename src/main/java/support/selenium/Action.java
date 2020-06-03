package support.selenium;

import core.driver.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author Rubens Lobo
 * @since 13/05/2018
 */
@Log4j2
public class Action extends DriverManager {


    /**
     * Returns an existing element from the screen
     *
     * @param by      Type of "By"
     * @return Returns an existing element from the screen
     * @author Rubens Lobo
     */
    public static WebElement getExistingElement(By by) {
        log.info(String.format("Retornando um elemento web via locator %s ", by.toString()));
        return new WebDriverWait(getDriver(),configuration.timeout())
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }


    /**
     * Returns attachment visible element from the screen
     *
     * @param by      Type of "By"
     * @return Returns an visible element from the screen
     * @author Rubens Lobo
     */
    public static WebElement getVisibleElement(By by) {
        log.info(String.format("Retornando um elemento web via locator %s ", by.toString()));
        return new WebDriverWait(getDriver(),configuration.timeout())
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Returns attachment clickable element from the screen
     *
     * @param by      Type of "By"
     * @return Returns attachment clickable element from the screen
     * @author Rubens Lobo
     */
    public static WebElement getClickableElement(By by) {
        log.info(String.format("Retornando um elemento web via locator %s ", by.toString()));
        return new WebDriverWait(getDriver(),configuration.timeout())
                .until(ExpectedConditions.elementToBeClickable(by));
    }


    /**
     * Clears the Text Field of attachment WebElement
     *
     * @param by      Type of "By"
     * @author Rubens Lobo
     */
    public static void clearField(By by) {
        WebElement element = getClickableElement(by);
        log.info(String.format("Limpando o campo texto do elemento web via locator %s ", by.toString()));
        element.clear();
    }

    /**
     * Enters text in attachment WebElement Text Field
     *
     * @param by      Type of "By"
     * @param text    set attachment text to web element
     * @author Rubens Lobo
     */
    public static void setText(By by, Object text) {
        WebElement element = getClickableElement(by);
        log.info(String.format("Inserindo texto no elemento web via locator %s ", by.toString()));
        element.click();
        element.clear();
        element.sendKeys((CharSequence) text);
    }

    /**
     * Gets text from attachment WebElement Text Field
     *
     * @param by      Type of "By"
     * @author Rubens Lobo
     */
    public static String getText(By by) {
        String textExtracted = null;
        WebElement element = Action.getVisibleElement(by);
        log.info(String.format("Pegando o valor de um elemento web via locator %s ", by.toString()));
        if (element != null) {
            textExtracted = element.getText().trim();
        } else {
            log.error("Failed to retrieve the value from the web element.");
        }
        return textExtracted;
    }


    /**
     * Gets text by attribute in attachment WebElement Text Field
     *
     * @param by        Type of "By"
     * @param attribute get text value thought of the tag name
     * @author Rubens Lobo
     */
    public static String getTextAttribute(By by, String attribute) {
        String textExtracted = null;
        WebElement element = Action.getVisibleElement(by);
        log.info(String.format("Pegando o texto de um atributo do elemento via locator %s ", by.toString()));
        if (element != null) {
            textExtracted = element.getAttribute(attribute).trim();
        } else {
            log.error("Failed to retrieve the value from the web element.");
        }
        return textExtracted;
    }


    /**
     * Clicks in the web element
     *
     * @param by      Type of "By"
     * @author Rubens Lobo
     */
    public static void clickOnElement(By by) {
        //if the object is not enabled or visible, this line finalizes the test, but if the object exists the method returns attachment AppWeb Element object
        WebElement element = getClickableElement(by);
        log.info(String.format("Clicando no elemento web via locator %s ", by.toString()));
        //Clicks at the element requested
        element.click();
    }


    /**
     * Selects Combo Box Option by: Value
     *
     * @author Rubens Lobo
     * @param by Type of "By"
     */
    public static void selectComboOptionByValue(By by, String value) {
        WebElement element = Action.getClickableElement(by);
        Select selectCombo = new Select(element);
        selectCombo.selectByVisibleText(value);
    }

}
