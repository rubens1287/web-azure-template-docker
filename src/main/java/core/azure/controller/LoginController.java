package core.azure.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;

@Log4j2 @Getter @Setter
public class LoginController {

    public String token;

    public void generateToken(){
        String token = System.getProperty("token");
        String AuthStr;
        Base64 base64 = new Base64();
        if(!token.isEmpty()){
             AuthStr = ":" + token;
             this.token = new String(base64.encode(AuthStr.getBytes()));
        }else {
            this.token = "";
        }
    }
}
