package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.EBook;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 *
 * @author caofanqi
 */
public interface EBookRepository extends JpaRepositoryImplementation<EBook,Long> {
}
