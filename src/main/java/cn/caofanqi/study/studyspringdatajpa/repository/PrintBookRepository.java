package cn.caofanqi.study.studyspringdatajpa.repository;


import cn.caofanqi.study.studyspringdatajpa.pojo.domain.PrintBook;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * @author caofanqi
 */
public interface PrintBookRepository extends JpaRepositoryImplementation<PrintBook,Long> {
}
