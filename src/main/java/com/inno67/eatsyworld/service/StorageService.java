package com.inno67.eatsyworld.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class StorageService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    private final AmazonS3 s3;

    public StorageService(AmazonS3 s3){
        this.s3 = s3;
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)){
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error ("Error converting multipartFile to file", e);
            throw new IllegalArgumentException("파일 변환에 실패했습니다.");
        }
        return convertedFile;
    }

    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String filename = System.currentTimeMillis() + "-" +file.getOriginalFilename();
        s3.putObject(new PutObjectRequest(bucketName,filename, fileObj));
        fileObj.delete();
        return s3.getUrl(bucketName,filename).toString();
    }
}
