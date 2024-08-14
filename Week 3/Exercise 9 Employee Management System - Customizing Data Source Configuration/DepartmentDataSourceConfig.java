package com.example.EmployeeManagementSystem.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.EmployeeManagementSystem.repository.department", // Department repository package
        entityManagerFactoryRef = "departmentEntityManagerFactory",
        transactionManagerRef = "departmentTransactionManager"
)
public class DepartmentDataSourceConfig {

    @Bean(name = "departmentDataSource")
    public DataSource departmentDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:department")
                .username("sa")
                .password("password")
                .driverClassName("org.h2.Driver")
                .build();
    }

    @Bean(name = "departmentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean departmentEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("departmentDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("com.example.EmployeeManagementSystem.model.department") // Department entity package
                .persistenceUnit("departmentPU")
                .build();
    }

    @Bean(name = "departmentTransactionManager")
    public PlatformTransactionManager departmentTransactionManager(
            @Qualifier("departmentEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
