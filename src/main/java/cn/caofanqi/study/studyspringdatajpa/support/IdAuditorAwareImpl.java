package cn.caofanqi.study.studyspringdatajpa.support;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.AuditUser;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * AuditorAware实现示例，根据自己业务进行实现
 * @author caofanqi
 */
public class IdAuditorAwareImpl implements AuditorAware<Long> {


    private Optional<AuditUser> currentUser = Optional.empty();

    public void setCurrentUser(AuditUser currentUser){
        this.currentUser = Optional.of(currentUser);
    }

    @Override
    public Optional<Long> getCurrentAuditor() {
        return currentUser.map(AuditUser::getId);
    }

}
