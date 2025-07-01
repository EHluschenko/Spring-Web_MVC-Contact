package ed.hluschenko.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:app.properties")
@EnableTransactionManagement
public class AppContext {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
sessionFactory.setPackagesToScan("ed.hluschenko.entity");
sessionFactory.setHibernateProperties(hibernateProperties());
return sessionFactory;}

@Bean
    public DataSource dataSource(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
dataSource.setDriverClassName(environment.getRequiredProperty("jdbcDriver"));
dataSource.setUrl(environment.getRequiredProperty("dbUrl"));
dataSource.setUsername(environment.getRequiredProperty("dbUser"));
dataSource.setPassword(environment.getRequiredProperty("dbPass"));
return dataSource;
}

private Properties hibernateProperties(){
    Properties properties = new Properties();
    properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
    properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
    properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
    return properties;
}

@Bean
public HibernateTransactionManager getTransactionManager(){
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
}

    @Bean(initMethod = "migrate") // triggers flyway.migrate() on startup
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)  // You must add this line if your DB is not empty
                .load();
    }


}
