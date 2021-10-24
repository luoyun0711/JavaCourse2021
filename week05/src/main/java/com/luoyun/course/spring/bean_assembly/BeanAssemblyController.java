package com.luoyun.course.spring.bean_assembly;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * BeanAssemblyController
 *
 * @author luoyun
 * @data 2021/10/24
 */
@RestController
public class BeanAssemblyController {

    @Resource
    private AutoAssembly byAutoAssembly;
    @Resource
    private ConfigurationBean configurationBean;
    @Resource
    private XmlBean xmlBean;

    @RequestMapping("/bean/assembly/test")
    public String sendQueue() {
        return byAutoAssembly.getInfo() + ";"
                + configurationBean.getInfo() + ";"
                + xmlBean.getInfo() + ";";
    }


}
