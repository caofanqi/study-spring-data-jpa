package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Student;
import cn.caofanqi.study.studyspringdatajpa.repository.custom.StudentRepositoryCustom;
import cn.caofanqi.study.studyspringdatajpa.repository.custom.StudentRepositoryCustomJdbc;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * 继承jpa的repository，和自己自定义的扩展
 * @author caofanqi
 */
public interface StudentRepository extends JpaRepositoryImplementation<Student,Long>, StudentRepositoryCustomJdbc,StudentRepositoryCustom<Student,Long> {
}
