package com.ruoyi.system.service.impl;

import java.util.*;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.system.domain.FgwDept;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.vo.FgwProProgressDto;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.IFgwDeptService;
import com.ruoyi.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FgwProProgressMapper;
import com.ruoyi.system.domain.FgwProProgress;
import com.ruoyi.system.service.IFgwProProgressService;

/**
 * 项目进度Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
@Service
public class FgwProProgressServiceImpl implements IFgwProProgressService 
{
    @Autowired
    private FgwProProgressMapper fgwProProgressMapper;
    @Autowired
    private IFgwDeptService fgwDeptService;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 查询项目进度
     * 
     * @param id 项目进度主键
     * @return 项目进度
     */
    @Override
    public FgwProProgressDto selectFgwProProgressById(Long id)
    {
        FgwProProgressDto fgwProProgressDto = fgwProProgressMapper.selectFgwProProgressById(id);

        List<Integer> integerList = new ArrayList<>();
        if(StringUtils.isNotEmpty(fgwProProgressDto.deptIds)){
            String[] strArr = fgwProProgressDto.deptIds.split(",");
            for(int i=0;i<strArr.length;i++){
                integerList.add(Integer.valueOf(strArr[i]));
            }
            fgwProProgressDto.setBelongtodeptArr(integerList);
        }
        return fgwProProgressDto;
    }

    /**
     * 查询项目进度列表
     * 
     * @param fgwProProgressDto1 项目进度
     * @return 项目进度
     */
    @Override
    public List<FgwProProgressDto> selectFgwProProgressList(FgwProProgressDto fgwProProgressDto1)
    {
        List<FgwProProgressDto> fgwProProgressList = new ArrayList<>();
        String sWhere = "";
        String str ="";
        String str_deptId = " AND 1 = 1";
        Map<String,String> map = new HashMap<>();
        try {
            if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){//非管理员
                SysRole sysRole = null;

                //根据人员id查询岗位
                List<SysUserRole> sysUserRoleList = userRoleMapper.selectUserRoleByUserId(SecurityUtils.getUserId());
                for(SysUserRole sysUserRole:sysUserRoleList){//数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限 5：本人权限）
                    sysRole = new SysRole();
                    sysRole = roleMapper.selectRoleById(sysUserRole.getRoleId());
                    if("5".equals(sysRole.getDataScope())){//本人权限
                        str = " AND create_by = '"+SecurityUtils.getUserId()+"'";
                    }
                }
                //获取人员部门权限
                String power = sysRoleService.getPowerInfoByCondition("dept_id",SecurityUtils.getUserId());
                if(fgwProProgressDto1.getBelongtodeptArr()!=null && fgwProProgressDto1.getBelongtodeptArr().size()>0){
                    str_deptId = str_deptId + SqlUtil.appendSql(fgwProProgressDto1.getBelongtodeptArr(), "dept_id");
                }


                String abv = power+str_deptId+str;
                map.put("condition",abv.replaceFirst("AND"," "));
                List<Long> longs = fgwDeptService.selectPIdByMap(map);//获取父键id集合
                sWhere = SqlUtil.appendSql(longs, "id");
            }else{//管理员
                if(fgwProProgressDto1.getBelongtodeptArr()!=null && fgwProProgressDto1.getBelongtodeptArr().size()>0){
                    str_deptId = str_deptId + SqlUtil.appendSql(fgwProProgressDto1.getBelongtodeptArr(), "dept_id");
                }
                map.put("condition",str_deptId.replaceFirst("AND"," "));
                List<Long> longs = fgwDeptService.selectPIdByMap(map);//获取父键id集合
                sWhere = SqlUtil.appendSql(longs, "id");
            }
            if(StringUtils.isNotEmpty(sWhere)){
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(" AND 1=1 ");
                if(StringUtils.isNotEmpty(fgwProProgressDto1.getProName())){
                    stringBuffer.append(" AND pro_name = '"+fgwProProgressDto1.getProName()+"'");
                }
                if(StringUtils.isNotEmpty(fgwProProgressDto1.getYear())){
                    stringBuffer.append(" AND year = '"+fgwProProgressDto1.getYear()+"'");
                }
                if(StringUtils.isNotEmpty(fgwProProgressDto1.getHhlType())){
                    stringBuffer.append(" AND hhl_type = '"+fgwProProgressDto1.getHhlType()+"'");
                }
                if(StringUtils.isNotEmpty(fgwProProgressDto1.getCateType())){
                    stringBuffer.append(" AND cate_type = '"+fgwProProgressDto1.getCateType()+"'");
                }
                if(StringUtils.isNotEmpty(fgwProProgressDto1.getProType())){
                    stringBuffer.append(" AND pro_type = '"+fgwProProgressDto1.getProType()+"'");
                }
                sWhere = sWhere+stringBuffer.toString();

                Map<String,String> stringMap = new HashMap<>();

                stringMap.put("condition",sWhere.replaceFirst("AND"," "));
                fgwProProgressList = fgwProProgressMapper.selectListByMap(stringMap);
                String[] strArr = null;
                List<Integer> integerList = null;
                for(FgwProProgressDto fgwProProgressDto:fgwProProgressList){
                    integerList = new ArrayList<>();
                    if(StringUtils.isNotEmpty(fgwProProgressDto.deptIds)){
                        strArr = fgwProProgressDto.deptIds.split(",");
                        for(int i=0;i<strArr.length;i++){
                            integerList.add(Integer.valueOf(strArr[i]));
                        }
                        fgwProProgressDto.setBelongtodeptArr(integerList);
                    }
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return fgwProProgressList;
    }

    /**
     * 新增项目进度
     * 
     * @param fgwProProgressDto 项目进度
     * @return 结果
     */
    @Override
    public int insertFgwProProgress(FgwProProgressDto fgwProProgressDto)
    {
        FgwProProgress fgwProProgress = new FgwProProgress();
        BeanUtils.copyBeanProp(fgwProProgress,fgwProProgressDto);
        fgwProProgress.setCreateTime(DateUtils.getNowDate());
        fgwProProgress.setCreateBy(SecurityUtils.getUsername());
        fgwProProgress.setHhlType("green");//新建时默认绿色
        int count = fgwProProgressMapper.insertFgwProProgress(fgwProProgress);
        //插入部门关联表
        List<Integer> deptList = fgwProProgressDto.getBelongtodeptArr();
        FgwDept fgwDept = null;
        for (Integer deptId:deptList){
            fgwDept = new FgwDept();
            fgwDept.setpId(fgwProProgress.getId());
            fgwDept.setDeptId(Long.valueOf(deptId));
            fgwDept.setCreateBy(SecurityUtils.getUsername());
            fgwDeptService.insertFgwDept(fgwDept);
        }
        return count;
    }

    /**
     * 修改项目进度
     * 
     * @param fgwProProgressDto 项目进度
     * @return 结果
     */
    @Override
    public int updateFgwProProgress(FgwProProgressDto fgwProProgressDto)
    {
        //关联表先删后插
        fgwDeptService.deleteFgwDeptByPId(fgwProProgressDto.getId());
        //插入部门关联表
        List<Integer> deptList = fgwProProgressDto.getBelongtodeptArr();
        FgwDept fgwDept = null;
        for (Integer deptId:deptList){
            fgwDept = new FgwDept();
            fgwDept.setpId(fgwProProgressDto.getId());
            fgwDept.setDeptId(Long.valueOf(deptId));
            fgwDeptService.insertFgwDept(fgwDept);
        }
        FgwProProgress fgwProProgress = new FgwProProgress();
        BeanUtils.copyBeanProp(fgwProProgress,fgwProProgressDto);
        fgwProProgress.setUpdateTime(DateUtils.getNowDate());
        return fgwProProgressMapper.updateFgwProProgress(fgwProProgress);
    }

    /**
     * 批量删除项目进度
     * 
     * @param ids 需要删除的项目进度主键
     * @return 结果
     */
    @Override
    public int deleteFgwProProgressByIds(Long[] ids)
    {
        fgwDeptService.deleteFgwDeptByPIds(ids);
        return fgwProProgressMapper.deleteFgwProProgressByIds(ids);
    }

    /**
     * 删除项目进度信息
     * 
     * @param id 项目进度主键
     * @return 结果
     */
    @Override
    public int deleteFgwProProgressById(Long id)
    {
        fgwDeptService.deleteFgwDeptByPId(id);
        return fgwProProgressMapper.deleteFgwProProgressById(id);
    }

    /**
     * 根据id更新红黄绿标识
     * @author:
     * @date: 2022/11/24 15:29
     * @param fgwProProgress
     * @return
     */
    public int updateHhlTypeById(FgwProProgress fgwProProgress){
        return fgwProProgressMapper.updateHhlTypeById(fgwProProgress);
    }
}
