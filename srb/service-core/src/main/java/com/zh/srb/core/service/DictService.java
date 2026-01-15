package com.zh.srb.core.service;

import com.zh.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.srb.core.pojo.entity.ExcelDictDTO;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author zhouhui
 * @since 2026-01-14
 */
public interface DictService extends IService<Dict> {

    void importData(InputStream inputStream);

    List<ExcelDictDTO> listDictData();

    List<Dict> listByParentId(Long parentId);
}
