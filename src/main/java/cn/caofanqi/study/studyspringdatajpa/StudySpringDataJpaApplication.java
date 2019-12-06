package cn.caofanqi.study.studyspringdatajpa;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.AuditUser;
import cn.caofanqi.study.studyspringdatajpa.repository.impl.MyRepositoryImpl;
import cn.caofanqi.study.studyspringdatajpa.support.AuditorAwareImpl;
import cn.caofanqi.study.studyspringdatajpa.support.IdAuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.persistence.Id;

/**
 * 启动类
 * @author caofanqi
 */
@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(
        /*queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND*/
     /*   ,repositoryImplementationPostfix = "MyPostfix",*/
        /*repositoryBaseClass = MyRepositoryImpl.class*/)
@EnableJpaAuditing(auditorAwareRef = "idAuditorAwareImpl")
public class StudySpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudySpringDataJpaApplication.class, args);
    }

    /**
     * 如果ApplicationContext中只有一个AuditorAware类型的bean，Spring-Date会自动选择，
     * 如果又多个，需要通过@EnableJpaAuditing注解的auditorAwareRef属性进行设置。
     */
    @Bean(name = "auditorAwareImpl")
    public AuditorAware<AuditUser> auditorAwareImpl() {
        return new AuditorAwareImpl();
    }

    @Bean(name = "idAuditorAwareImpl")
    public AuditorAware<Long> idAuditorAwareImpl() {
        return new IdAuditorAwareImpl();
    }

}
