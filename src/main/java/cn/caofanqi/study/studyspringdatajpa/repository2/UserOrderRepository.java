package cn.caofanqi.study.studyspringdatajpa.repository2;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain2.UserOrder;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * @author caofanqi
 */
public interface UserOrderRepository extends JpaRepositoryImplementation<UserOrder,Long> {
}
