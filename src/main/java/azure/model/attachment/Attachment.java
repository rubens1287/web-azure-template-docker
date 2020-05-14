package azure.model.attachment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class Attachment {

    public String stream;
    public String fileName;
    public String comment;
    public String attachmentType;
}
