package com.ruoyi.web.controller.common;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.onePicture.domain.XcxFile;
import com.ruoyi.onePicture.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.config.ServerConfig;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/common")
@Api(tags = "公共接口")
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IFileService fileService;

    private static final String FILE_DELIMETER = ",";

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {

            // ========== 新增安全校验开始 ==========
            // 校验文件名不能为空
            if (StringUtils.isEmpty(fileName))
            {
                throw new Exception("文件名不能为空");
            }

            // 校验文件名合法性（防止路径遍历）
            if (fileName.contains("../") || fileName.contains("..\\"))
            {
                throw new Exception("文件名包含非法字符");
            }
            // 2. 黑名单校验
            if (isBlacklisted(fileName))
            {
                throw new Exception("禁止下载该类型文件");
            }

            // 2. 规范化文件名，防止路径遍历
            String normalizedFileName = FilenameUtils.normalize(fileName);
            String ALLOWED_DOWNLOAD_DIR = RuoYiConfig.getDownloadPath();
            // 4. 构建完整文件路径并校验
            Path filePath1 = Paths.get(ALLOWED_DOWNLOAD_DIR, normalizedFileName).normalize();

            // 5. 验证路径是否在允许的目录内
            if (!filePath1.startsWith(ALLOWED_DOWNLOAD_DIR)) {
                throw new SecurityException("非法文件访问: 尝试访问非授权目录");
            }

            // ========== 新增安全校验结束 ==========


            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }
    /**
     * 黑名单检查
     */
    private boolean isBlacklisted(String fileName)
    {
        // 文件黑名单 - 禁止下载的文件扩展名
        Set<String> FILE_BLACKLIST_EXTENSIONS = new HashSet<>(Arrays.asList(
                "jsp", "jspx", "exe", "sh", "bat", "cmd", "php", "py", "pl", "asp", "aspx",
                "dll", "so", "jar", "war", "class", "properties", "yml", "yaml", "xml",
                "conf", "cfg", "ini", "log", "sql", "db", "mdb", "bak", "tmp"
        ));

        // 路径黑名单 - 禁止访问的路径关键词
        Set<String> PATH_BLACKLIST_KEYWORDS = new HashSet<>(Arrays.asList(
                "WEB-INF", "META-INF", "/etc/", "/bin/", "/usr/", "/root/", "/boot/",
                "windows/", "system32/", "passwd", "shadow", "hosts", "env", "profile"
        ));


        // 检查文件扩展名
        String ext = FileUtils.getFileExtension(fileName).toLowerCase();
        if (FILE_BLACKLIST_EXTENSIONS.contains(ext))
        {
            return true;
        }

        // 检查路径关键词
        String normalizedPath = fileName.replace('\\', '/').toLowerCase();
        for (String keyword : PATH_BLACKLIST_KEYWORDS)
        {
            if (normalizedPath.contains(keyword.toLowerCase()))
            {
                return true;
            }
        }

        // 检查路径遍历
        if (fileName.contains("../") || fileName.contains("..\\"))
        {
            return true;
        }

        return false;
    }
    /**
     * 文件删除
     *
     * @param  jsonObject
     */
    @RequestMapping("/deleteFile")
//    public void deleteFile(@Param("fileName") String fileName)
    public void deleteFile(@RequestBody JSONObject jsonObject)
    {
        try
        {
            String fileName = jsonObject.getString("fileName");
            fileName = fileName.replace(Constants.RESOURCE_PREFIX+"/upload","");
            String filePath = RuoYiConfig.getUploadPath() + fileName;
            FileUtils.deleteFile(filePath);
        }
        catch (Exception e)
        {
            log.error("文件删除失败", e);
        }
    }
    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传请求（多个）
     */
    @ApiOperation("多文件上传")
    @PostMapping("/uploads")
    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            String fileIds = "";
            for (MultipartFile file : files)
            {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                urls.add(url);
                fileNames.add(fileName);
                newFileNames.add(FileUtils.getName(fileName));
                originalFilenames.add(file.getOriginalFilename());
                //保存到附件表
                String name = file.getOriginalFilename();
                String suffix = name.substring(name.indexOf(".")+1);
                Double fileSize =Double.valueOf( file.getSize());
                Double size =Double.valueOf(String.format("%.2f",fileSize/1024*1024)) ;
                XcxFile xcxFile = new XcxFile();
                xcxFile.setFileName(fileName);
                xcxFile.setSuffix(suffix);
                xcxFile.setSize(size);
                xcxFile.setUploadTime(new Date());
                xcxFile.setPath(url);
                fileService.insert(xcxFile);
                fileIds += xcxFile.getId()+",";
            }
            if (StringUtils.isNotEmpty(fileIds)){
                fileIds = fileIds.substring(0,fileIds.lastIndexOf(","));
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            ajax.put("fileIds", fileIds);
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
            ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {

            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = RuoYiConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }
}
