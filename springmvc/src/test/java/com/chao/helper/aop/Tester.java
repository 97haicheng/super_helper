package com.chao.helper.aop;

import com.chao.helper.aop.model.Member;
import com.chao.helper.aop.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by think on 2017/4/24.
 */
@ContextConfiguration(locations = {"classpath*:application-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class Tester {

    @Autowired
    private MemberService memberService;

    @Test
    public void test(){
        memberService.add(new Member());
    }

}
