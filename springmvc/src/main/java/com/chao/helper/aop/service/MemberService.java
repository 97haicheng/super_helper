package com.chao.helper.aop.service;

import com.chao.helper.aop.model.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by think on 2017/4/24.
 */
@Service("memberService")
public class MemberService {

    Logger LOG = Logger.getLogger(String.valueOf(MemberService.class));

    public Member get(Long id){
        LOG.info("get member by id method...");
        return new Member();
    }

    public List<Member> get(){
        LOG.info("get member method...");
        return new ArrayList<Member>();
    }

    public Boolean add(Member member){
        LOG.info("add member method...");
        return true;
    }

    public boolean romove(Long id) throws Exception{
        LOG.info("romove member method...");
        throw new Exception("Spring AOP ThrowAdvice...");
    }
}
