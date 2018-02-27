package com.trs.services;

import com.trs.entity.Photo;

import java.util.List;

public interface PhotoServices {
   List<Photo> selectPhotoPath();
   List<Photo> selectPhotoPaths();
}
