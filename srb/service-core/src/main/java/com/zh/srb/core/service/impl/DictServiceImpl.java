package com.zh.srb.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.srb.core.listener.ExcelDictDTOListener;
import com.zh.srb.core.pojo.entity.Dict;
import com.zh.srb.core.mapper.DictMapper;
import com.zh.srb.core.pojo.entity.ExcelDictDTO;
import com.zh.srb.core.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2026-01-14
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importData(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelDictDTO.class,new ExcelDictDTOListener(baseMapper)).sheet().doRead();
        log.info("import Data finished");
    }

    @Override
    public List<ExcelDictDTO> listDictData() {

        List<Dict> dictList = baseMapper.selectList(null);
        List<ExcelDictDTO> excelDictDTOList = new ArrayList<>(dictList.size());

        dictList.forEach(dict -> {
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();
            BeanUtils.copyProperties(dict,excelDictDTO);
            excelDictDTOList.add(excelDictDTO);
        });

        return excelDictDTOList;
    }

    @Override
    public List<Dict> listByParentId(Long parentId) {

        //先查询redis中是否存在数据列表
        List<Dict> dictList=null;

        try{
            dictList=(List<Dict>)redisTemplate.opsForValue().get("srb:core:dictList:"+parentId);
            if(dictList!=null){
                log.info("从redis中取值");
                return dictList;
            }
        }catch (Exception e){
            log.error("redis服务器异常");
        }

        log.info("从数据库中取值");
        dictList= baseMapper.selectList(new QueryWrapper<Dict>().eq("parent_id", parentId));
        dictList.forEach(dict -> {
            boolean hasChildren=this.hasChildren(dict.getId());
            dict.setHasChildren(hasChildren);
        });

        //将数据存入redis
        try{
            redisTemplate.opsForValue().set("srb:core:dictList:"+parentId,dictList,5 , TimeUnit.MINUTES);
            log.info("数据存入redis");
        }catch (Exception e){
            log.error("redis服务器异常");
        }
        return dictList;
    }

    /**
     * 判断该节点是否有子节点
     */
    private boolean hasChildren(Long id){
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>().eq("parent_id", id);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count.intValue()>0){
            return true;
        }else{
            return false;
        }
    }
}
