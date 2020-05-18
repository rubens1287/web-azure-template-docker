package core.azure.controller;

import cucumber.api.Scenario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CucumberController {

    private  String planId;
    private  String suiteId;
    private  String testId;

    public CucumberController(Scenario scenario) {
        List<String> tags = (List<String>) scenario.getSourceTagNames();
        for (int i = 0; i < tags.size(); i++) {
            String value = tags.get(i).toUpperCase();
            if(value.contains("SUITEID") ){
                this.suiteId = tags.get(i).toUpperCase().replace("@SUITEID=","");
            }else if(value.contains("TESTID")){
                this.testId = tags.get(i).toUpperCase().replace("@TESTID=","");
            }else if(value.contains("PLANID")){
                this.planId = tags.get(i).toUpperCase().replace("@PLANID=","");
            }
        }
    }

    public static int getStatus(Scenario scenario){
        switch (scenario.getStatus()) {
            case PASSED:
                return 2;
            case FAILED:
                return 3;
            case PENDING:
                return 4;
            case SKIPPED:
                return 4;
            case UNDEFINED:
                return 1;
        }
        return 0;
    }
}
