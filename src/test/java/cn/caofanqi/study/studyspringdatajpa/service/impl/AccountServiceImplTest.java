package cn.caofanqi.study.studyspringdatajpa.service.impl;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Account;
import cn.caofanqi.study.studyspringdatajpa.repository.AccountRepository;
import cn.caofanqi.study.studyspringdatajpa.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Rollback(false)
@Transactional
@SpringBootTest
class AccountServiceImplTest {

    @Resource
    private AccountService accountService;

    @Resource
    private AccountRepository accountRepository;

    @Test
    void addAccountMoney() throws InterruptedException {

        CountDownLatch count = new CountDownLatch(2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            String result = accountService.addAccountMoney("张三的账户", BigDecimal.valueOf(-50));
            System.out.println(Thread.currentThread().getName() + "，result : " + result);
            count.countDown();
        });

        TimeUnit.SECONDS.sleep(1);

        executorService.execute(() -> {
            String result = accountService.addAccountMoney("张三的账户", BigDecimal.valueOf(100));
            System.out.println(Thread.currentThread().getName() + "，result : " + result);
            count.countDown();
        });

        count.await(10, TimeUnit.SECONDS);

        Account endAccount = accountRepository.findByAccountName("张三的账户");
        System.out.println("final balance ：" + endAccount.getBalance());

    }


    /**
     *  for update ，如果不通过索引条件检索数据，那么InnoDB将对表中的所有记录加锁，实际效果跟锁表一样
     */
    @Test
    void addAccountMoney2() throws InterruptedException {

        CountDownLatch count = new CountDownLatch(2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            String result = accountService.addAccountMoney("张三的账户", BigDecimal.valueOf(-50));
            System.out.println(Thread.currentThread().getName() + "，result : " + result);
            count.countDown();
        });

        TimeUnit.SECONDS.sleep(1);

        executorService.execute(() -> {
            String result = accountService.addAccountMoney("李四的账户", BigDecimal.valueOf(100));
            System.out.println(Thread.currentThread().getName() + "，result : " + result);
            count.countDown();
        });

        count.await(20, TimeUnit.SECONDS);

    }

}