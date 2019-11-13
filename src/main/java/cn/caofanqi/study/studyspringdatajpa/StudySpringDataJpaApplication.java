package cn.caofanqi.study.studyspringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 * @author caofanqi
 */
@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
public class StudySpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudySpringDataJpaApplication.class, args);
    }

}
