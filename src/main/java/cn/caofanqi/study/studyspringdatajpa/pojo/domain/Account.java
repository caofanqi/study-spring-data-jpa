package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.AbstractID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;

/**
 * 账户实体
 *
 * @author caofanqi
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Table(name = "jpa_account")
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractID {

    /**
     *  简单代表一下账户所属人
     */
    @Column(unique = true,nullable = false)
    private String accountName;

    @Column(columnDefinition = "DECIMAL(19, 2)")
    private BigDecimal balance;

    /**
     * 乐观锁version
     */
//    @Version
    private Integer version;

}
