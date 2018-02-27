package com.trs.mapper;

import com.trs.entity.Photo;
import com.trs.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface VideoMapper {
    /**
     * 增量
     * @param start
     * @param end
     * @return
     */
    List<Video> selectVideoPath();

    /**
     * 增量
     * 查询图片表总数
     */
    Integer selectVideoCounts();

    /**
     * 全量
     * @param start
     * @param end
     * @return
     */
    List<Video> selectVideoPaths();

    /**
     * 全量
     * 查询图片表总数
     */
    Integer selectVideoCount();
}
