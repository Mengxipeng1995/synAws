package com.trs.synawsUntil;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class synAwsUntil {


    /*
    *
    * HttpGet httpGet = new HttpGet("http://stackoverflow.com/");
           RequestConfig requestConfig = RequestConfig.custom()
        .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
        .setSocketTimeout(5000).build();
httpGet.setConfig(requestConfig);
CloseableHttpResponse response = httpclient.execute(httpGet);
System.out.println("得到的结果:" + response.getStatusLine());//得到请求结果
HttpEntity entity = response.getEntity();//得到请求回来的数据</code>
    * */

    @Test
    public void awsDown() throws InterruptedException {
        AWSCredentials credentials = null;
        credentials = new BasicAWSCredentials("AKIAPCE2LRYAJFLZQ4QQ", "0f7C0oOzUGT1XPoa5/YoTE4HX7zzxDA45zhK1YQK\n");
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        Region china = Region.getRegion(Regions.CN_NORTH_1);

        AmazonS3 connection = new AmazonS3Client(credentials, clientConfig);
        connection.setRegion(china);
        //connection.setEndpoint("http://192.168.1.87");

        GeneratePresignedUrlRequest httpRequest = new GeneratePresignedUrlRequest("cmanuf", "photo/sysml/000458c8be814129b34d111d3005ba33.jpg");
        String url = connection.generatePresignedUrl(httpRequest).toString();//临时链接



        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
           // Thread.sleep(60);
            HttpGet httpget = new HttpGet(url);
            httpget.setHeader("Connection","keep-alive");
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(50000)
                    .setConnectTimeout(50000)
                    .setConnectionRequestTimeout(50000)
                    .setStaleConnectionCheckEnabled(true)
                    .build();
            httpget.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            InputStream input = entity.getContent();
            //  IOUtils.toByteArray(input);
            FileOutputStream output = new FileOutputStream(new File("d:\\8e351245a7354ddd9eef44b65a42ce59.jpg"));
            byte[] buffer = new byte[1024000];
            int ch = 0;
            while ((ch = input.read(buffer)) != -1) {
                output.write(buffer, 0, ch);
            }
            input.close();
            output.flush();
            output.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
