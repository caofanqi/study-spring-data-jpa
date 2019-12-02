package cn.caofanqi.study.studyspringdatajpa.repository.custom.impl;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Student;
import cn.caofanqi.study.studyspringdatajpa.repository.custom.StudentRepositoryCustomJdbc;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * 默认要以Impl结尾
 * @author caofanqi
 */
public class StudentRepositoryCustomJdbcImpl implements StudentRepositoryCustomJdbc {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> findStudentByJdbcName(String name) {
        String sql = "SELECT * FROM cfq_jpa_student WHERE name = " + "'" + name + "'";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Student.class));
    }

}
