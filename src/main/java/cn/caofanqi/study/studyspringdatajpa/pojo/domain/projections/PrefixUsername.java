package cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections;

import org.springframework.beans.factory.annotation.Value;

/**
 * spel使用方法中的参数值投影
 * @author caofanqi
 */
public interface PrefixUsername {

    String getUsername();

    @Value("#{args[0] + '' + target.username + '!'}")
    String getPrefixUsername(String prefix);

}