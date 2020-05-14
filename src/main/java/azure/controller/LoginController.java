package azure.controller;

import org.apache.commons.codec.binary.Base64;

public class LoginController {

    public static String getToken(String personalToken){
        String AuthStr = ":" + personalToken;
        Base64 base64 = new Base64();
        return new String(base64.encode(AuthStr.getBytes()));
    }
}
