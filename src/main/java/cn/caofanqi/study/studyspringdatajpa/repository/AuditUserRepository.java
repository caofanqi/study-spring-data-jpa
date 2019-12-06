package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.AuditUser;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;


/**
 * @author caofanqi
 */
public interface AuditUserRepository extends JpaRepositoryImplementation<AuditUser,Long> {


    AuditUser findByName(String name);

}
