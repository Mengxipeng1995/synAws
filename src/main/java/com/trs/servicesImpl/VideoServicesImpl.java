package com.trs.servicesImpl;

import com.trs.entity.Video;
import com.trs.mapper.VideoMapper;
import com.trs.services.VideoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoServicesImpl implements VideoServices{

    @Autowired
    private VideoMapper videoMapper;


    public List<Video> selectVideoPath() {
        return videoMapper.selectVideoPath();
    }

    public List<Video> selectVideoPaths() {
        return videoMapper.selectVideoPaths();
    }
}
