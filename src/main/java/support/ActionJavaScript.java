package support;

import core.driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;


public class ActionJavaScript extends DriverManager {


    public static String getValueById(String id){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (String) js.executeScript("return document.getElementById('"+id+"').value");
    }
}
