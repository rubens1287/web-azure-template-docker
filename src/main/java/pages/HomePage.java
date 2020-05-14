package pages;

import driver.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import report.Report;
import support.Verifications;

@Log4j2
public class HomePage extends DriverManager implements CommonTestingType {

    private By lblBemVindo = By.xpath("//div[contains(text(),'Bem vindo')]");

    @Override
    public boolean isPresent() {
        Report.takeScreenShot();
        return Verifications.verifyElementIsVisible(lblBemVindo);
    }


}
