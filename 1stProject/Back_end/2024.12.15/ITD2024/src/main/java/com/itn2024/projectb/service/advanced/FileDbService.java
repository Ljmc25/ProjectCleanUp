package com.itn2024.projectb.service.advanced;

import com.itn2024.projectb.mapper.advanced.FileDbMapper;
import com.itn2024.projectb.vo.advanced.FileDb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileDbService {

    private final FileDbMapper fileDbMapper;


    public Optional<String> selectProductImage(String prid) {
        // prid에 해당하는 파일 목록을 가져옴 (List<String>으로 수정)
        List<String> imageUrls = fileDbMapper.selectProductImage(prid);

        // 이미지가 하나라도 있으면 첫 번째 이미지 URL을 반환
        if (!imageUrls.isEmpty()) {
            return Optional.of(imageUrls.get(0));  // 첫 번째 이미지 URL 반환
        }

        // 이미지가 없다면 Optional.empty() 반환
        return Optional.empty();
    }

    public List<String> selectProductImages(String prid) {
        return fileDbMapper.selectProductImages(prid); // Mapper의 쿼리 실행
    }



    public void deleteFiles(String prid) {
        fileDbMapper.deleteFile(prid);
    }

    public void insertFile(FileDb fileDb, String fname) {
        // 1) UUID 생성
        String uuid = UUID.randomUUID().toString();

        // 2) 프로젝트 내 상대 경로 설정
        String projectDir = System.getProperty("user.dir"); // 프로젝트 경로 가져오기
        String imageDirPath = projectDir + "/src/main/resources/static/images"; // 상대 경로로 이미지 폴더 경로 설정

        // 3) 디렉터리 존재 여부 확인, 없으면 생성
        File directory = new File(imageDirPath);
        if (!directory.exists()) {
            directory.mkdirs();  // 디렉터리 생성
        }

        // 4) 이미지 파일 저장할 파일 객체 생성
        File destination = new File(directory, fname);

        // 5) 파일 저장
        try {
            Files.write(destination.toPath(), fileDb.getFileData()); // 이미지 파일 저장
        } catch (IOException e) {
            e.printStackTrace();  // 예외 처리
        }

        // 6) 이미지 URL 생성
        String url = generateDownloadUrl(fname);

        // 7) FileDb 객체에 UUID와 URL 저장
        fileDb.setUuid(uuid);
        fileDb.setFileUrl(url);

        // 8) DB에 저장
        fileDbMapper.insertFile(fileDb);
    }




    public String generateDownloadUrl(String uuid) {
        // 이미지 파일은 static/images 폴더에 저장되고, 해당 URL을 생성
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()                      // 기본 URL
                .path("/images/")                              // 이미지 경로
                .path(uuid)                                    // UUID 추가 (예: uuid.jpg)
                .toUriString();                                // 최종 URL 반환
    }


}
