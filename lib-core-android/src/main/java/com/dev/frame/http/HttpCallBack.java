package  com.dev.frame.http;
/**
 * Created by Devt on 16/6/1.
 * Email:devt@foxmail.com
 */
public abstract class HttpCallBack {
     public void onSuccess(String t) {}

     public void onSuccess(byte[] t) {
        if (t != null) {
            onSuccess(new String(t));
        }
     }

    public void onFailure(int errorNo, String strMsg) {}


}
