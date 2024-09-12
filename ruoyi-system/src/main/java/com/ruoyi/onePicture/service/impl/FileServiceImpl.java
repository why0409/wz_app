package com.ruoyi.onePicture.service.impl;

import com.ruoyi.onePicture.domain.XcxFile;
import com.ruoyi.onePicture.mapper.FileMapper;
import com.ruoyi.onePicture.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author tonyJiang
 * @Date 2023 10 16 14 13
 **/
@Service
public class FileServiceImpl implements IFileService {
    @Autowired
    private FileMapper fileMapper;
    @Override
    public void insert(XcxFile xcxFile) {
        fileMapper.insert(xcxFile);
    }
}
