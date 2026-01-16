package com.zh.srb.oss.controller.api;

import com.zh.srb.common.exception.BusinessException;
import com.zh.srb.common.result.R;
import com.zh.srb.common.result.ResponseEnum;
import com.zh.srb.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Api(tags = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/api/oss/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R upload(
            @ApiParam(value="文件",required = true)
            @RequestParam("file") MultipartFile file,

            @ApiParam(value = "模块", required = true)
            @RequestParam("module") String module
    ){
        try{
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String uploadUrl = fileService.upload(inputStream, module, originalFilename);

            return R.ok().message("文件上传成功").data("url",uploadUrl);
        }catch (Exception e){
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }
    }

    @ApiOperation("文件删除")
    @DeleteMapping("/remove")
    public R remove(@ApiParam(value = "要删除的文件路径", required = true)
                        @RequestParam("url") String url){
        fileService.removeFile(url);
        return R.ok().message("删除成功");
    }
}
