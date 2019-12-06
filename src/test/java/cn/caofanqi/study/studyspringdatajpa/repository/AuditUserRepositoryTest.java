package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.AuditUser;
import cn.caofanqi.study.studyspringdatajpa.support.AuditorAwareImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


import static org.junit.jupiter.api.Assertions.*;

/**
 * 该测试用例，要使用指定 @EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
 */
//@SpringBootTest
class AuditUserRepositoryTest {

    @Resource
    private AuditUserRepository auditUserRepository;

    @Resource
    private AuditorAwareImpl auditorAware;


    @Test
    void testAuditDate(){
        /*
         *不设置创建和修改时间，由springl-data替我们完成
         */
        AuditUser audit = AuditUser.builder().name("张三").build();
        AuditUser save = auditUserRepository.save(audit);
        System.out.println(save);
    }

    @Test
    void testAuditUser(){

        /*
         * 模拟当前用户
         */
        auditorAware.setCurrentUser(auditUserRepository.findByName("张三"));

        /*
         * 这里不设置是谁保存的，看spring-data是否会为我们完成
         */
        AuditUser audit = AuditUser.builder().name("李四").build();

        AuditUser save = auditUserRepository.save(audit);
        System.out.println(save);
    }

}