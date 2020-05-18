package pages;


import core.azure.model.attachment.Attachment;
import core.driver.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import support.Verifications;

@Log4j2
public class HomePage extends DriverManager implements CommonTestingType {

    private By lblBemVindo = By.xpath("//div[contains(text(),'Bem vindo')]");

    @Override
    public boolean isPresent() {
        attachments.add(new Attachment());
        return Verifications.verifyElementIsVisible(lblBemVindo);
    }


}
