package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.AuditPerson;
import cn.caofanqi.study.studyspringdatajpa.support.IdAuditorAwareImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

@SpringBootTest
class AuditPersonRepositoryTest {


    @Resource
    private AuditPersonRepository auditPersonRepository;

    @Resource
    private AuditUserRepository auditUserRepository;

    @Resource
    private IdAuditorAwareImpl auditorAware;

    @Test
    void testAbstractAudit(){

        auditorAware.setCurrentUser(auditUserRepository.findByName("张三"));

        AuditPerson tom = AuditPerson.builder().personName("tom").build();
        AuditPerson save = auditPersonRepository.save(tom);
        System.out.println(save);

    }


    @Test
    void testFind(){
        List<AuditPerson> list = auditPersonRepository.findAll();
        list.forEach(p -> System.out.println(p.getPersonName() + " : " + p.getCreatedByUserId()));
    }

}