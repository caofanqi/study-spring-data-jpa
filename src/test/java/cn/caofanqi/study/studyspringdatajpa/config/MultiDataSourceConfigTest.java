package cn.caofanqi.study.studyspringdatajpa.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 多数据源测试
 * @author caofanqi
 */
@SpringBootTest
@ActiveProfiles("multi-datasource")
class MultiDataSourceConfigTest {

    @Resource
    private DruidDataSource dataSourceOne;
    @Resource
    private DruidDataSource dataSourceTwo;

    @Test
    void testDataSourceOne(){

        assertThat(dataSourceOne.getUrl()).isEqualTo("jdbc:mysql://localhost:3306/study-spring-data-jpa1?characterEncoding=UTF-8&useSSL=false");
        assertThat(dataSourceOne.getUsername()).isEqualTo("root");
        assertThat(dataSourceOne.getPassword()).isEqualTo("root");
        assertThat(dataSourceOne.getDriverClassName()).isEqualTo("com.mysql.jdbc.Driver");

        assertThat(dataSourceOne.getInitialSize()).isEqualTo(1);
        assertThat(dataSourceOne.getMinIdle()).isEqualTo(1);

        assertThat(dataSourceOne.getMaxActive()).isEqualTo(10);
        assertThat(dataSourceOne.getMaxWait()).isEqualTo(10000);
    }


    @Test
     void testDataSourceTwo() {

        assertThat(dataSourceTwo.getUrl()).isEqualTo("jdbc:mysql://localhost:3306/study-spring-data-jpa2?characterEncoding=UTF-8&useSSL=false");
        assertThat(dataSourceTwo.getUsername()).isEqualTo("root");
        assertThat(dataSourceTwo.getPassword()).isEqualTo("root");
        assertThat(dataSourceTwo.getDriverClassName()).isEqualTo("com.mysql.jdbc.Driver");

        assertThat(dataSourceTwo.getInitialSize()).isEqualTo(2);
        assertThat(dataSourceTwo.getMinIdle()).isEqualTo(2);

        assertThat(dataSourceTwo.getMaxActive()).isEqualTo(20);
        assertThat(dataSourceTwo.getMaxWait()).isEqualTo(20000);
    }

}