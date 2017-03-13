package com.chao.helper.service.ssmone;

import com.chao.helper.dao.ContentDao;
import com.chao.helper.pojo.Content;
import com.chao.helper.service.ContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IDEA
 * Created by ${jie.chen} on 2016/7/14.
 */
@Service("contentService")
public class ContentServiceImpl implements ContentService {

    @Resource
    public ContentDao contentDao ;

    @Override
    public List<Content> findContentList() {
        return contentDao.findContentList();
    }

    @Override
    public int insertSelective(Content content) {
        return contentDao.insertSelective(content);
    }
}
