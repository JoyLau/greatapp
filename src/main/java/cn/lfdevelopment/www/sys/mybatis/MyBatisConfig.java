/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.mybatis;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by LiuFa on 2016/8/8.
 * cn.lfdevelopment.www.sys.mybatis
 * DevelopmentApp
 * DataSource 交由Druid自动根据配置创建
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {

    @Autowired
    private DataSource dataSource;


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("cn.lfdevelopment.www.app.**.pojo");
        //支持属性使用驼峰的命名,mapper配置不需要写字段与属性的配置，会自动映射。
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        /* 3.3.0版本可用 - 分页参数合理化，默认false禁用
         启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
         禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
         在EXTjs里面配置与否无所谓，因为在前台传过来的分页数据已经进行合理化了 */
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
       /* 3.5.0版本可用 - 为了支持startPage(Object params)方法
         增加了一个`params`参数来配置参数映射，用于从Map或ServletRequest中取值
         可以配置pageNum,pageSize,count,pageSizeZero,reasonable,orderBy,不配置映射的用默认值
         不理解该含义的前提下，不要随便复制该配置 -->*/

//        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:mapperxml/**/*Mapper.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        try {
            return new DataSourceTransactionManager(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
