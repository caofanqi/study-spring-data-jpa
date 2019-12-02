package cn.caofanqi.study.studyspringdatajpa.repository.custom;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Student;

import java.util.List;

/**
 * @author caofanqi
 */
public interface StudentRepositoryCustomJdbc {

    List<Student> findStudentByJdbcName(String name);

}
