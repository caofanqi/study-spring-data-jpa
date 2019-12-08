package cn.caofanqi.study.studyspringdatajpa.service.impl;

import cn.caofanqi.study.studyspringdatajpa.pojo.Account;
import cn.caofanqi.study.studyspringdatajpa.repository.AccountRepository;
import cn.caofanqi.study.studyspringdatajpa.service.AccountService;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author caofanqi
 */
@Service
public class AccountServiceImpl implements AccountService {


    @Resource
    private AccountRepository accountRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addAccountMoney(String accountName, BigDecimal money){

        try {
            updateAccount(accountName, money);
            return "success";
        }catch (ObjectOptimisticLockingFailureException e){
            //记录日志，重新操作...
            return "fail";
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void updateAccount(String accountName, BigDecimal money) {
        System.out.println(Thread.currentThread().getName() + "，addAccountMoney start...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Account account = accountRepository.findByAccountName(accountName);
        System.out.println(Thread.currentThread().getName() + "，find balance : " + account.getBalance());
        account.setBalance(account.getBalance().add(money));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Account result = accountRepository.save(account);
        System.out.println(Thread.currentThread().getName() + "， update balance end ,balance : " + result.getBalance());


        System.out.println(Thread.currentThread().getName() + "，addAccountMoney sleep...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "，addAccountMoney end...");
    }


}
