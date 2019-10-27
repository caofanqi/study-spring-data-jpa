package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.IdClassDemo;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.UnionKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.Optional;

@Rollback(false)
@Transactional
@SpringBootTest
class IdClassDemoRepositoryTest {

    @Autowired
    private IdClassDemoRepository idClassDemoRepository;

    @Test
    public void test(){
        IdClassDemo idClassDemo = new IdClassDemo();
        idClassDemo.setIdOne("IdOne");
        idClassDemo.setIdTwo("IdTwo");
        idClassDemo.setContext("xxxxx");

        idClassDemoRepository.save(idClassDemo);

        Optional<IdClassDemo> op = idClassDemoRepository.findById(new UnionKey("IdOne", "IdTwo"));
        Assertions.assertEquals("xxxxx",op.get().getContext());
    }

}