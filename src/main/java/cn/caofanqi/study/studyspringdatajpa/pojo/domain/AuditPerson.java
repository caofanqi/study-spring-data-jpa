package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author caofanqi
 */
@Getter
@Setter
@Entity
@Builder
@Table(name = "jpa_audit_person")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class AuditPerson extends AbstractAuditDomain {

    private String personName;

}
