package com.ttmy.awm.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * 随项目启动初始化数据源
 * 反而变慢了？
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidDataSourceConfig {
    private String driverClassName;
    private String url;
    private String username;
    private String password;


//    @Bean(name = "dataSource",initMethod = "init",destroyMethod = "close")
//    public DruidDataSource dataSource() throws SQLException {
//        DruidDataSource ds = new DruidDataSource();
//        ds.setDriverClassName(this.driverClassName);
//        ds.setUrl(this.url);
//        ds.setUsername(this.username);
//        ds.setPassword(this.password);
////        ds.setInitialSize(this.initialSize.intValue());
////        ds.setMinIdle(this.minIdle.intValue());
////        ds.setMaxActive(this.maxActive.intValue());
////        ds.setMaxWait(this.maxWait.longValue());
////        ds.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis.longValue());
////        ds.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis.longValue());
////        ds.setValidationQuery(this.validationQuery);
////        ds.setTestWhileIdle(this.testWhileIdle.booleanValue());
////        ds.setTestOnBorrow(this.testOnBorrow.booleanValue());
////        ds.setTestOnReturn(this.testOnReturn.booleanValue());
////        ds.setFilters(this.filters);
//        ds.init();
//        return ds;
//    }
}
