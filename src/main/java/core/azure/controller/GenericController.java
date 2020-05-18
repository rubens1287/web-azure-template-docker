package core.azure.controller;


import core.azure.config.AzureConfig;
import org.aeonbits.owner.ConfigCache;

public abstract class GenericController {

    protected AzureConfig azureConfig = ConfigCache.getOrCreate(AzureConfig.class);

    public String getBaseUrl(){
        return String.format("https://%s/%s/%s/",azureConfig.hostAzure(), azureConfig.organization(), azureConfig.project());
    }

}
