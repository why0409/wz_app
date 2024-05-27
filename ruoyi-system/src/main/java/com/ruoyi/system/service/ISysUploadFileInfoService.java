package com.ruoyi.system.service;

import com.ruoyi.jishijiao.Dto.FileReturnDto;
import com.ruoyi.system.domain.SysUploadFileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 文件上传记录信息Service接口
 * 
 * @author admin
 * @date 2021-04-19
 */
public interface ISysUploadFileInfoService 
{
    /**
     * 查询文件上传记录信息
     * 
     * @param id 文件上传记录信息ID
     * @return 文件上传记录信息
     */
    public SysUploadFileInfo selectSysUploadFileInfoById(Long id);

    /**
     * 查询文件上传记录信息列表
     * 
     * @param sysUploadFileInfo 文件上传记录信息
     * @return 文件上传记录信息集合
     */
    public List<SysUploadFileInfo> selectSysUploadFileInfoList(SysUploadFileInfo sysUploadFileInfo);

    /**
     * 新增文件上传记录信息
     * 
     * @param sysUploadFileInfo 文件上传记录信息
     * @return 结果
     */
    public int insertSysUploadFileInfo(SysUploadFileInfo sysUploadFileInfo);

    /**
     * 修改文件上传记录信息
     * 
     * @param sysUploadFileInfo 文件上传记录信息
     * @return 结果
     */
    public int updateSysUploadFileInfo(SysUploadFileInfo sysUploadFileInfo);

    /**
     * 批量删除文件上传记录信息
     * 
     * @param ids 需要删除的文件上传记录信息ID
     * @return 结果
     */
    public int deleteSysUploadFileInfoByIds(Long[] ids);

    /**
     * 删除文件上传记录信息信息
     * 
     * @param id 文件上传记录信息ID
     * @return 结果
     */
    public int deleteSysUploadFileInfoById(Long id);

    /**
     * 文件上传
     * @param type 文件上传类型
     * @param urlList 文件集合
     * @return
     */
    List<FileReturnDto> uploadFile(String type, MultipartFile[] urlList)throws Exception;

    /**
     *删除文件路径信息
     * @param sysUploadFileInfo
     * @return
     */
    String deleteFile(SysUploadFileInfo sysUploadFileInfo) throws Exception;

    /**
     * 根据条件先查询到信息在根据id进行删除
     * @param sysUploadFileInfo
     * @return
     */
    public int deleteInfoBycondition(SysUploadFileInfo sysUploadFileInfo);

    /**
     * 根据父键删除
     * @param parentIds
     * @return
     */
    public int deleteInfoByparentId(Long[] parentIds);

    /**
     * 根据条件删除文件
     * @param map
     * @return
     */
    public int deleteInfoByCondition(Map<String, String> map);
}
