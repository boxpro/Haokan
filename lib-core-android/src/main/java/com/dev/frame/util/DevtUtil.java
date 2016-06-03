package  com.dev.frame.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by Devt on 16/6/2.
 * Email:devt@foxmail.com
 */
public class DevtUtil {

    public static String fixEncoding(String response) {
        try {
            byte[] u = response.toString().getBytes(
                    "ISO-8859-1");
            response = new String(u, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

}
