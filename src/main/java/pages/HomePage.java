package pages;


import core.azure.model.attachment.Attachment;
import core.driver.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import support.selenium.Verifications;

@Log4j2
public class HomePage extends DriverManager implements CommonTestingType {

    private By lblBemVindo = By.xpath("//div[contains(text(),'Bem vindo')]");

    @Override
    public boolean isPresent() {
        attachments.add(new Attachment("png",null));
        return Verifications.verifyElementIsVisible(lblBemVindo);
    }
}
