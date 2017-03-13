package com.chao.helper.service.ssmone;

import com.chao.helper.dao.ContentLogMapper;
import com.chao.helper.pojo.ContentLog;
import com.chao.helper.service.ContentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Function:
 *
 * @author chenjiec
 *         Date: 2017/1/16 上午12:12
 * @since JDK 1.7
 */
@Service
public class ContentLogServiceImpl implements ContentLogService {

    @Autowired
    private ContentLogMapper contentLogMapper;

    @Override
    public int insertSelective(ContentLog record) {

        return contentLogMapper.insertSelective(record);
    }
}
