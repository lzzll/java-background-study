package com.example.lzzll.javastudy.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Component
public class AliyunOSSUtil {
    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.url}")
    private String url;

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.secretAccessKey}")
    private String secretAccessKey;

    public OSS initOSSClient(){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, secretAccessKey);
        // 判断容器是否存在,不存在就创建
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        return ossClient;
    }


    /**
     * 文件流上传
     * @param inputStream 文件流
     * @param fileName oss文件路径
     * @param contentType 文件contentType
     * @return oss request url
     */
    public String uploadInputStream(InputStream inputStream, String fileName, String contentType){
        OSS ossClient = initOSSClient();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        PutObjectResult result = ossClient.putObject(bucketName, fileName, inputStream, metadata);
        ossClient.shutdown();
        ossClient = null;
        return url + "/" + fileName;
    }

    public String generatePresignedUrl(String fileName){
        OSS ossClient = initOSSClient();
        Date date = new Date(System.currentTimeMillis()+3600000);
        URL url = ossClient.generatePresignedUrl(bucketName, fileName, date);
        return url.toString();
    }
    /**
     * 删除OSS文件
     * @param ossKey
     */
    public void deleteFile(String ossKey){
        OSS ossClient = initOSSClient();
        ossClient.deleteObject(bucketName,ossKey);
        ossClient.shutdown();
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public String getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if(".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }else if(".svg".equalsIgnoreCase(fileExtension)) {
            return "image/svg+xml";
        }else if(".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }else if(".jpg".equalsIgnoreCase(fileExtension)) {
            return "image/jpg";
        }else if(".png".equalsIgnoreCase(fileExtension)) {
            return "image/png";
        }else if(".jpeg".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }else if(".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }else if(".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }else if(".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }else if(".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }else if(".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }else if(".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }else if(".mp3".equalsIgnoreCase(fileExtension)) {
            return "audio/mp3";
        }else if(".json".equalsIgnoreCase(fileExtension)) {
            return "application/json";
        }else {
            return "image/jpeg";
        }
    }
}
