package com.trs.mapper;

import com.trs.entity.Photo;
import org.apache.ibatis.annotations.Param;

import java.util.List;



public interface PhotoMapper {
    /**
     *增量
     * @param start
     * @param end
     * @return
     */
    List<Photo> selectPhotoPath();


    /**
     * 全量
     * @param start
     * @param end
     * @return
     */
    List<Photo> selectPhotoPaths();


}
