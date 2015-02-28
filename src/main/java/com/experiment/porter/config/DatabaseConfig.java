package com.experiment.porter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Value("${porter.db:oracle}")
    String database;

    @Value("${porter.db.url}")
    String databaseUrl;

    @Value("${porter.db.username}")
    String databaseUsername;

    @Value("${porter.db.password}")
    String databasePassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        switch (database) {
            case "mysql":
                //TODO Mysql supported to be implemented
                //break;
            case "oracle":
            default:
                dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
                break;
        }
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.experiment.porter.domain");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        switch (database) {
            case "mysql":
                //TODO Mysql supported to be implemented
                //break;
            case "oracle":
            default:
                adapter.setDatabase(Database.ORACLE);
                break;
        }
        adapter.setShowSql(false);
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.experiment.porter.domain");
        return lef;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory(dataSource(),jpaVendorAdapter()).getObject().createEntityManager();
    }

    public Properties hibernateProperties() {
        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
        prop.setProperty("hibernate.show_sql","false");
        prop.setProperty("hibernate.hbm2ddl.auto","validate");
        return prop;
    }
}
