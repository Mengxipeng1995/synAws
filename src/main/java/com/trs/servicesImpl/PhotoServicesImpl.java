package com.trs.servicesImpl;

import com.trs.entity.Photo;
import com.trs.mapper.PhotoMapper;
import com.trs.services.PhotoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PhotoServicesImpl implements PhotoServices{

    @Autowired
    private PhotoMapper photoMapper;

    public List<Photo> selectPhotoPath() {
        return photoMapper.selectPhotoPath();
    }


    public List<Photo> selectPhotoPaths() {
        return photoMapper.selectPhotoPaths();
    }


}
