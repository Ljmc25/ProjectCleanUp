package com.itn2024.projectb.mapper.advanced;

import com.itn2024.projectb.vo.advanced.FileDb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileDbMapper {
    // 이미지 삽입
    public int insertFile(FileDb fileDb);

    // prid 기준으로 이미지 하나 조회
    public List<String> selectProductImage(String prid);

//    prid 기준으로 이미지 목록 조회
    public List<String> selectProductImages(String prid);

    public int updateFile(FileDb fileDb);

    public int deleteFile(String prid);
}
