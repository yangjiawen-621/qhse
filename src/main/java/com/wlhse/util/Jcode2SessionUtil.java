package com.wlhse.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Jcode2SessionUtil {


    public static String jscode2session(String appid, String secret, String code, String grantType) throws IOException {
        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/sns/jscode2session");
        List<org.apache.http.NameValuePair> params=new ArrayList<NameValuePair>();
        //设置请求参数
        params.add(new BasicNameValuePair("appid",appid));
        params.add(new BasicNameValuePair("secret",secret));
        params.add(new BasicNameValuePair("js_code",code));
        params.add(new BasicNameValuePair("grant_type",grantType));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        return EntityUtils.toString(new DefaultHttpClient().execute(httpPost).getEntity());
    }
}
