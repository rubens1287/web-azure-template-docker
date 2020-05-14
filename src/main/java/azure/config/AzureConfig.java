package azure.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:azure.properties"})
public interface AzureConfig extends Config {

    @Key("host.azure")
    String hostAzure();

    @Key("organization")
    String organization();

    @Key("project")
    String project();

    @Key("personal.access.token")
    String personalToken();
}
