package cn.caofanqi.study.studyspringdatajpa.service;


import java.math.BigDecimal;

/**
 * @author caofanqi
 */
public interface AccountService {

     String addAccountMoney(String accountName, BigDecimal money);
}
