package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 组织
 * @author caofanqi
 */
@Getter
@Setter
@Entity
@Table(name = "jpa_organization")
@NoArgsConstructor
@AllArgsConstructor
public class Organization extends AbstractID{

    private String code;

    private String name;

}
