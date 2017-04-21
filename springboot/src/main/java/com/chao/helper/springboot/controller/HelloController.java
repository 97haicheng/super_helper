package com.chao.helper.springboot.controller;

import com.chao.helper.springboot.service.Send;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 返回结果为视图文件路径。视图相关文件默认放置在路径 resource/templates下：
 */
@Controller
public class HelloController {

    private Logger logger = Logger.getLogger(HelloController.class);

    @Autowired
    private Send send;

    /*
     * http://127.0.0.1:8080/h5/hello?name=quchao
     */
    @RequestMapping("/hello")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        logger.info("hello");
        model.addAttribute("name", name);
        send.sendMsg("I'm " + name);
        return "hello";
    }
    
}
