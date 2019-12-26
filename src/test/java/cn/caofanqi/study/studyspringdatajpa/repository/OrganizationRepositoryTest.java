package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class OrganizationRepositoryTest {


    @Resource
    private OrganizationRepository organizationRepository;

    @Resource
    private PlatformTransactionManager transactionManager;


    /**
     * 观察update语句和success打印的顺序，没有调用flush方法时，success在update语句之前打印，因为此时事务还没有提交，没有将修改同步到数据库。
     * 我们可以使用flush方法，将修改立即同步到数据库。
     */
    @Test
    void test1(){

        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        Optional<Organization> organizationOp = organizationRepository.findById(1L);
        Organization organization = organizationOp.orElseThrow(() -> new RuntimeException("查询不到数据"));
        organization.setName("xxxx");
        organizationRepository.flush();
        System.out.println("success");

        //提交事务
        transactionManager.commit(status);
        //回滚事务
//        transactionManager.rollback(status);
    }

    /**
     * 两次findById方法，只执行了一次SQL
     */
    @Test
    @Transactional
    void test2(){
        organizationRepository.findById(1L);
        organizationRepository.findById(1L);
    }

    /**
     * 使用方法派生查询出数据，在使用findById方法查询，只执行一次SQL
     */
    @Test
    @Transactional
    void test3(){
        organizationRepository.findOrganizationById(1L);
        organizationRepository.findById(1L);
    }

    /**
     * 使用@Query查询出数据，在使用findById方法查询，只执行一次SQL
     */
    @Test
    @Transactional
    void test4(){
        organizationRepository.selectById(1L);
        organizationRepository.findById(1L);
    }

    /**
     *  五句SQL
     */
    @Test
    @Transactional
    void test5(){
        organizationRepository.findById(1L);
        organizationRepository.selectById(1L);
        organizationRepository.selectById(1L);
        organizationRepository.findOrganizationById(1L);
        organizationRepository.findOrganizationById(1L);
    }


    /**
     * 对持久性上下文中的对象进行修改的话，再执行非findById查询时，不调用flush方法也会立刻同步，而不是事务提交时在同步
     * select，update，select，打印xxxx 3句SQL
     */
    @Test
    @Rollback(false)
    @Transactional
    void test6(){
        Optional<Organization> organizationOp = organizationRepository.findOrganizationById(1L);
        Organization organization = organizationOp.orElseThrow(() -> new RuntimeException("查询不到数据"));
        organization.setName("xxxx");
        organizationOp =  organizationRepository.findOrganizationById(1L);
        organization = organizationOp.orElseThrow(() -> new RuntimeException("查询不到数据"));
        System.out.println(organization.getName());
    }


    /**
     * 对持久性上下文中的对象进行修改的话，执行findById查询时，会先从持久化上下文中查找，找到了不再执行查询SQL
     * select，打印xxxx ，update 2句SQL
     */
    @Test
    @Rollback(false)
    @Transactional
    void test7(){
        Optional<Organization> organizationOp = organizationRepository.findById(1L);
        Organization organization = organizationOp.orElseThrow(() -> new RuntimeException("查询不到数据"));
        organization.setName("xxxx");
//        organizationRepository.flush();
        organizationOp = organizationRepository.findById(1L);
        organization = organizationOp.orElseThrow(() -> new RuntimeException("查询不到数据"));
        System.out.println(organization.getName());
    }


    /**
     * 当@Modifying属性clearAutomatically为false时，修改后不清空持久化上下文，
     * 使用findById查询时，如果从持久化上下文中找到要查询的对象，那么该对象状态是未修改之前的。
     */
    @Test
    @Rollback(false)
    @Transactional
    void test8(){
        //向持久化上下文中存放对象
        Optional<Organization> organizationOp = organizationRepository.findById(1L);

        //如果使用@Query+@Modifying进行操作对象时，持久化上下文中的对象不会受到影响
        organizationRepository.updateNameById1(1L,"xxxx");

        //findById方法会取持久化上下文中的对象，（name没有修改为xxxx的）,打印的是组织001
        organizationOp = organizationRepository.findById(1L);
        Organization organization = organizationOp.orElseThrow(() -> new RuntimeException("查询不到数据"));
        System.out.println(organization.getName());
    }

    /**
     * 当@Modifying属性clearAutomatically为true时，修改后清空持久化上下文
     * 使用findById查询时，因为持久化上下文被清空了，所以会再次执行select语句。
     */
    @Test
    @Rollback(false)
    @Transactional
    void test9(){
        //向持久化上下文中存放对象
        Optional<Organization> organizationOp = organizationRepository.findById(1L);

        //如果使用@Query+@Modifying进行操作对象时，持久化上下文中的对象不会受到影响，但设置了清空持久化上下文
        organizationRepository.updateNameById2(1L,"xxxx");

        //因为持久化上下文中是空的，所以findById会执行select语句，打印的是xxxx
        organizationOp = organizationRepository.findById(1L);
        Organization organization = organizationOp.orElseThrow(() -> new RuntimeException("查询不到数据"));
        System.out.println(organization.getName());
    }


}