package com.erp.batch.mainapp.config;

import com.infoworks.lab.jsql.JsqlConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
//@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "tmrOracleEntityManagerFactory",
        transactionManagerRef = "tmrOracleTransactionManager",
        basePackages = {"com.erp.batch.domain.entities"}
)
//@PropertySource("classpath:oracle-db-local.properties")
@PropertySource("classpath:oracle-db.properties")
public class TmrOracleDBConfig {

    private Environment env;
    public TmrOracleDBConfig(@Autowired Environment env) {
        this.env = env;
    }

    @Primary
    @Bean("TmrOracleJsqlConfig")
    JsqlConfig getJsqlConfig(@Qualifier("TmrOracleDataSource") DataSource dataSource){
        return new JsqlConfig(dataSource);
    }

    @Bean("AppOracleDBNameKey")
    String appDBNameKey(){
        return env.getProperty("app.db.name");
    }

    @Value("${spring.datasource.driver-class-name}")
    String driverClassName;

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${app.db.name}")
    String persistenceUnitName;

    @Value("${spring.datasource.hikari.poolName}")
    String poolName;

    @Value("${spring.datasource.hikari.maximumPoolSize}")
    String maximumPoolSize;

    @Value("${spring.datasource.hikari.minimumIdle}")
    String minimumIdle;

    @Value("${spring.datasource.hikari.idleTimeout}")
    String idleTimeout;

    @Value("${spring.datasource.hikari.connectionTimeout}")
    String connectionTimeout;

    @Value("${spring.datasource.hikari.maxLifetime}")
    String maxLifetime;

    /*@Primary
    @Bean("TmrOracleDataSource")
    public DataSource dataSource(){
        DataSource dataSource = DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url(url)
                .driverClassName(driverClassName)
                .build();
        return dataSource;
    }*/

    @Primary
    @Bean("TmrOracleDataSource")
    public HikariDataSource dataSource() {
        final HikariDataSource hds = new HikariDataSource();
        hds.setDriverClassName(driverClassName);
        hds.setJdbcUrl(url); ;
        hds.setUsername(username);
        hds.setPassword(password);
        hds.setPoolName(poolName);
        hds.setMaximumPoolSize(Integer.parseInt(maximumPoolSize));
        hds.setMinimumIdle(Integer.parseInt(minimumIdle));
        hds.setIdleTimeout(Long.parseLong(idleTimeout));
        hds.setConnectionTimeout(Long.parseLong(connectionTimeout));
        hds.setMaxLifetime(Long.parseLong(maxLifetime));
        return hds;
    }

    @Primary
    @Bean("tmrOracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder
            , @Qualifier("TmrOracleDataSource") DataSource dataSource){
        return builder
                .dataSource(dataSource)
                .packages("com.infoworks.lab.domain.entities")
                .persistenceUnit(persistenceUnitName)
                .build();
    }

    @Bean("tmrOracleTransactionManager")
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

}
