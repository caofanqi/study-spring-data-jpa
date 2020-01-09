package cn.caofanqi.study.studyspringdatajpa.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author caofanqi
 */
@Configuration
@Profile("multi-datasource")
@AutoConfigureAfter(MultiDataSourceConfig.class)
@EnableJpaRepositories(basePackages = "cn.caofanqi.study.studyspringdatajpa.repository2",
        entityManagerFactoryRef = "entityManagerFactoryTwo",
        transactionManagerRef = "transactionManagerTwo")
public class JpaDataSourceTwoConfig {

    @Resource
    private DataSource dataSourceTwo;


    @Bean
    PlatformTransactionManager transactionManagerTwo() {
        return new JpaTransactionManager(entityManagerFactoryTwo().getObject());
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryTwo() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSourceTwo);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("cn.caofanqi.study.studyspringdatajpa.pojo.domain2");

        return factoryBean;
    }


}
