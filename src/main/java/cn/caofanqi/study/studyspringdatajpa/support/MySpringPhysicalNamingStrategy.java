package cn.caofanqi.study.studyspringdatajpa.support;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 * 自定义命名策略
 *
 * @author caofanqi
 */
public class MySpringPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {


    /**
     * 为表添加指定前缀
     */
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return super.toPhysicalTableName(new Identifier("cfq_" + name.getText(),name.isQuoted()), jdbcEnvironment);
    }


}
