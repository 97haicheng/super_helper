package com.chao.helper.service.ssmtwo;

import com.chao.helper.dao.DatasourceMapper;
import com.chao.helper.pojo.Datasource;
import com.chao.helper.pojo.DatasourceExample;
import com.chao.helper.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Function:
 *
 * @author chenjiec
 *         Date: 2017/1/2 下午8:00
 * @since JDK 1.7
 */
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DatasourceMapper datasourceMapper;

    @Override
    public List<Datasource> selectByExample(DatasourceExample example) {
        return datasourceMapper.selectByExample(example);
    }

    @Override
    public Datasource selectByPrimaryKey(Integer id) {
        return datasourceMapper.selectByPrimaryKey(id);
    }
}
