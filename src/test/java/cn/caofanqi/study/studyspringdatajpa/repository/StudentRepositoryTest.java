package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Student;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.StudentAgeAndAgeCountDTO;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;
import java.util.Optional;

@Transactional
@SpringBootTest
class StudentRepositoryTest {


    @Resource
    private StudentRepository studentRepository;


    @BeforeEach
    void setup(){
        Student s1 = Student.builder().name("张三").age(23).build();
        Student s2 = Student.builder().name("李四").age(24).build();
        Student s3 = Student.builder().name("王五").age(25).build();


        studentRepository.saveAll(Lists.newArrayList(s1,s2,s3));
    }

    @Test
    void testFindStudentByJdbcName(){
        List<Student> list = studentRepository.findStudentByJdbcName("张三");
        list.forEach(s -> System.out.println(s.getName()));
    }


    @Test
    void testCustomFindById(){
        List<Student> list = studentRepository.findStudentByJdbcName("张三");
        list.forEach(s -> System.out.println(s.getName()));

        Optional<Student> studentOp = studentRepository.findById(list.get(0).getId());
        studentOp.ifPresent(student -> System.out.println(student.getName()));

    }


    @Test
    void testSave(){
        Student tom = studentRepository.save(Student.builder().name("tom").age(26).build());
        System.out.println(studentRepository);
        System.out.println(tom);
    }

    @Test
    void testFindCountGroupByAge(){
        List<StudentAgeAndAgeCountDTO> list = studentRepository.findCountGroupByAge();
        list.forEach(s -> System.out.println(s.getAge() + " : " + s.getAgeCount()));
    }


}