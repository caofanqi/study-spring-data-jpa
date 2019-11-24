package cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections;

import org.springframework.beans.factory.annotation.Value;

/**
 * 使用spring bean的方式的投影
 */
public interface AdminUsernameAndFullAddressWithSpringBean {

    String getUsername();

    @Value("#{@myAdminBean.getFullAddress(target)}")
    String getFullAddress();

}