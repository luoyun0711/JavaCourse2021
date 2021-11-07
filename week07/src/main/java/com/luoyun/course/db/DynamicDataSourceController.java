package com.luoyun.course.db;

import com.luoyun.course.db.datasource1.DynamicDataSourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * DynamicDataSourceController
 *
 * @author luoyun
 * @data 2021/11/7
 */
@RestController
public class DynamicDataSourceController {

    @Resource
    private DynamicDataSourceService dynamicDataSourceService;

    @RequestMapping("/test/dynamicDataSource1")
    public void dynamicDataSource1() {
        dynamicDataSourceService.getTest1Data();
        dynamicDataSourceService.getTest2Data();
    }

}
