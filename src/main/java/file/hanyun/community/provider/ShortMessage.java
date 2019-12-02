package file.hanyun.community.provider;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * author:hanyun.li
 * createTime:2019/12/2
 * return:void
 */
public class ShortMessage {

    //请求的目标接口
    private String uri = "http://utf8.api.smschinese.cn/";

    /**
     * param:Uid-注册用户名
     * param:Key-注册用户的key(授权的密钥)
     * param:smsMob-需要发送的目标手机号，可填写多个 eg:(152...0978,137...6236)
     * param:smsText:短信发送的内容 eg:验证码(8888) 普通短信70个字/条，超过70个字算长短信，按64个字/条计费，长短信最多支持300个字不拆分
     */
    public void sendHttpPost() {
        try {
            CloseableHttpClient client = null;
            try {
                // 创建一个提交数据的容器
                List<BasicNameValuePair> parames = new ArrayList<>();
                parames.add(new BasicNameValuePair("Uid", "hy.li"));
                parames.add(new BasicNameValuePair("Key", "d41d8cd98f00b204e980"));
                parames.add(new BasicNameValuePair("smsMob", "15271730978"));
                parames.add(new BasicNameValuePair("smsText", "8888"));

                HttpPost httpPost = new HttpPost(uri);
                httpPost.setEntity(new UrlEncodedFormEntity(parames, "UTF-8"));

                httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
                client = HttpClients.createDefault();
            } finally {
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
