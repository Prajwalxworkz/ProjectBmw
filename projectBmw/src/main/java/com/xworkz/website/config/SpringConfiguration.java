package com.xworkz.website.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.xworkz.website")
public class SpringConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/projectbmw");
        dataSource.setUsername("root");
        dataSource.setPassword("qwerty");
        return dataSource;
    }

    private Properties getProperties(){
        Properties properties=new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
        bean.setPackagesToScan("com.xworkz.website.entity");
        bean.setDataSource(getDataSource());
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setJpaProperties(getProperties());
        return bean;
    }

    @Bean
    public InternalResourceViewResolver viewResolver(){
        return new InternalResourceViewResolver("/",".jsp");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setMaxInMemorySize(1048976);
        resolver.setMaxUploadSize(20971528);
        return  resolver;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
