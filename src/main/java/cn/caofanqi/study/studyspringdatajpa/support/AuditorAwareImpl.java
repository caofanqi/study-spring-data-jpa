package cn.caofanqi.study.studyspringdatajpa.support;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.AuditUser;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 获取当前的审计人,实际项目中可以从Spring Security中或Token/{session}中获取，这里只是举个例子进行模拟。
 * @author caofanqi
 */
public class AuditorAwareImpl implements AuditorAware<AuditUser> {


    private Optional<AuditUser> currentUser = Optional.empty();

    public void setCurrentUser(AuditUser currentUser){
        this.currentUser = Optional.of(currentUser);
    }

    @Override
    public Optional<AuditUser> getCurrentAuditor() {
        //要使用的当前用户
        return currentUser;
    }

}
