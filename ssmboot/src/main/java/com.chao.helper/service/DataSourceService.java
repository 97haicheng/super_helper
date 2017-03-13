package com.chao.helper.service;

import com.chao.helper.pojo.Datasource;
import com.chao.helper.pojo.DatasourceExample;

import java.util.List;

/**
 * Function:
 *
 * @author chenjiec
 *         Date: 2016/12/9 上午12:16
 * @since JDK 1.7
 */
public interface DataSourceService {
    List<Datasource> selectByExample(DatasourceExample example);

    Datasource selectByPrimaryKey(Integer id);


}
