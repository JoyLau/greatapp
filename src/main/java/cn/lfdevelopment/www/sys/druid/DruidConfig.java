/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.druid;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by LiuFa on 2016/9/14.
 * cn.lfdevelopment.www.sys.druid
 * DevelopmentApp
 */
@Configuration
public class DruidConfig{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource() {
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
            public void init() {
                try {
                    logger.info("Datasource Initializing.....");
                    super.init();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
