package com.trs.springtest;


import com.trs.entity.Photo;
import com.trs.services.PhotoServices;
import com.trs.until.Config;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringTest {

    @Autowired
    private PhotoServices photoServices;

    @Scheduled(cron="0/10 * *  * * ? ")
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        PhotoServices photos= (PhotoServices) ctx.getBean("photoServicesImpl");

        /*查询图片总数*/



    }
    @Test
    public  void test2(){
        String ss = Config.getKey("disk.video");
        System.out.println(ss);
    }
}
