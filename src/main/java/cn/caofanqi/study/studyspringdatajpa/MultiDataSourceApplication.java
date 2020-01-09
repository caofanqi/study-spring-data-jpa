package cn.caofanqi.study.studyspringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类，测试jpa多数据源使用
 * @author caofanqi
 */
//@EnableAsync
//@SpringBootApplication
//@EnableTransactionManagement
public class MultiDataSourceApplication{


    public static void main(String[] args) {
        SpringApplication.run(MultiDataSourceApplication.class, args);
    }


}
