package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Order;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * @author caofanqi
 */
public interface OrderRepository extends JpaRepositoryImplementation<Order,Long> {


    Order findByOrderNo(String orderNo);

}
