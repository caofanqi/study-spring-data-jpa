package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.AuditPerson;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * @author caofanqi
 */
public interface AuditPersonRepository extends JpaRepositoryImplementation<AuditPerson,Long> {
}
