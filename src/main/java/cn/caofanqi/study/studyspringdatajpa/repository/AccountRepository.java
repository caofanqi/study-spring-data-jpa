package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Account;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import javax.persistence.LockModeType;


/**
 * @author caofanqi
 */
public interface AccountRepository extends JpaRepositoryImplementation<Account,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Account findByAccountName(String accountName);

}
