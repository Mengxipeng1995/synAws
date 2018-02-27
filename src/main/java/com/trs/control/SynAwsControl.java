package com.trs.control;

import com.trs.entity.Photo;
import com.trs.entity.Video;
import com.trs.services.PhotoServices;
import com.trs.services.VideoServices;
import com.trs.until.Config;
import com.trs.until.synAwsUntil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class SynAwsControl {


    /*图片输出位置*/
    private static final String PHOTOPATH = Config.getKey("disk.photo");
    /*图片S3路径*/
    private static final String PHOTOS3PATH = Config.getKey("s3.photo");
    /*图片S3存储桶*/
    private static final String PHOTOBUSKNAME = Config.getKey("photo.buck");


    /*图片输出位置*/
    private static final String ORGINPHOTOPATH = Config.getKey("disk.orginPhoto");
    /*图片S3路径*/
    private static final String ORGINPHOTOS3PATH = Config.getKey("s3.orginPhoto");
    /*图片S3存储桶*/
    private static final String ORGINPHOTOBUSKNAME = Config.getKey("orginPhoto.buck");

    /*视频输出位置*/
    private static final String VIDEOPATH = Config.getKey("disk.video");
    /*视频S3路径*/
    private static final String VIDEOS3PATH = Config.getKey("s3.video");
    /*视频S3存储桶*/
    private static final String VIDEOBUCKNAME = Config.getKey("video.buck");


    private static final String SYNPHOTOTIME = Config.getKey("photo.time");

    @Autowired
    private PhotoServices photoServices;
    @Autowired
    private VideoServices videoServices;

    /*同步水印图片*/
    public void synPhoto() {

        int i = 0;
        List<Photo> photoPath = photoServices.selectPhotoPath();
        for (Photo p : photoPath) {
            synAwsUntil.awsDown(PHOTOS3PATH + p.getPath(), PHOTOPATH + "/" + p.getPath(), PHOTOBUSKNAME);
            synAwsUntil.awsDown(ORGINPHOTOS3PATH + p.getPath(), ORGINPHOTOPATH + "/" + p.getPath(), ORGINPHOTOBUSKNAME);
            System.out.println(p.getPath() + "========>" + i);
        }

        System.out.println("=======================>photo---over-----photo<========================");
    }


    public void synPhotos() {

        int i = 0;


        List<Photo> photoPath = photoServices.selectPhotoPaths();
        for (Photo p : photoPath) {
            System.out.println(PHOTOS3PATH + p.getPath() + "<======>" + PHOTOPATH + "/" + p.getPath() + "<========>" + PHOTOBUSKNAME);
            synAwsUntil.awsDown(PHOTOS3PATH + p.getPath(), PHOTOPATH + "/" + p.getPath(), PHOTOBUSKNAME);
            synAwsUntil.awsDown(ORGINPHOTOS3PATH + p.getPath(), ORGINPHOTOPATH + "/" + p.getPath(), ORGINPHOTOBUSKNAME);
            System.out.println(p.getPath() + "========>" + i++);
        }

        System.out.println("=======================>photo---over-----photo<========================");
    }

//    /*同步原图片*/
//    @Scheduled(cron="0 29 16 * * ? ")
//    public void synOrgainPhoto() {
//
//        Integer count = photoServices.selectPhotoCount();
//
//        for (int i = 0; i < count; i++) {
//            List<Photo> photoPath = photoServices.selectPhotoPath(i, 1);
//            for (Photo p : photoPath) {
//                synAwsUntil.awsDown(ORGINPHOTOS3PATH+p.getPath(),ORGINPHOTOPATH+"/"+p.getPath(),ORGINPHOTOBUSKNAME);
//                System.out.println(p.getPath());
//            }
//        }
//        System.out.println("=======================>Orginalphoto---over-----Orginalphoto<========================");
//    }


    /*同步视频*/
    public void synVideo() {
        int i = 0;
        List<Video> videoPath = videoServices.selectVideoPath();
        for (Video v : videoPath) {

            String path = v.getPath();

            String fileName = path.substring(path.lastIndexOf("=")+1);

            synAwsUntil.awsDown(VIDEOS3PATH + fileName, VIDEOPATH + "/" + fileName, VIDEOBUCKNAME);
            System.out.println(fileName + "========>" + i++);
        }

        System.out.println("=======================>video---over-----video<========================");

    }


    public void synVideos() {

        int i = 1;

        List<Video> videoPath = videoServices.selectVideoPaths();
        System.out.println(videoPath);
        for (Video v : videoPath) {
            String path = v.getPath();

            String fileName = path.substring(path.lastIndexOf("=")+1);

            synAwsUntil.awsDown(VIDEOS3PATH + fileName, VIDEOPATH + "/" + fileName, VIDEOBUCKNAME);
            System.out.println(fileName + "=========>" + i++);
        }
        System.out.println("=======================>video---over-----video<========================");

    }
}


