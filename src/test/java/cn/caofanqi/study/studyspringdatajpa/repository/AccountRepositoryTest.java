package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.Account;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Rollback(false)
@Transactional
@SpringBootTest
class AccountRepositoryTest {


    @Resource
    private AccountRepository accountRepository;

    @Test
    void testSave(){
        Account account1 = Account.builder().accountName("张三的账户").balance(BigDecimal.valueOf(100)).build();
        Account account2 = Account.builder().accountName("李四的账户").balance(BigDecimal.valueOf(200)).build();
        accountRepository.saveAll(Lists.newArrayList(account1,account2));
    }


    @Test
    void testFind(){
        Account account = accountRepository.findByAccountName("张三的账户");
        System.out.println(account);
    }


    @Test
    void testUpdate(){
        Account account = accountRepository.findByAccountName("张三的账户");
        account.setBalance(account.getBalance().divide(BigDecimal.valueOf(50)));
        accountRepository.save(account);
    }


    @Test
    void testDelete(){
        Account account = accountRepository.findByAccountName("张三的账户");
        accountRepository.delete(account);
    }


}