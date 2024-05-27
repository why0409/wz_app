package com.ruoyi.system.service.impl;


import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.jishijiao.Dto.FileReturnDto;
import com.ruoyi.system.domain.SysUploadFileInfo;
import com.ruoyi.system.mapper.SysUploadFileInfoMapper;
import com.ruoyi.system.service.ISysUploadFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

/**
 * 文件上传记录信息Service业务层处理
 * 
 * @author admin
 * @date 2021-04-19
 */
@Service
public class SysUploadFileInfoServiceImpl implements ISysUploadFileInfoService
{
    @Autowired
    private SysUploadFileInfoMapper sysUploadFileInfoMapper;

    /**
     * 查询文件上传记录信息
     * 
     * @param id 文件上传记录信息ID
     * @return 文件上传记录信息
     */
    @Override
    public SysUploadFileInfo selectSysUploadFileInfoById(Long id)
    {
        return sysUploadFileInfoMapper.selectSysUploadFileInfoById(id);
    }

    /**
     * 查询文件上传记录信息列表
     * 
     * @param sysUploadFileInfo 文件上传记录信息
     * @return 文件上传记录信息
     */
    @Override
    public List<SysUploadFileInfo> selectSysUploadFileInfoList(SysUploadFileInfo sysUploadFileInfo)
    {
        return sysUploadFileInfoMapper.selectSysUploadFileInfoList(sysUploadFileInfo);
    }

    /**
     * 新增文件上传记录信息
     * 
     * @param sysUploadFileInfo 文件上传记录信息
     * @return 结果
     */
    @Override
    public int insertSysUploadFileInfo(SysUploadFileInfo sysUploadFileInfo)
    {
        sysUploadFileInfo.setCreateTime(DateUtils.getNowDate());
        sysUploadFileInfo.setCreateBy(SecurityUtils.getUsername());
        return sysUploadFileInfoMapper.insertSysUploadFileInfo(sysUploadFileInfo);
    }

    /**
     * 修改文件上传记录信息
     * 
     * @param sysUploadFileInfo 文件上传记录信息
     * @return 结果
     */
    @Override
    public int updateSysUploadFileInfo(SysUploadFileInfo sysUploadFileInfo)
    {
        sysUploadFileInfo.setUpdateTime(DateUtils.getNowDate());
        return sysUploadFileInfoMapper.updateSysUploadFileInfo(sysUploadFileInfo);
    }

    /**
     * 批量删除文件上传记录信息
     * 
     * @param ids 需要删除的文件上传记录信息ID
     * @return 结果
     */
    @Override
    public int deleteSysUploadFileInfoByIds(Long[] ids)
    {
        return sysUploadFileInfoMapper.deleteSysUploadFileInfoByIds(ids);
    }

    /**
     * 删除文件上传记录信息信息
     * 
     * @param id 文件上传记录信息ID
     * @return 结果
     */
    @Override
    public int deleteSysUploadFileInfoById(Long id)
    {
        return sysUploadFileInfoMapper.deleteSysUploadFileInfoById(id);
    }

    /**
     * 文件上传
     * parentId 父id
     * @param type 文件上传类型
     * @param urlList 文件集合
     * @return
     */
    @Override
    @Transactional(rollbackFor =  Exception.class)
    public List<FileReturnDto> uploadFile(String type, MultipartFile[] urlList)throws Exception {
        FileReturnDto fileReturnDto = null;
        List<FileReturnDto> fileReturnDtoList = new ArrayList<>();
        try {
            SysUploadFileInfo sysUploadFileInfo = null;
            String path = "";
            Date date=new Date();
            SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
            String dateStr=format.format(date);
            if(urlList.length>0){
                for(MultipartFile file : urlList) {
                    fileReturnDto = new FileReturnDto();
                    sysUploadFileInfo = new SysUploadFileInfo();
                    path = FileUploadUtils.upload(RuoYiConfig.getUploadPath(dateStr)+"/"+type, file);
                    sysUploadFileInfo.setFname(file.getOriginalFilename());
                    sysUploadFileInfo.setFurl(path);
                    sysUploadFileInfo.setFtype(type);
                    this.insertSysUploadFileInfo(sysUploadFileInfo);
                    fileReturnDto.setFileName(file.getOriginalFilename());
                    fileReturnDto.setUrl(path);
                    fileReturnDtoList.add(fileReturnDto);
                }
            }
        } catch (IOException e) {
            //如果上传失败且未回滚，需要在此进行手动清除记录
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("文件保存失败");
        }
        return fileReturnDtoList;
    }

