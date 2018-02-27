package com.trs.services;

import com.trs.entity.Photo;
import com.trs.entity.Video;

import java.util.List;

public interface VideoServices {
   List<Video> selectVideoPath();
   List<Video> selectVideoPaths();
}
