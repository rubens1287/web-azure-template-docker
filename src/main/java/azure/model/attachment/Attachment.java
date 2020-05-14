package azure.model.attachment;

import dates.DatePicker;
import driver.DriverManager;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Getter @Setter
public class Attachment {

    public String stream;
    public String fileName;
    public String comment;
    public String attachmentType;

    public Attachment() {
        this.stream = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        this.fileName = String.format("Screeshot_%s_%s.png",
                DatePicker.getCurrentDate().replace("/",""),
                DatePicker.getCurrentTime().replace(":",""));
        this.comment = "Screenshot  anexada via teste automatizado";
        this.attachmentType = "GeneralAttachment";
    }

    public Attachment(String comment) {
        this.stream = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        this.fileName = String.format("Screeshot_%s_%s.png",
                DatePicker.getCurrentDate().replace("/",""),
                DatePicker.getCurrentTime().replace(":",""));
        this.comment = comment;
        this.attachmentType = "GeneralAttachment";
    }
}
