/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.druid;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by LiuFa on 2016/9/14.
 * cn.lfdevelopment.www.sys.druid
 * DevelopmentApp
 */
@Configuration
public class DruidConfig{

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource() {
            @Override
            public void setUsername(String username) {
                try {
                    username = ConfigTools.decrypt(username);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.setUsername(username);
            }

            @Override
            public void setUrl(String jdbcUrl) {
                try {
                    jdbcUrl = ConfigTools.decrypt(jdbcUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.setUrl(jdbcUrl);
            }
            @Override
            public void setDriverClassName(String driverClassName) {
                super.setUrl(driverClassName);
            }
        };
        return druidDataSource;
    }
}
