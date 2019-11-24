package cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections;

import org.springframework.beans.factory.annotation.Value;

/**
 * username和全地址拼接投影
 * @author caofanqi
 */
public interface AdminUsernameAndFullAddress {

    String getUsername();

    @Value("#{target.address.province + ' ' + target.address.city + ' ' + target.address.county + ' ' + target.address.detailedAddress}")
    String getFullAddress();
}