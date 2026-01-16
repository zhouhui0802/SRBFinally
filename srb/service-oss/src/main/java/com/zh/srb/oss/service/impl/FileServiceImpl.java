package com.zh.srb.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.zh.srb.oss.service.FileService;
import com.zh.srb.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;
@Service
public class FileServiceImpl implements FileService {

    /**
     * 文件上传至阿里云
     */
    @Override
    public String upload(InputStream inputStream, String module, String fileName) {

        OSS ossClient = new OSSClientBuilder().build(
                OssProperties.ENDPOINT,
                OssProperties.KEY_ID,
                OssProperties.KEY_SECRET
        );
        /*System.out.println(OssProperties.BUCKET_NAME);
        System.out.println(OssProperties.KEY_ID);
        System.out.println(OssProperties.KEY_SECRET);*/
        if(!ossClient.doesBucketExist(OssProperties.BUCKET_NAME)) {
            ossClient.createBucket(OssProperties.BUCKET_NAME);

            ossClient.setBucketAcl(OssProperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }

        String folder = new DateTime().toString("yyyy/MM/dd");

        fileName = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));


        String key = module+ '/' +folder+ '/'+fileName;

        ossClient.putObject(OssProperties.BUCKET_NAME,key,inputStream);

        ossClient.shutdown();

        return "https://"+OssProperties.BUCKET_NAME+"."+ OssProperties.ENDPOINT + "/" + key;
    }

    // https://zhouhui-srb.oss-cn-beijing.aliyuncs.com/zh/2026/01/16/df8409bc-2733-4214-886d-09336139caf4.jpg
    /**
     * 根据路径删除文件
     * @param url
     */

    @Override
    public void removeFile(String url) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(
                OssProperties.ENDPOINT,
                OssProperties.KEY_ID,
                OssProperties.KEY_SECRET);

        //文件名（服务器上的文件路径）
        String host = "https://" + OssProperties.BUCKET_NAME + "." + OssProperties.ENDPOINT + "/";
        String objectName = url.substring(host.length());

        // 删除文件。
        ossClient.deleteObject(OssProperties.BUCKET_NAME, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
