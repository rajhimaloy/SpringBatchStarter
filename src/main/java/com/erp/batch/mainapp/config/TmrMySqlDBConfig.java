package com.erp.batch.mainapp.config;

import com.infoworks.lab.jsql.JsqlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "tmrMysqlEntityManagerFactory",
        transactionManagerRef = "tmrMysqlTransactionManager",
        basePackages = {"com.erp.batch.domain.entities"}
)
//@PropertySource("classpath:mysql-db-local.properties")
@PropertySource("classpath:mysql-db.properties")
public class TmrMySqlDBConfig {

    private Environment env;
    public TmrMySqlDBConfig(@Autowired Environment env) {
        this.env = env;
    }

    @Bean("TmrMysqlJsqlConfig")
    JsqlConfig getJsqlConfig(@Qualifier("TmrMysqlDataSource") DataSource dataSource){
        return new JsqlConfig(dataSource);
    }

    @Bean("AppMysqlDBNameKey")
    String appDBNameKey(){
        return env.getProperty("mysql.app.db.name");
    }

    @Value("${mysql.app.db.mysql.datasource.driver-class-name}") String driverClassName;
    @Value("${mysql.app.db.mysql.datasource.url}") String url;
    @Value("${mysql.app.db.mysql.datasource.username}") String username;
    @Value("${mysql.app.db.mysql.datasource.password}") String password;
    @Value("${mysql.app.db.name}") String persistenceUnitName;

    @Bean("TmrMysqlDataSource")
    public DataSource dataSource(){
        DataSource dataSource = DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url(url)
                .driverClassName(driverClassName)
                .build();
        return dataSource;
    }

    @Bean("tmrMysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("TmrMysqlDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean bean = builder
                .dataSource(dataSource)
                .packages("com.infoworks.lab.domain.repositories")
                .persistenceUnit(persistenceUnitName)
                .build();

        //Because, By-Default Spring can handle single Datasource
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setJpaProperties(additionalProperties());
        return bean;
    }

    @Bean("tmrMysqlTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    //Secondary Datasource, Because, By-Default Spring can handle single Datasource
    private Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("mysql.app.db.mysql.hibernate.show-sql"));
        hibernateProperties.setProperty("hibernate.generate-ddl", env.getProperty("mysql.app.db.mysql.hibernate.generate-ddl"));
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("mysql.app.db.mysql.hibernate.ddl-auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("mysql.app.db.mysql.hibernate.dialect"));
        return hibernateProperties;
    }
}
