package com.gachon.UntactPhotoBooth.Service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름

    // 이미지 업로드 code 로 구분 posts, user
    public ArrayList<String> uploadImageToS3(MultipartFile[] files){
        ObjectMetadata meta = new ObjectMetadata();
        ArrayList<String> resultUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            meta.setContentType(MediaType.IMAGE_PNG_VALUE);
            meta.setContentLength(file.getSize());
            String randomFileName = "static/" +UUID.randomUUID() + file.getOriginalFilename();
            try {
                amazonS3Client.putObject(
                        new PutObjectRequest(bucket, randomFileName, file.getInputStream(), meta)
                                .withCannedAcl(CannedAccessControlList.PublicRead));
                resultUrls.add(amazonS3Client.getUrl(bucket, randomFileName).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultUrls;
    }
}
