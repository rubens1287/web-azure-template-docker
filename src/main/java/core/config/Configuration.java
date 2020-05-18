package core.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:conf/${env}.properties"})
public interface Configuration extends Config {

    @Key("target")
    String target();

    @Key("url.base")
    String url();

    @Key("timeout")
    int timeout();

    @Key("grid.url")
    String gridUrl();

    @Key("grid.port")
    String gridPort();

    @Key("faker.locale")
    String faker();

    @Key("string.con.db")
    String hostDb();

    @Key("user.db")
    String userDb();

    @Key("pass.db")
    String passDb();
}
