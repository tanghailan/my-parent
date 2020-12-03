package com.kanavi.system.controller;

import com.kanavi.response.api.ResponseBean;
import com.kanavi.system.dto.MinioUploadDto;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b><code>MinioController</code></b>
 * <p>
 * Description
 * <p/>
 * <b>Creation Time:</b>2020-08-17-15:46
 *
 * @author tanghailan
 * @since LearnDemo 0.0.1
 */
@RestController
@RequestMapping("/minio")
@Slf4j
@Api(value = "MinioController", tags = "上传文件控制器")
public class MinioController {

    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    @PostMapping(value = "/upload")
    @ApiOperation(value = "上传文件", notes = "file不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件"),
    })
    public ResponseBean upload(@RequestParam("file") MultipartFile file) {
        try {
            //创建一个MinIO的Java客户端
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            boolean isExist = minioClient.bucketExists(BUCKET_NAME);
            if (isExist) {
                log.info("存储桶已经存在！");
            } else {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(BUCKET_NAME);
                minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_ONLY);
            }
            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置存储对象名称
            String objectName = sdf.format(new Date()) + "/" + filename;
            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
            log.info("文件上传成功!");
            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(filename);
            minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
            return ResponseBean.success(minioUploadDto);
        } catch (Exception e) {
            log.info("上传发生错误: {}！", e.getMessage());
        }
        return ResponseBean.error("上传发生错误!");
    }

    @ApiOperation(value = "删除文件", notes = "file不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectName", value = "文件名称"),
    })
    @PostMapping(value = "/delete")
    public ResponseBean delete(@RequestParam("objectName") String objectName) {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            minioClient.removeObject(BUCKET_NAME, objectName);
            return ResponseBean.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseBean.error("删除出错!");
    }
}
