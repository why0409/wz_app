package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.SysUploadFileInfo;

import java.util.List;
import java.util.Map;

/**
 * 文件上传记录信息Mapper接口
 * 
 * @author admin
 * @date 2021-04-19
 */
public interface SysUploadFileInfoMapper 
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
     * 删除文件上传记录信息
     * 
     * @param id 文件上传记录信息ID
     * @return 结果
     */
    public int deleteSysUploadFileInfoById(Long id);

    /**
     * 批量删除文件上传记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUploadFileInfoByIds(Long[] ids);

    public int deleteInfoByparentId(Long[] parentIds);

    public List<SysUploadFileInfo> selectSysUploadFileInfoByIds(Long[] parentIds);

    /**
     * 根据条件删除信息
     * @param map
     * @return
     */
    public List<SysUploadFileInfo> selectByCondition(Map<String, String> map);
}
