//package com.kevingates.uploadimage.config;
//
//
//import org.apache.ibatis.datasource.pooled.PooledDataSource;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.config.PropertiesFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author 徐森威
// * @date 2017/11/14
// */
//@Configuration
////@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*"})
//public class MybatisTableConfig {
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String driver;
//
//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Value("${mybatis.mapper-locations}")
//    private String mapperLocations;
//
//    @Bean
//    public PropertiesFactoryBean configProperties() throws Exception{
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        propertiesFactoryBean.setLocations(resolver.getResources("classpath*:application.properties"));
//        return propertiesFactoryBean;
//    }
//
//    @Bean
//    public PooledDataSource dataSource() {
//        PooledDataSource dataSource;
//        try {
//            dataSource = new PooledDataSource(driver, url, configProperties().getObject());
//            dataSource.setUrl(url);
//            dataSource.setUsername(username);
//            dataSource.setPassword(password);
//            return dataSource;
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Bean
//    public DataSourceTransactionManager dataSourceTransactionManager() {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(dataSource());
//        return dataSourceTransactionManager;
//    }
//
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactory() throws Exception{
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        List<Resource> resources = new ArrayList<>();
//        for(String s:mapperLocations.split(","))
//            resources.addAll(Arrays.asList(resolver.getResources(s)));
//        Resource[] rs = new Resource[resources.size()];
//        int i = 0;
//        for(Resource resource : resources) {
//            rs[i] = resource;
//            i++;
//        }
//        sqlSessionFactoryBean.setMapperLocations(rs);
//        sqlSessionFactoryBean.setTypeAliasesPackage("com.kevingates.uploadimage.models.*");
//        return sqlSessionFactoryBean;
//    }
//
//}