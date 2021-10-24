package com.luoyun.course.spring.bean_assembly;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * ConfigurationAssembly
 *
 * @author luoyun
 * @data 2021/10/24
 */
@Configurable
public class ConfigurationAssembly {

    @Bean(name = "configurationBean")
    private ConfigurationBean getConfigurationBean() {
        return new ConfigurationBean();
    }
}
