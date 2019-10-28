package com.dongyang.demo.hikaridemo.config;

import com.dongyang.demo.hikaridemo.enums.DataSourceEnum;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dongyang.hu
 * @date 2019/10/24 19:24
 */
@Data
@Component
@ConfigurationProperties(prefix = "hikari-demo.datasource")
public class DataSourceConfig {
    private HikariDataSource testDb1;
    private HikariDataSource testDb2;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceEnum.TEST_DB_1.name(), this.testDb1);
        targetDataSources.put(DataSourceEnum.TEST_DB_2.name(), this.testDb2);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(this.testDb1);
        return dataSource;
    }
}