    /**
     *  删除文件路径信息
     * @param sysUploadFileInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor =  Exception.class)
    public String deleteFile(SysUploadFileInfo sysUploadFileInfo) throws Exception{
        String  returnCode = "9999";
        try {
            //先根据条件查询表中信息是否存在且只有一条
            List<SysUploadFileInfo> sysUploadFileInfoList = this.selectSysUploadFileInfoList(sysUploadFileInfo);
            if(sysUploadFileInfoList!=null || sysUploadFileInfoList.size()==1 ){
                String filePath = sysUploadFileInfo.getFurl().replaceAll("/profile", RuoYiConfig.getProfile());
                //根据条件删除路径信息表
                sysUploadFileInfoMapper.deleteSysUploadFileInfoById(sysUploadFileInfoList.get(0).getId());
                if(!FileUtils.deleteFile(filePath)){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    throw new RuntimeException("文件删除失败");
                }
                returnCode = "0000";

            }else{
                throw new RuntimeException("文件删除失败,存在多条记录信息！");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("文件删除失败");
        }
        return returnCode;
    }

    /**
     * 根据条件先查询到信息在根据id进行删除
     * @param sysUploadFileInfo
     * @return
     */
    public int deleteInfoBycondition(SysUploadFileInfo sysUploadFileInfo){
            //先根据条件查询所有记录
        List<SysUploadFileInfo> sysUploadFileInfoList =  this.selectSysUploadFileInfoList(sysUploadFileInfo);
        return this.handleRemove(sysUploadFileInfoList);
    }

    /**
     * 根据集合获取id进行批量删除
     * @param sysUploadFileInfoList
     * @return
     */
    public int handleRemove(List<SysUploadFileInfo> sysUploadFileInfoList){
        Long[] idArray = new Long[sysUploadFileInfoList.size()];
        Stream.iterate(0, i -> i + 1).limit(sysUploadFileInfoList.size()).forEach(index -> {
            idArray[index] = sysUploadFileInfoList.get(index).getId();
        });
        int result  = 0;
        if(idArray.length>0){
            this.deleteSysUploadFileInfoByIds(idArray);
        }
        return result;
    }
    /**
     * 根据父键删除信息
     * @param parentIds
     * @return
     */
    public int deleteInfoByparentId(Long[] parentIds){
        //先根据父键查询
        List<SysUploadFileInfo> sysUploadFileInfoList = sysUploadFileInfoMapper.selectSysUploadFileInfoByIds(parentIds);
        try {
            if(sysUploadFileInfoList.size()>0){
                this.deleteFileInServer(sysUploadFileInfoList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.handleRemove(sysUploadFileInfoList);
    }

    /**
     * 根据条件删除文件
     * @param map
     * @return
     */
    public int deleteInfoByCondition(Map<String,String> map ){
        List<SysUploadFileInfo> sysUploadFileInfoList = sysUploadFileInfoMapper.selectByCondition(map);
        try {
            //删除服务器文件
            this.deleteFileInServer(sysUploadFileInfoList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.handleRemove(sysUploadFileInfoList);
    }

    /**
     * 根据集合逐条删除服务器文件
     * @param sysUploadFileInfoList
     */
    public void deleteFileInServer(List<SysUploadFileInfo>  sysUploadFileInfoList){
        try {
            for(SysUploadFileInfo sysUploadFileInfo:sysUploadFileInfoList){
                String filePath = sysUploadFileInfo.getFurl().replaceAll("/profile", RuoYiConfig.getProfile());
                if(!FileUtils.deleteFile(filePath)){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    throw new RuntimeException("文件删除失败");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("文件删除失败");
        }
    }

}
