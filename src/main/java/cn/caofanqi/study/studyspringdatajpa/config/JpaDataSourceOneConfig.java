package cn.caofanqi.study.studyspringdatajpa.config;

import cn.caofanqi.study.studyspringdatajpa.support.IdAuditorAwareImpl;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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
@EnableJpaAuditing(auditorAwareRef = "idAuditorAwareImpl")
@EnableJpaRepositories(basePackages = "cn.caofanqi.study.studyspringdatajpa.repository",
        entityManagerFactoryRef = "entityManagerFactoryOne",
        transactionManagerRef = "transactionManagerOne")
public class JpaDataSourceOneConfig {

    @Resource
    private DataSource dataSourceOne;


    @Bean
    @Primary
    PlatformTransactionManager transactionManagerOne() {
        return new JpaTransactionManager(entityManagerFactoryOne().getObject());
    }

    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean entityManagerFactoryOne() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSourceOne);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("cn.caofanqi.study.studyspringdatajpa.pojo.domain");

        return factoryBean;
    }


    @Bean(name = "idAuditorAwareImpl")
    public AuditorAware<Long> idAuditorAwareImpl() {
        return new IdAuditorAwareImpl();
    }

}
