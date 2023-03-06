// This file is auto-generated, don't edit it. Thanks.
package com.yanmou.mybatisplusstudy.utils;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.core.http.HttpClient;
import com.aliyun.core.http.HttpMethod;
import com.aliyun.core.http.ProxyOptions;
import com.aliyun.httpcomponent.httpclient.ApacheAsyncHttpClientBuilder;
import com.aliyun.sdk.service.dysmsapi20170525.models.*;
import com.aliyun.sdk.service.dysmsapi20170525.*;
import com.google.gson.Gson;
import darabonba.core.RequestConfiguration;
import darabonba.core.client.ClientOverrideConfiguration;
import darabonba.core.utils.CommonUtil;
import darabonba.core.TeaPair;
import org.junit.jupiter.api.Test;

//import javax.net.ssl.KeyManager;
//import javax.net.ssl.X509TrustManager;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SendSms {
    public static void sendMsg(String code,String toPhone) throws ExecutionException, InterruptedException {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId("LTAI5t8rBtipaVY6WCZ2i2Pj")
                .accessKeySecret("V0l0k7aGPBg4IaqdVBYDXGbHopymIi")
                .build());
        AsyncClient client = AsyncClient.builder()
                .region("cn-qingdao")

                .credentialsProvider(provider)

                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName("瑞吉外卖")
                .templateCode("SMS_272410326")
                .templateParam("{\"code\":\""+code+"\"}")
                .phoneNumbers(toPhone)
                .build();
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        SendSmsResponse resp = response.get();
        System.out.println(new Gson().toJson(resp));
        client.close();
    }

  @Test
    public void test(){
        String code = "8888";
      System.out.println("{\"code\":\""+code+"\"}");
  }

}
