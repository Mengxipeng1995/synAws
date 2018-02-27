package com.trs.until;


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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class synAwsUntil {


    public static void  awsDown(String sourceFilePath,String outPath,String bucketName) {
        AWSCredentials credentials = null;
        credentials=new BasicAWSCredentials("AKIAPCE2LRYAJFLZQ4QQ","0f7C0oOzUGT1XPoa5/YoTE4HX7zzxDA45zhK1YQK\n");
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        Region china = Region.getRegion(Regions.CN_NORTH_1);
       // Socket socket = new Socket(host,port);
        AmazonS3 connection = new AmazonS3Client(credentials,clientConfig);
        connection.setRegion(china);
        //"cmanuf"
        //"Photo/bigPic/53bd1d81b6034c9c84358040e395b6c9.jpg"
        GeneratePresignedUrlRequest httpRequest=new GeneratePresignedUrlRequest(bucketName, sourceFilePath.trim());
        String url=connection.generatePresignedUrl(httpRequest).toString();//临时链接
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(6000)
                    .setConnectTimeout(6000)
                    .setConnectionRequestTimeout(6000)
                    .setStaleConnectionCheckEnabled(true)
                    .build();
            httpget.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            InputStream input = entity.getContent();
            //  IOUtils.toByteArray(input);
            FileOutputStream output = new FileOutputStream(new File(outPath));
            byte[] buffer=new byte[10240];
            int ch = 0;
            while ((ch = input.read(buffer)) != -1) {
                output.write(buffer,0,ch);
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
